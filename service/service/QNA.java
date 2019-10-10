package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Service.QnaDAO;
import jdbc.Service.QnaDTO;


public class QNA  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<QnaDTO> dto = QnaDAO.getInstance().list();
		request.setAttribute("data", dto); 
		
	}
}
