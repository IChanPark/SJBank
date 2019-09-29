package security.Blockalarm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;

public class Management implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data", AccountDAO.getInstance().selectID("elliottjo"));
		System.out.println("서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}