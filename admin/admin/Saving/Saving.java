package admin.Saving;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;

import jdbc.Saving.Saving_infoDAO;
import jdbc.Saving.Saving_infoDTO;



public class Saving implements M_Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Saving_infoDTO> dto = Saving_infoDAO.getInstance().list();
		request.setAttribute("data", dto); 
		System.out.println(dto+"dto");
		
	}
}
