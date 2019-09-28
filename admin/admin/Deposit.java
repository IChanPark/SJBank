package admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_infoDTO;

public class Deposit  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Deposits_infoDTO> dto = Deposits_infoDAO.getInstance().list();
		request.setAttribute("data", dto); 
	}
}
