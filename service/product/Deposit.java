package product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Deposit.Deposits_infoDAO;

public class Deposit  implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data", Deposits_infoDAO.getInstance().list());
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}
}


