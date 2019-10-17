package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;

public class Datainput {

	public static void main(String[] args) throws ParseException {
		
		String	account_number = "010-1111-1111-122",
				to_account_number ="010-1111-1111-161",
				receive ="박이찬",
				register_date ="";
			
		
		long 	sum = 0;
			int 	fee = 0;
				
		String [] feetype = {"송금", "펀드수수료", "예약이체"},
				  target = {"SJ은행","하나은행","신한은행"};

		
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		   
		 Date date = null;
		try {
			date = df.parse("2011-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		         
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		        
		         
		System.err.println(df.format(cal.getTime()));
		        
		for (int i = 2015; i < 2019; i++) {
			int aaa = ((int)Math.random() * 1000)+10; 
			int bbb = ((int)Math.random() * 100)+1; 
			for (int j = 1; j < 12; j++) {
				
				for (int j2 = 1; j2 < aaa; j2++) {
					
					int t = ((int)Math.random() * 3);
					int f = ((int)Math.random() * 3);
					sum = ((long)Math.random() * 110000000)+1000;
					fee = (int) ((int)sum * 0.01);
					
					Transfer_logDAO.getInstance().insert(account_number, feetype[t], target[f], to_account_number,
							receive, sum, fee, df.format(cal.getTime()));
					cal.add(Calendar.DATE, 1);
				}
			}
		}
		
		
	}
}
