package server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import jmodels.Transfer_delayDAO;

public class Test2 {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(new Date());
		System.out.println("--------------------");
		System.out.println(sdf.format(new Date())); 
		
		Calendar cd = Calendar.getInstance();

		Calendar cd2 = Calendar.getInstance();
		
		cd2.set(2030,5, 20);
		
		ArrayList<Date> ad = new ArrayList<Date>();
		ArrayList<String> as = new ArrayList<String>();
		
		System.out.println( sdf.format(new Date()) ); 
		
		while(cd.before(cd2))
		{
			cd.add(Calendar.MONTH, 1);
			ad.add(cd.getTime());
			as.add(sdf.format(cd.getTime()) );
		}
		String str= as.toString();
		
		Acc_Member_Data data = new Acc_Member_Data();
    	String name = "추가";	//web 기준 
    	
    	Acc_Member aa = new Acc_Member(name);
    	System.out.println(Access_IP.getInstance().getIP());
    	try {
    		data.name = name;
    		data.target = "DB";
    		data.data=Transfer_delayDAO.getInstance().selectSeq("1");
    		data.msg ="delay";
    		aa.dos.writeObject(data);
    		aa.dos.flush();
    		aa.dos.reset();
    	} catch (Exception e2) {}
	}
}