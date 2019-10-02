package admin.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Customer.Customer_faqDAO;
import jdbc.Customer.Customer_faqDTO;


public class FAQ  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Customer_faqDTO> dto = Customer_faqDAO.getInstance().list();
		request.setAttribute("data", dto); 
		
	}
}
