package banking.transfer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;

public class Reservation implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data", AccountDAO.getInstance().selectIDfromUsable((String)request.getSession().getAttribute("userID")));
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}