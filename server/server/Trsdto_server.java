package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jdbc.Transfer.Transfer_autoDTO;
import jdbc.Transfer.Transfer_delayDTO;
import jdbc.Transfer.Transfer_reserveDTO;
import jmodels.Transfer_delayDAO;
import jmodels.Transfer_reserveDAO; 

public class Trsdto_server {
	private HashMap<String, ObjectOutputStream>list  = null;
	private HashMap<Integer, Transfer_delayDTO> delayList  = null;
	private HashMap<Integer, Transfer_reserveDTO> reserveList  = null;
	private HashMap<Integer, ArrayList<String>>auto = null;
	private HashMap<Integer, Transfer_autoDTO> autoList  = null;
	private Date today = new Date();

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

			
//			for (Transfer_delayDTO tdd  : Transfer_delayDAO.getInstance().list() ) {
//				delayList.put(tdd.getSeq(), tdd);
//			}
//			
//			for (Transfer_reserveDTO trd  : Transfer_reserveDAO.getInstance().list() ) {
//				reserveList.put(trd.getSeq(), trd);
//			}
			
			
			
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
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			while(true) {
				try {

					sleep(5000);
					for (Map.Entry<Integer,Transfer_delayDTO> dd: delayList.entrySet()) {
						if(dd.getValue().getTrs_time().before(new Date()) && dd.getValue().getStatus().equals("활성") )
						{
							System.out.println(dd.getValue() +"지연 이체 가즈아에요");
							dd.getValue().setStatus("비활성");
						}					
					};

					for (Map.Entry<Integer, Transfer_reserveDTO> dd: reserveList.entrySet()) {
						if(dd.getValue().getTime().before(new Date()) && dd.getValue().getStatus().equals("활성") )
						{
							System.out.println(dd.getValue() );
							System.out.println(dd.getValue().getTimeStr() +"자 예약 이체가 실행 되었습니다.");
							dd.getValue().setStatus("이체완료");
						}
					};
					if(!sdf.format(today).equals(sdf.format(new Date() ) )  ) {
						for (Map.Entry<Integer, ArrayList<String>> dd: auto.entrySet()) {
							if(dd.getValue().contains(sdf.format(new Date()) ) && autoList.get(dd.getKey()).getStatus().equals("활성") )
							{
								System.out.println("오늘자 자동이체 되었습니다."); 
							}
						};
						today = new Date();
					}
					//DB를 여기서 돌리시오 
					System.out.println("돌고있어요오");
//					for (Transfer_delayDTO dto  : delayList.values()) {
//						System.out.println(dto +" delay 들입니다.");
//					}
					for (Transfer_reserveDTO dto  : reserveList.values()) {
						System.out.println(dto +" reserve 들입니다.");
					}
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
				System.out.println("오자마자");
				Acc_Member_Data def = (Acc_Member_Data)dis.readObject();
				System.out.println("여기못옴");
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
						Transfer_reserveDAO.getInstance().updateStatusBySeq(""+dto.getSeq(), "비활성");
					}
				}
				else if( def.msg.equals("reserve") ){
					
					Transfer_reserveDTO dto = (Transfer_reserveDTO)def.data;
					
					if(def.name.equals("추가")) {
						System.out.println("reserve 데이터를 추가합니다.");
						reserveList.put(dto.getSeq(), dto);
					}
					else
					{
						System.out.println("reserve 데이터를 비활성화합니다.");
						reserveList.get(dto.getSeq()).setStatus("비활성");
						
					}
				}
				else if(def.msg.equals("auto") ){
					
					Transfer_autoDTO dto =(Transfer_autoDTO)def.data;
					
					if(def.name.equals("추가")){
					
						autoList.put(dto.getSeq(), dto);
						
						int syear =  dto.getStart_date().getYear()+1900;
						int smonth = dto.getStart_date().getMonth();
						int sdate = dto.getStart_date().getDate();
						
						Calendar cd = Calendar.getInstance();

						cd.set(syear , smonth, sdate);
					
						int fyear = dto.getFinish_date().getYear()+1900;
						int fmonth = dto.getFinish_date().getMonth();
						int fdate = dto.getFinish_date().getDate();
	
						Calendar cd2 = Calendar.getInstance();
						
						cd2.set(fyear , fmonth, fdate);
						
						ArrayList<String> ad     = new ArrayList<String>();
					
						
						while(cd.before(cd2))
						{
							ad.add(sdf.format(cd.getTime() ) );
							cd.add(Calendar.MONTH, 1);
							System.out.println(ad);
						}
						auto.put(dto.getSeq(), ad);
					}
					else
					{
						autoList.get(dto.getSeq()).setStatus("비활성");;
					}
				}

//				sendToOne(def);
//
//				while(dis!=null) {
//					Acc_Member_Data data = (Acc_Member_Data)dis.readObject();
//					if(data.target != "DB"){
//						sendToOne(data);
//					}
//				}
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
