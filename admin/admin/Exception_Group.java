package admin;

import java.util.ArrayList;

public class Exception_Group {
	private ArrayList<String> nonClass;
	private String [] group = {
			"admin.DepositAdd" };	//여짝에 추가해주세용
	private Exception_Group() {
		nonClass = new ArrayList<String>();
		for (String e : group) 
			nonClass.add(e);
	}
	
	private static class Holder {
        public static final Exception_Group Exception = new Exception_Group();
    }
	
	public static Exception_Group getInstance() {
        return Holder.Exception;
    }
	
	public boolean check(String service) {
		return !nonClass.contains(service);
	}
}