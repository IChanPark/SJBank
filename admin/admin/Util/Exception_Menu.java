package admin.Util;

import java.util.ArrayList;

public class Exception_Menu {
	private ArrayList<String> nonClass;
	private String [] group = {
			"admin/service/loginmain", "admin/service/login", "admin/service/logout"
			//메뉴 예외처리
	};	
	private Exception_Menu() {
		nonClass = new ArrayList<String>();
		for (String e : group) 
			nonClass.add(e);
	}
	
	private static class Holder {
        public static final Exception_Menu DAO = new Exception_Menu();
    }
	
	public static Exception_Menu getInstance() {
        return Holder.DAO;
    }
	
	public boolean check(String service) {
		boolean chk = true;
		for (String s : nonClass) {
			if(service.contains(s))
				chk = false;
		}
		return chk;
	}
}