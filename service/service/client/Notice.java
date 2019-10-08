package service.client;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Service.NoticeDAO;
import jdbc.Service.NoticeDTO;


public class Notice  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<NoticeDTO> dto = NoticeDAO.getInstance().list();
		request.setAttribute("data", dto); 
		for (NoticeDTO dd : dto) {
			System.out.println(dd);
		}
	
	}
}
