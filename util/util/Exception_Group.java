package util;

import java.util.ArrayList;

public class Exception_Group {
	private ArrayList<String> nonClass = null;
	
	private Exception_Group() {
		nonClass = new ArrayList<String>();
		nonClass.add("service.LoginMain");
		nonClass.add("service.Join");
	}
	
	private static class Holder {
        public static final Exception_Group DAO = new Exception_Group();
    }
	
	public static Exception_Group getInstance() {
        return Holder.DAO;
    }
	
	public boolean check(String service) {
		return !nonClass.contains(service);
	}
}
