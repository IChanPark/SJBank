package product.deposit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Deposit.Deposits_infoDAO;

public class Detail implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data", Deposits_infoDAO.getInstance().list());
	}
}