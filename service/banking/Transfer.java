package banking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;

public class Transfer implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("트랜스퍼");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}