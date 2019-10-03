package product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import inf.M_Action;
import jdbc.Deposit.Deposits_typeDAO;
import jdbc.Saving.Saving_typeDAO;

public class Deposit  implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Gson gson = new Gson();
		request.setAttribute("type_dp", gson.toJson(Deposits_typeDAO.getInstance().list()));
		request.setAttribute("type_sv", gson.toJson(Saving_typeDAO.getInstance().list()));
		// Json 타입으로 넘김
	}
}