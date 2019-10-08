package management.Changed;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class Update  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserDTO dto = new UserDTO();
		
		dto.setId((String)request.getSession().getAttribute("userID"));//1
		dto.setPw(request.getParameter("pw"));//2
		dto.setSimple_pw(Integer.parseInt(request.getParameter("simple_pw")));//3
		dto.setTel(request.getParameter("tel"));//4
		dto.setGen(request.getParameter("gen"));//5
		dto.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));//6		
		dto.setJob_group(request.getParameter("position"));//7
		dto.setAddr(request.getParameter("addr")+"/"+request.getParameter("datail"));//8		
		dto.setPostal_code(Integer.parseInt(request.getParameter("zipcode")));//9
		
		//계좌번호 넣어주세요
		
		//넣어주세요
		
		UserDAO.getInstance().updateUser(dto);
		request.setAttribute("mainUrl", "main");

	}
}