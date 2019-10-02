package admin.Loan;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Loan.Loan_InfoDAO;
import jdbc.Loan.Loan_InfoDTO;

public class Loan  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Loan_InfoDTO> dto = Loan_InfoDAO.getInstance().list();
		request.setAttribute("data", dto); 
		
	}
}
