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

public class Accchange  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		System.out.println("들어왔어요");
		String acc= request.getParameter("acc");
		String spw =request.getParameter("simple_pw");
		String pw = request.getParameter("pw");
		String id = (String)session.getAttribute("userID");
		String alias= request.getParameter("alias");
		String status = request.getParameter("status");
		if(UserDAO.getInstance().chkSimplePw(id, spw))
		{
			AccountDAO.getInstance().updateAccByManag(pw, status, alias, acc);
		}
		
		
	}
}