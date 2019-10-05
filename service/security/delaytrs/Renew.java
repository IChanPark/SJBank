package security.delaytrs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Service.ServiceDAO;

public class Renew implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userID");
		System.out.println(request.getParameter("delayTime"));
		ServiceDAO.getInstance().update(id,"delay","활성",request.getParameter("delayTime"));
		System.out.println("delay 갱신 서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}