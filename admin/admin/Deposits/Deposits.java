package admin.Deposits;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import inf.M_Action;
import jdbc.Deposit.Deposits_typeDAO;

public class Deposits  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Gson gson = new Gson();
		request.setAttribute("type_dp", gson.toJson(Deposits_typeDAO.getInstance().list()));
	}
}
