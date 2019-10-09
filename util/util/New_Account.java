package util;

import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;

public class New_Account {
	private String acc = null;
	private AccountDTO dto =null;
	
	private New_Account() {
		
	}
	
	private static class Holder {
        public static final New_Account DAO = new New_Account();
    }
	
	public static New_Account getInstance() {
        return Holder.DAO;
    }
	
	
	public String getAccount() {
		newAccount(acc);
		
		return acc;
	}
	
	private void newAccount(String acc) {

		acc ="010-"+((int)(Math.random()*9999)+1)+"-"+
			String.format("%04d", (int)((Math.random()*9999)+1))+"-"+
			String.format("%04d", (int)((Math.random()*9999)+1));
		
		dto = AccountDAO.getInstance().selectAccount(acc);
		
		if(dto!=null)
			newAccount(acc);
	}
}