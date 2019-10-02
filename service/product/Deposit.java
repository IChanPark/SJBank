package product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_typeDAO;
import jdbc.Saving.Saving_infoDAO;
import jdbc.Saving.Saving_typeDAO;

public class Deposit  implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("data_dp", Deposits_infoDAO.getInstance().list());
		request.setAttribute("data_sv", Saving_infoDAO.getInstance().list());
		request.setAttribute("type_dp", Deposits_typeDAO.getInstance().list());
		request.setAttribute("type_sv", Saving_typeDAO.getInstance().list());
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}
}


