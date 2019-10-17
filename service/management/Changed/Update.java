package management.Changed;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class Update  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserDTO dto = new UserDTO();
		HttpSession session = request.getSession();
		
		dto.setId((String)request.getSession().getAttribute("userID"));//1
		System.out.println((String)request.getSession().getAttribute("userID"));
		dto.setPw(request.getParameter("pw"));//2
		System.out.println(request.getParameter("pw"));
		
		dto.setTel(request.getParameter("tel"));//4
		dto.setGen(request.getParameter("gen"));//5		
		dto.setJob_group(request.getParameter("position"));//7
		dto.setAddr(request.getParameter("addr")+"/"+request.getParameter("datail"));//8		
		dto.setPostal_code(request.getParameter("zipcode"));//9
		
		//계좌번호 넣어주세요
		
		//넣어주세요
		request.getParameter("여기");
		UserDAO.getInstance().updateUser(dto);
		request.setAttribute("mainUrl", "main");
	}
}