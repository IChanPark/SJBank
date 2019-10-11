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

		System.out.println(request.getParameter("pw")+"업데이트 입니다?");
		System.out.println(request.getParameter("alias")+"업데이트 입니다?");
		System.out.println(request.getParameter("acc")+"어카운트 입니다?");
		
		HttpSession session = request.getSession();
		
		ArrayList<AccountDTO>  dto = AccountDAO.getInstance().selectID((String)session.getAttribute("userID"));

		System.out.println(dto.toString());
		request.setAttribute("dd", "abc");

		request.setAttribute("data", dto);
	}
}