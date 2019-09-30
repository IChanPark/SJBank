package security.otp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Security.OTPDAO;

public class Inactive implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		OTPDAO.getInstance().UpdateToInactive( (String)session.getAttribute("userID") );
		
		System.out.println("서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}