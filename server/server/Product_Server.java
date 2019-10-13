package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import jdbc.Account.AccountDTO;
import jdbc.Deposit.DepositsDTO;
import jdbc.Deposit.Deposits_logDTO;
import jdbc.Fund.FundDTO;
import jdbc.Fund.Fund_InfoDTO;
import jdbc.Fund.Fund_LogDTO;
import jdbc.Saving.SavingDTO;
import jdbc.Saving.Saving_logDTO;
import jmodels.AccountDAO;
import proddepo.DepositsDAO;
import proddepo.Deposits_logDAO;
import prodfund.FundDAO;
import prodfund.Fund_InfoDAO;
import prodfund.Fund_LogDAO;
import prodsaving.SavingDAO;
import prodsaving.Saving_logDAO;

public class Product_Server {
	private HashMap<String, ObjectOutputStream> list = null;
	private HashMap<Integer, Fund_InfoDTO> fundinfoList = null;
	private	ArrayList<FundDTO>  fdlog = new  FundDAO().uplist();
	private Fund_LogDTO fdloginsert = new Fund_LogDTO();
	
	private	ArrayList<DepositsDTO>  deplog = new  DepositsDAO().uplist();
	private Deposits_logDTO deploginsert = new Deposits_logDTO();
	
	private	ArrayList<SavingDTO>  savlog = new  SavingDAO().uplist();
	private Saving_logDTO savloginsert = new Saving_logDTO();
	
	private Date today = new Date();


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

			//SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (true) {
				try {
					sleep(1000);
					System.out.println("돈다");
					
					for (SavingDTO ss : savlog) {
						savloginsert.setAccount_number(ss.getAccount_number());
						savloginsert.setStatus("성공");
						 AccountDTO acr = new AccountDAO().selectAccount(ss.getAccount_number());
						
						 int  add_preferential= (int) ((acr.getSum()*ss.getInterest())/12);
		
						 savloginsert.setSum(acr.getSum()+add_preferential);
						
						new  AccountDAO().updateMoney(savloginsert);
						
						new Saving_logDAO().insert(savloginsert);
					}
					
					for (DepositsDTO dd : deplog) {
						deploginsert.setAccount_number(dd.getAccount_number());
						deploginsert.setStatus("성공");
						 AccountDTO acr = new AccountDAO().selectAccount(dd.getAccount_number());
						
						 int  add_preferential= (int) ((acr.getSum()*dd.getInterest())/12);
		
						deploginsert.setSum(acr.getSum()+add_preferential);
						
						new  AccountDAO().updateMoney(deploginsert);
						
						new Deposits_logDAO().insert(deploginsert);
					}
					
					
					for (FundDTO ff : fdlog) {
	
							fdloginsert.setAccount_number(ff.getAccount_number());
							fdloginsert.setStatus("성공");
				
							int random = (int) ((Math.random() * 99) + 1);
							int pmr = (int) ((Math.random() * 99) + 1);
							if(pmr%2==0) {
								random=random * -1;
							}
			
							Float changde_price_modify = ff.getPrice_modify() + random;
							  float new_exchange = ff.getBuynum() * changde_price_modify; // 구매한량 * 현재가격(평가금액)
							  float new_nowmoney = new_exchange + ff.getRest();
				
							 fdloginsert.setFluctuation((float)0.0);
							 fdloginsert.setSum((int)new_nowmoney);
							 String string_changde_price_modify= ff.getPrice_modify() + random+"";
							 
							new FundDAO().updatetoday((float) 0.5, new_nowmoney,new_exchange, string_changde_price_modify,
									ff.getAccount_number());
							new Fund_InfoDAO().updateprice_modify(string_changde_price_modify,
									ff.getProduct());
							new Fund_LogDAO().insert(fdloginsert);
							
							 System.out.println("펀드 성공~~!!!");
				
					}

				} catch (Exception e) {
				}
			}
		}
	}
}
