package management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.User.UserDAO;

public class Accchange  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		System.out.println("들어왔어요");
		String acc= request.getParameter("acc");
		String upw =request.getParameter("user_pw");
		String pw = request.getParameter("pw");
		String id = (String)session.getAttribute("userID");
		String alias= request.getParameter("alias");
		String status = request.getParameter("status");
		if(UserDAO.getInstance().chkSimplePw(id, upw))
		{
			AccountDAO.getInstance().updateAccByManag(pw, status, alias, acc);
		}
		else
		{
			request.setAttribute("msg", "사용자비밀번호가 일치하지 않습니다...ByServelet");
			request.setAttribute("goUrl", "SJBank");
			request.setAttribute("mainUrl", "main");
			throw new Exception("패스워드 불일치!!!");
		}
		
		
	}
}