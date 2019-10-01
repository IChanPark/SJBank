package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Deposit.Deposits_infoDTO;

public class Deposit  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		Deposits_infoDTO dto = new Deposits_infoDTO();
		request.setAttribute("data", dto); 
	}
}
