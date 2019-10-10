package server;

public class send {
	
	public send(String name,String type, Object Sdata) {
		// TODO Auto-generated constructor stub
		
		Acc_Member_Data data = new Acc_Member_Data();
    	Acc_Member aa = new Acc_Member(name);
    	System.out.println(Access_IP.getInstance().getIP());
    	try {
    		data.name = name;
    		data.target = "DB";
    		data.msg =type;
    		data.data=Sdata;
    		aa.dos.writeObject(data);
    		aa.dos.flush();
    		aa.dos.reset();
    	} catch (Exception e2) {}
	}
}