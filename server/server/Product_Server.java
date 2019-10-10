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

import jdbc.Fund.FundDTO;
import jdbc.Fund.Fund_InfoDTO;
import jdbc.Transfer.Transfer_autoDTO;
import jdbc.Transfer.Transfer_reserveDTO;
import jmodels.Transfer_reserveDAO;
import prodfund.FundDAO;
import prodfund.Fund_InfoDAO; 

public class Product_Server {
	private HashMap<String, ObjectOutputStream>list  = null;
	private HashMap<Integer, Fund_InfoDTO> fundinfoList  = null;
	FundDTO fir  = new FundDAO().selectAccount("010-4794-6870-6563");
	private Date today = new Date();
	
	int random = (int)((Math.random()*99)+1);
	float changde_price_modify=fir.getPrice_modify()+random;
	float new_exchange=	fir.getBuynum()*changde_price_modify; //구매한량 * 현재가격(평가금액)
	float new_nowmoney = new_exchange+fir.getRest(); //출금가능돈(평가금액)
	
	
	private Product_Server() {
		try {
			fundinfoList= new HashMap<Integer, Fund_InfoDTO>();
			Collections.synchronizedMap(fundinfoList);
			
			for (Fund_InfoDTO trd  : new Fund_InfoDAO().list() ) {
				fundinfoList.put(trd.getSeq(), trd);
			}
			
			list = new HashMap<String, ObjectOutputStream>();
			Collections.synchronizedMap(list);
			ServerSocket server = new ServerSocket(7778);
			System.out.println("서버시작");
			SelectDB s = new SelectDB();
			s.setDaemon(true);
			s.start();
			
		} catch (IOException e) {}
	}
	
	private static class Holder {
        public static final Product_Server Server = new Product_Server();
    }
	
	public static Product_Server getInstance() {
        return Holder.Server;
    }

	class SelectDB extends Thread{
		@Override
		public void run() {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			while(true) {
				try {
					sleep(10000);
					if(!sdf.format(today).equals(sdf.format(new Date() ) )  ) {
						
						new FundDAO().updatetoday((float) 0.5,new_nowmoney,new_exchange,changde_price_modify,"010-4794-6870-6563");
						new FundDAO().updateprice_modify("changde_price_modify","MCO글로벌인컴혼합자산투자신탁(H)[재간접형]종류A-Es");
						new FundDAO().list();
					}
					
					
					
				} catch (Exception e) {}
			}
		}
	}
}
