package server;

import jdbc.Transfer.Transfer_delayDTO;
import jdbc.Transfer.Transfer_reserveDTO;
import jmodels.Transfer_delayDAO;
import jmodels.Transfer_reserveDAO;

public class Test2 {
	public static void main(String[] args) {
				
		Acc_Member_Data amd = new Acc_Member_Data();
    	String name = "추가";	//web 기준 
    	Transfer_reserveDTO dto = Transfer_reserveDAO.getInstance().selectSeq("1");
    	System.out.println(dto);
    	Acc_Member aa = new Acc_Member(name);
    	System.out.println(Access_IP.getInstance().getIP());
    	try {
    		amd.name = name;
    		amd.target = "DB";
    		amd.data=dto;
    		amd.msg ="reserve"; 
    		aa.dos.writeObject(amd);
    		aa.dos.flush();
    		aa.dos.reset();
    	} catch (Exception e2) {}
	}
}