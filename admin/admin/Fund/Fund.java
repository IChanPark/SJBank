package admin.Fund;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Fund.Fund_InfoDAO;
import jdbc.Fund.Fund_InfoDTO;



public class Fund implements M_Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Fund_InfoDTO> dto = Fund_InfoDAO.getInstance().list();
		request.setAttribute("data", dto); 
		
		
	}
}
