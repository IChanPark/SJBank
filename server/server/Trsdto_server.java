package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jdbc.Account.AccountDTO;
import jdbc.Transfer.Transfer_autoDTO;
import jdbc.Transfer.Transfer_delayDTO;
import jdbc.Transfer.Transfer_logDTO;
import jdbc.Transfer.Transfer_reserveDTO;
import jmodels.AccountDAO;
import jmodels.Transfer_logDAO;
import jmodels.Transfer_reserveDAO; 

public class Trsdto_server {
	private HashMap<String, ObjectOutputStream>list  = null;
	private HashMap<Integer, Transfer_delayDTO> delayList  = null;
	private HashMap<Integer, Transfer_reserveDTO> reserveList  = null;
	private HashMap<Integer, ArrayList<String>>auto = null;
	private HashMap<Integer, Transfer_autoDTO> autoList  = null;


	private Trsdto_server() {
		try {

			list = new HashMap<String, ObjectOutputStream>();
			delayList= new HashMap<Integer, Transfer_delayDTO>();
			reserveList= new HashMap<Integer, Transfer_reserveDTO>();
			auto = new  HashMap<Integer, ArrayList<String>>();
			autoList= new HashMap<Integer, Transfer_autoDTO>();
			Collections.synchronizedMap(list);
			Collections.synchronizedMap(delayList);
			Collections.synchronizedMap(reserveList);
			Collections.synchronizedMap(auto);
			Collections.synchronizedMap(autoList);

			for (Transfer_reserveDTO trd  : new Transfer_reserveDAO().list() ) {
				reserveList.put(trd.getSeq(), trd);
			}

			ServerSocket server = new ServerSocket(7777);
			System.out.println("기동 서버시작");
			SelectDB s = new SelectDB();
			s.setDaemon(true);
			s.start();
			while(true) {
				Socket client = server.accept();
				new TCPMulServerReciever(client).start();
			}
		} catch (IOException e) {}
	}

	private static class Holder {
		public static final Trsdto_server Server = new Trsdto_server();
	}

	public static Trsdto_server getInstance() {
		return Holder.Server;
	}

	class SelectDB extends Thread{

		@Override
		public void run() {

			while(true) {
				try {
					sleep(10000);

					for (Map.Entry<Integer, Transfer_reserveDTO> dd: reserveList.entrySet()) {
						if(dd.getValue().getTime().before(new Date()) && dd.getValue().getStatus().equals("활성") )
						{

							System.out.println(dd.getValue().getTimeStr() +"자 예약 이체가 실행 되었습니다.");

							String  toName = (new AccountDAO().chkOurBank(dd.getValue().getTo_account_number()));

							Transfer_logDTO dto = new Transfer_logDTO();
							dto.setAccount_number(dd.getValue().getAccount_number());
							dto.setTo_account_number(dd.getValue().getTo_account_number());
							dto.setFeetype("예약이체송금");
							dto.setTarget(dd.getValue().getTarget());
							dto.setSum((long)Integer.parseInt(dd.getValue().getSum()) );
							dto.setCms(dd.getValue().getCms());
							dto.setMemo(dd.getValue().getMemo());
							dto.setTo_memo(dd.getValue().getTo_memo());

							if(dd.getValue().getTarget().equals("SJBank"))
								dto.setFee(0);
							else
								dto.setFee(500);

							if(dto.getTarget().toUpperCase().equals("SJBANK") ||dto.getTarget().toUpperCase().equals("SJ은행")  )
								dto.setReceived( new AccountDAO().chkOurBank(dd.getValue().getTo_account_number()) );
							else
								dto.setReceived("외부계좌");

							AccountDTO adto = new AccountDAO().selectAccount(dd.getValue().getAccount_number());

							if(adto.getSum()>dto.getSum()+dto.getFee()) {
								dto.setStatus("성공");
								//계좌 잔액 변경
								AccountDTO toDto = new AccountDAO().selectAccount(dd.getValue().getTo_account_number());
								if(dto.getTarget().equals("SJBank") && toDto.getType().equals("적금")  )
								{
									int toMax = new AccountDAO().getSavingMax( dd.getValue().getTo_account_number()  );
									if( toMax < toDto.getSum()+dto.getSum())
									{
										dto.setStatus("대상한도초과");
										new Transfer_reserveDAO().updateStatusBySeq(dd.getValue().getSeq(), "대상한도초과");
										dd.getValue().setStatus("대상한도 초과");
									}
									else {
										dto.setStatus("성공");
										new AccountDAO().updateMoney(-1 * Integer.parseInt( dd.getValue().getSum())-1*dto.getFee() , dd.getValue().getAccount_number());
										new Transfer_reserveDAO().updateStatusBySeq(dd.getValue().getSeq(), "예약이체완료");
										dd.getValue().setStatus("이체완료");
									}
								}
								else 
								{
									new AccountDAO().updateMoney(-1 * Integer.parseInt( dd.getValue().getSum())-1*dto.getFee() , dd.getValue().getAccount_number());
									new Transfer_reserveDAO().updateStatusBySeq(dd.getValue().getSeq(), "예약이체완료");
									dd.getValue().setStatus("이체완료");
								}
							}
							else {
								dto.setStatus("실패");
								new Transfer_reserveDAO().updateStatusBySeq(dd.getValue().getSeq(), "금액부족");
								dd.getValue().setStatus("금액부족");
							}
							new Transfer_logDAO().insert(dto);
							////////////////////////////////////////자기꺼

							///자행 이체 상대방 로그 넣기

							if( ( dd.getValue().getTarget().equals("SJBank") ) && ( adto.getSum()>dto.getSum()+dto.getFee()  ) && dto.getStatus().equals("성공") )
							{
								Transfer_logDTO ddto = new Transfer_logDTO();
								ddto.setAccount_number(dd.getValue().getTo_account_number());
								ddto.setTo_account_number(dd.getValue().getAccount_number());
								ddto.setFeetype("예약이체입금");
								ddto.setTarget("SJBank");
								ddto.setSum((long)Integer.parseInt(dd.getValue().getSum()) );
								ddto.setCms("");
								ddto.setMemo(dd.getValue().getTo_memo());
								ddto.setTo_memo(dd.getValue().getMemo());
								ddto.setFee(0);
								ddto.setReceived( new AccountDAO().chkOurBank(dd.getValue().getAccount_number()) );
								ddto.setStatus("성공");
								new Transfer_logDAO().insert(ddto);
								new AccountDAO().updateMoney( Integer.parseInt( dd.getValue().getSum()) , dd.getValue().getTo_account_number());
							}
						}
					};
				} catch (Exception e) {}
			}
		}
	}

	////각 클라이언트에 대한 리시버 이면서 받으면 바로 모든 접속자에게 메세지 보냄
	class TCPMulServerReciever extends Thread{

		ObjectOutputStream dos;
		ObjectInputStream dis;
		String name;

		TCPMulServerReciever(Socket client) {
			try {
				dos = new ObjectOutputStream(client.getOutputStream());
				dis = new ObjectInputStream(client.getInputStream());
			} catch (IOException e) {}
		}

		@Override
		public void run() { 
			try {
				Acc_Member_Data def = (Acc_Member_Data)dis.readObject();
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				name = def.name;
				list.put(name,dos);
				System.out.println(def.msg +"받은 메세지");
				if(def.msg.equals("delay") ) {

					Transfer_delayDTO dto=(Transfer_delayDTO)def.data;

					if(def.name.equals("추가")) 
					{
						System.out.println("delay 데이터를 추가합니다.");
						delayList.put(dto.getSeq(), dto);
					}
					else
					{
						System.out.println("delay 데이터를 비활성화합니다.");
						delayList.get(dto.getSeq()).setStatus("비활성");
						//Transfer_reserveDAO.getInstance().updateStatusBySeq(dto.getSeq(), "비활성");
					}
				}
				else if( def.msg.equals("reserve") ){

					Transfer_reserveDTO dto = (Transfer_reserveDTO)def.data;

					if(def.name.equals("추가")) {
						System.out.println("reserve 데이터를 추가합니다.");
						dto.setSeq(new Transfer_reserveDAO().reSeqInsert(dto));
						reserveList.put(dto.getSeq(), dto);
					}
					else
					{
						System.out.println("reserve 데이터를 비활성화합니다.");
						reserveList.get(dto.getSeq()).setStatus("비활성");
						new Transfer_reserveDAO().updateStatusBySeq(dto.getSeq(), "비활성");
					}
				}
			}catch (Exception e) {
				System.out.println("에러에요"+e.getMessage());

			}
			finally {  
				list.remove(name);   
				System.out.println("나감 "+name+"\n 접속자 "+list.keySet());
			}
		}
	}

	void sendToOne(Acc_Member_Data data) {
		ObjectOutputStream dd = list.get(data.target);
		System.out.println("타겟 "+data.target+" 보내는이 "+data.name);
		System.out.println("보내는내용"+data.msg);
		try {
			dd.writeObject(data);
			dd.flush();
			dd.reset();
		} catch (IOException e) { e.printStackTrace(); }
	}
}
