package management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;

public class Accupdate  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

			
		AccountDTO  dto = AccountDAO.getInstance().selectAccount(request.getParameter("acc"));
		
		request.setAttribute("data", dto);
		
	}
}