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
import prodfund.FundDAO;
import prodfund.Fund_InfoDAO;

public class Product_Server {
	private HashMap<String, ObjectOutputStream> list = null;
	private HashMap<Integer, Fund_InfoDTO> fundinfoList = null;

	private Date today = new Date();

	private ArrayList getsys() {
		ArrayList mm = null;
		FundDTO fir = null;

		int random;
		float changde_price_modify;
		float new_exchange; // 구매한량 * 현재가격(평가금액)
		float new_nowmoney; // 출금가능돈(평가금액)

		fir = new FundDAO().selectAccount("010-4794-6870-6563");

		random = (int) ((Math.random() * 99) + 1);
		System.out.println("랜덤값" + random);
		System.out.println("겟모디파이 : " + fir.getPrice_modify());
		changde_price_modify = fir.getPrice_modify() + random;
		new_exchange = fir.getBuynum() * changde_price_modify; // 구매한량 * 현재가격(평가금액)
		new_nowmoney = new_exchange + fir.getRest(); // 출금가능돈(평가금액)

		mm.add(changde_price_modify);
		mm.add(new_exchange);
		mm.add(new_nowmoney);

		return mm;
	}

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
					if (!sdf.format(today).equals(sdf.format(new Date()))) {

						
						 String changde_price_modify = (String)getsys().get(0); 
						  float new_exchange =(float) getsys().get(1);
						  float new_nowmoney = (float) getsys().get(2);
						 System.out.println("정보가져오기~~"+new_nowmoney);
						 
						new FundDAO().updatetoday((float) 0.5, new_nowmoney,new_exchange, changde_price_modify,
								"010-4794-6870-6563");
						new Fund_InfoDAO().updateprice_modify("changde_price_modify",
								"MCO글로벌인컴혼합자산투자신탁(H)[재간접형]종류A-Es");
						//Fund_InfoDAO.getInstance().list();
					}

				} catch (Exception e) {
				}
			}
		}
	}
}
