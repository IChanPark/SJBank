package banking;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.Deposit.DepositsDAO;
import jdbc.Deposit.DepositsDTO;

public class Transfer  implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("userID");
		
		HashMap<String,AccountDTO> MapAd = new HashMap<String, AccountDTO>();
		
		ArrayList<AccountDTO> alad = AccountDAO.getInstance().selectIDfromUsable(id);
		
		for (AccountDTO dto : alad) {
			MapAd.put(dto.getAccount_number(), dto);
		}
		
		ArrayList<DepositsDTO> untrs = DepositsDAO.getInstance().UnTrsAccount(id);
		System.out.println(id +"id에요");
		System.out.println(untrs +"어레이에요");
		for (DepositsDTO dto : untrs) {
			System.out.println(dto.getAccount_number() + " 제거대상");
			MapAd.remove(dto.getAccount_number());
		}
		
		alad.clear();
		
		for (AccountDTO dto : MapAd.values()) {
			alad.add(dto);
		}
		
		request.setAttribute("data", alad);
	}
}
