package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import jdbc.Fund.FundDTO;
import jdbc.Fund.Fund_InfoDTO;
import jdbc.Fund.Fund_LogDTO;
import prodfund.FundDAO;
import prodfund.Fund_InfoDAO;
import prodfund.Fund_LogDAO;

public class Product_Server {
	private HashMap<String, ObjectOutputStream> list = null;
	private HashMap<Integer, Fund_InfoDTO> fundinfoList = null;
	private	ArrayList<FundDTO>  fdlog = new  FundDAO().uplist();
	private Fund_LogDTO fdloginsert = new Fund_LogDTO();
	
	private Date today = new Date();
//
//	private ArrayList<Float> getsys(String acc) {
//		ArrayList<Float> mm = new ArrayList<Float>();
//		FundDTO fir = new FundDTO();
//		
//		int random;
//		float changde_price_modify;
//		float new_exchange; // 구매한량 * 현재가격(평가금액)
//		float new_nowmoney; // 출금가능돈(평가금액)
//
//		fir = new FundDAO().selectAccount(acc);
//
//		random = (int) ((Math.random() * 99) + 1);
//		System.out.println("랜덤값" + random);
//		System.out.println("겟모디파이 : " + fir.getPrice_modify());
//		changde_price_modify = Float.parseFloat(fir.getPrice_modify()) + random;
//		System.out.println("changde_price_modify"+changde_price_modify);
//		new_exchange = fir.getBuynum() * changde_price_modify; // 구매한량 * 현재가격(평가금액)
//		System.out.println("new_exchange"+new_exchange);
//		new_nowmoney = new_exchange + fir.getRest(); // 출금가능돈(평가금액)
//		System.out.println("new_nowmoney"+new_nowmoney);
//		
//		mm.add(changde_price_modify);
//		mm.add(new_exchange);
//		mm.add(new_nowmoney);
//		
//		System.out.println("mm들"+changde_price_modify+new_exchange+new_nowmoney);
//		return mm;
//	}

	private Product_Server() {
		try {
			fundinfoList = new HashMap<Integer, Fund_InfoDTO>();
			Collections.synchronizedMap(fundinfoList);

			for (Fund_InfoDTO trd : new Fund_InfoDAO().list()) {
				fundinfoList.put(trd.getSeq(), trd);
			}

			list = new HashMap<String, ObjectOutputStream>();
			Collections.synchronizedMap(list);
			ServerSocket server = new ServerSocket(7778);
			System.out.println("서버시작");
			SelectDB s = new SelectDB();
			//s.setDaemon(true);
			s.start();

		} catch (IOException e) {
		}
	}

	private static class Holder {
		public static final Product_Server Server = new Product_Server();
	}

	public static Product_Server getInstance() {
		return Holder.Server;
	}

	class SelectDB extends Thread {
		@Override
		public void run() {
			System.out.println("db돌리기  실행 들어옴");
			//new FundDAO().updatetoday((float)0.5,(float)12345,(float)67890,(float)1031,"010-4794-6870-6563");
			System.out.println("db돌리기 들어옴");
			 
			
			//SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("db돌리기2 들어옴");
			while (true) {
				try {
					sleep(1000);
					System.out.println("돈다");
					
					
					for (FundDTO ff : fdlog) {
						
						//!sdf.format(today).equals(sdf.format(new Date()))&&
						
							System.out.println(ff.getAccount_number()+ " fgac" );
							System.out.println("if 들어온다.");
							System.out.println("null마들기 성공");
							fdloginsert.setAccount_number(ff.getAccount_number());
							System.out.println("계좌셋 성공");
							fdloginsert.setStatus("성공");
							System.out.println("첫번째 성공");
							int random = (int) ((Math.random() * 99) + 1);
							int pmr = (int) ((Math.random() * 99) + 1);
							if(pmr%2==0) {
								random=random * -1;
							}
							System.out.println("random "+random);
							System.out.println("getPrice_modify "+ff.getPrice_modify());
							Float changde_price_modify = ff.getPrice_modify() + random;
							System.out.println("changde_price_modify  "+changde_price_modify);
							  float new_exchange = ff.getBuynum() * changde_price_modify; // 구매한량 * 현재가격(평가금액)
							  float new_nowmoney = new_exchange + ff.getRest();
							 System.out.println("정보가져오기~~"+new_nowmoney);
							 fdloginsert.setFluctuation((float)0.0);
							 fdloginsert.setSum((int)new_nowmoney);
							 
							 String string_changde_price_modify= ff.getPrice_modify() + random+"";
							 
							new FundDAO().updatetoday((float) 0.5, new_nowmoney,new_exchange, string_changde_price_modify,
									ff.getAccount_number());
							new Fund_InfoDAO().updateprice_modify(string_changde_price_modify,
									"MCO글로벌인컴혼합자산투자신탁(H)[재간접형]종류A-Es");
							new Fund_LogDAO().insert(fdloginsert);
							
							 System.out.println("펀드 성공~~!!!");
							//Fund_InfoDAO.getInstance().list();
						
						
						
					}

				} catch (Exception e) {
				}
			}
		}
	}
}
