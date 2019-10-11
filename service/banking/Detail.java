package banking;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;

public class Detail implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  HttpSession session = request.getSession();
		request.setAttribute("data", AccountDAO.getInstance().selectAccount( (String)session.getAttribute("accountNumber")  )  );
		request.setAttribute("log", Transfer_logDAO.getInstance().selectAN((String)session.getAttribute("accountNumber")  ));
		request.setAttribute("inputLog", Transfer_logDAO.getInstance().selectToAN((String)session.getAttribute("accountNumber")  ));
		request.setAttribute("IO", Transfer_logDAO.getInstance().selectIOAN((String)session.getAttribute("accountNumber")  ));
		
		System.out.println("계좌번호  " + (String)session.getAttribute("accountNumber") );
		
		
		System.out.println("디테일 서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}