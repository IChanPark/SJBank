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
		
		System.out.println("들어왔어요23");
		String acc= request.getParameter("acc");
		String spw =request.getParameter("simple_pw");
		String pw = request.getParameter("pw");
		String alias= request.getParameter("alias");
		String status = request.getParameter("status");
		
		String id = (String)session.getAttribute("userID");
		String uid = request.getParameter("id");
		String upw = request.getParameter("upw");

		UserDTO dto = UserDAO.getInstance().selectId(id);
		System.out.println("뭐야 이상하잖아");
		if( !(uid.equals(dto.getId())&&  upw.equals(dto.getPw() )  )  )
		{
			request.setAttribute("msg", "아이디 비밀번호가 틀렸습니다.");
			throw new Exception();
		}
		
		AccountDAO.getInstance().updateAccByManag(pw, status, alias, acc);
		
		
		
	}
}