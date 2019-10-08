package banking.transfer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;

public class autoReg implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data", AccountDAO.getInstance().selectID("elliottjo"));
		System.out.println("연산귀찮아서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
		
		System.out.println("잠깐 확인 하고 갑니다 auto REG");
		System.out.println(request.getParameter("acc")+  " 입력계좌");
		System.out.println(request.getParameter("toAcc")+  " 받는계좌");
		System.out.println(request.getParameter("start")+  " 입력계좌");
		
		
		
	}

}