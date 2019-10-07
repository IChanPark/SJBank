package util;

import java.util.ArrayList;

public class Exception_Group {
	private ArrayList<String> nonClass;
	private String [] group = {
			"service.LoginMain", "service.Join","banking.Confirm",
			"security.Otp","security.otp.Register","security.Accaign",
			"security.Delaytrs","security.Blockalarm","management.user.check",
			"management.User","security.otp.Apply","Security","Banking",
			"service.SearchID","service.SearchPW","service.client.Faq",
			"product.Deposit"
			
	};	//여짝에 추가해주세용
	private Exception_Group() {
		nonClass = new ArrayList<String>();
		for (String e : group) 
			nonClass.add(e);
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