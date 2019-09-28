package banking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import inf.Transfer.Transfer;
import jdbc.Account.AccountDAO;
import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;

public class Detail implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data", AccountDAO.getInstance().selectAccount("101-1112-112-1232"));
		request.setAttribute("log", Transfer_logDAO.getInstance().selectAN("101-1112-112-1232"));
		
		
	
		System.out.println("디테일 서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}