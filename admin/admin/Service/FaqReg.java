package admin.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Service.FaqDAO;
import jdbc.Service.FaqDTO;


public class FaqReg implements M_Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		FaqDTO dto = new FaqDTO();

		dto.setId(request.getParameter("id"));
		dto.setTitle(request.getParameter("title"));
		dto.setType(request.getParameter("type"));
		dto.setContent(request.getParameter("content"));
		dto.setStatus("활성");
		
		
		//계좌번호 넣어주세요
		
		//넣어주세요

		//DB 저장
		FaqDAO.getInstance().insert(dto); 
		request.setAttribute("mainUrl","admin/inc/main");	
		System.out.println("노티스 Reg 들어옴");
		///redirect
//		request.setAttribute("msg","작성되었습니다." );
//		request.setAttribute("goUrl","Detail?id="+dto.getId());
//		request.setAttribute("mainUrl", "alert");
//		
//		request.setAttribute("goUrl", "admin.jsp");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp"); 
//		dispatcher.forward(request, response);

	}

}
