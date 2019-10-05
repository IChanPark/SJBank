package security.delaytrs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Service.ServiceDAO;
import jdbc.Service.ServiceDTO;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class Complete implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		
		
		UserDTO udto = UserDAO.getInstance().selectId( (String)session.getAttribute("userID"));
		
		System.out.println(udto+" udto입니다.");
		
		ServiceDTO dto = new ServiceDTO();
		dto.setId(udto.getId());
		dto.setName(udto.getName());
		dto.setType("delay");
		dto.setStatus("활성"); 
		dto.setOption(request.getParameter("delayTime"));
	
		ServiceDAO.getInstance().insert(dto);
		
				
		System.out.println("Delay 등록 완료 서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}