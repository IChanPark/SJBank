package management;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class Accupdate  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(request.getParameter("acc")+"어카운트 업데이트 입니다?");
		
		HttpSession session = request.getSession();
		
		AccountDTO  dto = AccountDAO.getInstance().selectAccount(request.getParameter("acc"));
		
		request.setAttribute("data", dto);
		
	}
}