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

public class Accmanag  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("들어왔어요");
		
		HttpSession session = request.getSession();
		
		ArrayList<AccountDTO>  dto = AccountDAO.getInstance().selectID((String)session.getAttribute("userID"));

		System.out.println(dto.toString());
		request.setAttribute("dd", "abc");

		request.setAttribute("data", dto);
	}
}