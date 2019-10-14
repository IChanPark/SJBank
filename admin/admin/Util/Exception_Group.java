package admin.Util;

import java.util.ArrayList;

public class Exception_Group {
	private ArrayList<String> nonClass;
	private String [] group = {
			"admin.Deposits.DepositsAdd","admin.Fund.FundAdd","admin.Saving.SavingAdd",
			"admin.Loan.LoanAdd","admin.Block.Block","admin.Block.Userblock",
			"admin.Server.Server","admin.service.noticeinsert","admin.service.mailform",
			"admin.service.faq_insert", "admin.Calc.Calc", "admin.service.qna_replay",
			"admin.Product.Deposits","admin.Product.Saving", "admin.Product.Fund",
			"admin.Service.LoginMain"
	};
	//여짝에 추가해주세용
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