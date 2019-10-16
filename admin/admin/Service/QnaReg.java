package admin.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Service.QnaDAO;
import jdbc.Service.QnaDTO;


public class QnaReg implements M_Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		
		QnaDTO dto = new QnaDTO();
		System.out.println("gid:"+request.getParameter("gid"));
		String d = "[답변]";
		
		dto.setGid(Integer.parseInt(request.getParameter("gid")));
		dto.setLev(Integer.parseInt(request.getParameter("lev")));
		dto.setSeq(Integer.parseInt(request.getParameter("rseq")));
		dto.setType(request.getParameter("type"));
		dto.setTitle(d+request.getParameter("title"));
		dto.setName(request.getParameter("name"));
		dto.setContent(request.getParameter("content"));
	//	dto.setRseq(Integer);
		dto.setStatus("활성");
		System.out.println(request.getParameter("Register_dateStr"));
		//dto.setRegister_dateStr(request.getParameter("Register_dateStr"));
		//계좌번호 넣어주세요
		
		//넣어주세요

		//DB 저장
		QnaDAO.getInstance().insert(dto); 
		
		ArrayList<QnaDTO> qto = QnaDAO.getInstance().list();
		request.setAttribute("data", qto); 		
		
		request.setAttribute("mainUrl","admin/service/qna");	
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
