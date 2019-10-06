package server;

public class Test2 {
	public static void main(String[] args) {
		
		
		Acc_Member_Data data = new Acc_Member_Data();
    	String name = "WEB";	//web 기준 
    	
    	Acc_Member aa = new Acc_Member(name);
    	
    	try {
    		data.name = name;
    		data.target = "DB";
    		data.msg ="gdgd";
    		aa.dos.writeObject(data);
    		aa.dos.flush();
    		aa.dos.reset();
    	} catch (Exception e2) {}
	}

}