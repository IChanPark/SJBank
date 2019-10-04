package security.delaytrs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;

public class Complete implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		System.out.println(request.getParameter("dalayTime"));
		
		AccountDAO.getInstance().updateAccType( (String)session.getAttribute("userID"),request.getParameter("dalayTime") );
		
		System.out.println("Delay 등록 완료 서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}