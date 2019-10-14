package admin.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Customer.Customer_faqDAO;
import jdbc.Customer.Customer_faqDTO;
import jdbc.Service.FaqDAO;
import jdbc.Service.FaqDTO;


public class FaqReg implements M_Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		FaqDTO dto = new FaqDTO();

		dto.setId((String)request.getSession().getAttribute("adminID"));
		dto.setTitle(request.getParameter("title"));
		dto.setType(request.getParameter("type"));
		dto.setContent(request.getParameter("content"));
		dto.setStatus("활성");

		//계좌번호 넣어주세요
		
		//넣어주세요

		//DB 저장
		FaqDAO.getInstance().insert(dto); 
		
		ArrayList<Customer_faqDTO> fto = Customer_faqDAO.getInstance().list();
		request.setAttribute("data", fto); 
		
		request.setAttribute("mainUrl","admin/service/faq");	
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
