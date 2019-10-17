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
import jdbc.Transfer.Transfer_logDTO;
import jmodels.AccountDAO;
import jmodels.Transfer_logDAO;
import proddepo.DepositsDAO;
import proddepo.Deposits_logDAO;
import prodfund.FundDAO;
import prodfund.Fund_InfoDAO;
import prodfund.Fund_LogDAO;
import prodsaving.SavingDAO;
import prodsaving.Saving_logDAO;

public class Product_Server {
	private HashMap<String, ObjectOutputStream> list = null;

	private	ArrayList<FundDTO>  fdlog = new  FundDAO().uplist();
	private Fund_LogDTO fdloginsert = new Fund_LogDTO();
	
	private	ArrayList<DepositsDTO>  deplog = new DepositsDAO().uplist();
	private Deposits_logDTO deploginsert = new Deposits_logDTO();
	
	
	private	ArrayList<SavingDTO>  savlog = new  SavingDAO().uplist();
	private Saving_logDTO savloginsert = new Saving_logDTO();
	
	private	Transfer_logDTO transloginsert = new Transfer_logDTO();
	
	private Date today = new Date();


	private Product_Server() {
		try {
			

			 //fdlog = new  FundDAO().uplist();
			//System.out.println("here");

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
					sleep(3000);
					System.out.println("돈다");
					
					for (SavingDTO ss : savlog) {
						System.out.println("적금 시작~~!!!");
						savloginsert.setAccount_number(ss.getAccount_number());
						savloginsert.setStatus("성공");
						 AccountDTO acr = new AccountDAO().selectAccount(ss.getAccount_number());
						 
						
						
						 int  add_preferential= (int) ((acr.getSum()* ss.getInterest())/12);
		
						 savloginsert.setSum(acr.getSum()+add_preferential);
						 savloginsert.setInterest(ss.getInterest());
						new  AccountDAO().updateMoney(savloginsert);
						
						new Saving_logDAO().insert(savloginsert);
						
						transloginsert.setAccount_number("777-77777-7777");
						transloginsert.setFeetype("입금");
						transloginsert.setTarget("sj은행");
						transloginsert.setTo_account_number(ss.getAccount_number());
						transloginsert.setReceived(ss.getId());
						transloginsert.setSum((long)add_preferential);
						transloginsert.setFee(0);
						transloginsert.setStatus("성공");
						
						new  Transfer_logDAO().insert(transloginsert);
						 System.out.println("적금 성공~~!!!");
						savlog.remove(ss);
					}
					
					
					System.out.println("디포짓정보:"+deplog);
					for (DepositsDTO dd : deplog) {
						System.out.println("디포짓 시작~~!!!");
						System.out.println("depsit "+dd);
						deploginsert.setAccount_number(dd.getAccount_number());
						deploginsert.setStatus("성공");
						 AccountDTO dacr = new AccountDAO().selectAccount(dd.getAccount_number());
						System.out.println("dacr: "+dacr);
						 System.out.println(dacr.getSum());
						 int  add_preferential= (int)((dacr.getSum()*dd.getInterest())/12);
						 
						 	
						deploginsert.setSum(dacr.getSum()+add_preferential);
						deploginsert.setInterest(dd.getInterest());
						
						new  AccountDAO().updateMoney(deploginsert);
						
						new Deposits_logDAO().insert(deploginsert);
						
						deplog.remove(dd);
						System.out.println(dd);
						System.out.println(deplog);
						
						
//						transloginsert.setAccount_number("777-77777-7777");
//						transloginsert.setFeetype("이자입금");
//						transloginsert.setTarget("sj은행");
//						transloginsert.setTo_account_number(dd.getAccount_number());
//						transloginsert.setReceived(dd.getId());
//						transloginsert.setSum((long)add_preferential);
//						transloginsert.setFee(0);
//						transloginsert.setStatus("성공");
//						
//						new  Transfer_logDAO().insert(transloginsert);
						
						 System.out.println("디포짓 성공~~!!!");
						
						
					}
					
					System.out.println(fdlog +" array what");
					for (FundDTO ff : fdlog) {
							System.out.println(ff+"     ff what");
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
							 fdlog.remove(ff);
				
					}
					if(savlog.size()<=0) {
						savlog = new  SavingDAO().uplist();
						System.out.println("도2323니");
					}
					if(savlog.size()<=0) {
						savlog = null;
						System.out.println("도니");
					}
					if(deplog.size()<=0) {
						deplog = new  DepositsDAO().uplist();
					}
					if(fdlog.size()<=0) {
						fdlog = new  FundDAO().uplist();
					}
				} catch (Exception e) {
				}
			}
		}
	}
}
