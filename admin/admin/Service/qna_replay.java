package admin.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Service.QnaDAO;
import jdbc.Service.QnaDTO;


public class qna_replay  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDTO dto = QnaDAO.getInstance().selectSeq(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("data", dto); 
		
	}
}
