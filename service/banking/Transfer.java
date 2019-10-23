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
		
		ArrayList<AccountDTO>  dto=  AccountDAO.getInstance().selectIDfromUsable(id);
		
		request.setAttribute("data", dto);
		System.out.println(dto);
	}
}
