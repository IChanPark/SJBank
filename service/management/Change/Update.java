package management.Change;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class Update  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserDTO dto = new UserDTO();
		
		dto.setId((String)request.getSession().getAttribute("userID"));
		dto.setPw(request.getParameter("pw"));
		dto.setSimple_pw(Integer.parseInt(request.getParameter("simple_pw")));
		dto.setTel(request.getParameter("tel"));
		dto.setGen(request.getParameter("gen"));
		dto.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));		
		dto.setJob_group(request.getParameter("position"));
		dto.setAddr(request.getParameter("addr"));		
		dto.setPostal_code(request.getParameter("zipcode"));

		//계좌번호 넣어주세요
		
		//넣어주세요
		
		UserDAO.getInstance().updateUser(dto);
		request.setAttribute("mainUrl", "main");

	}
}