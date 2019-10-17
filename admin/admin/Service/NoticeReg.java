package admin.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Service.NoticeDAO;
import jdbc.Service.NoticeDTO;


public class NoticeReg implements M_Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		NoticeDTO dto = new NoticeDTO();

		dto.setId((String)request.getSession().getAttribute("adminID"));
		dto.setTitle(request.getParameter("title"));		
		dto.setContent(request.getParameter("content"));
		dto.setStatus("활성");
		//계좌번호 넣어주세요
		
		//넣어주세요

		//DB 저장
		NoticeDAO.getInstance().insert(dto); 
		
		ArrayList<NoticeDTO> nto = NoticeDAO.getInstance().list();
		request.setAttribute("data", nto); 
		
		request.setAttribute("mainUrl","admin/service/notice");	
		System.out.println("노티스 Reg 들어옴");
		///redirect
//		request.setAttribute("msg","작성되었습니다." );
//		request.setAttribute("goUrl","Detail?id="+dto.getId());
//		request.setAttribute("mainUrl", "alert");
//		
//		request.setAttribute("goUrl", "SJAdmin");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp"); 
//		dispatcher.forward(request, response);

	}

}
