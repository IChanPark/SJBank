package management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class User  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("들어왔어요");
		
		UserDTO dto = UserDAO.getInstance().selectId((String)request.getSession().getAttribute("userID"));

		System.out.println(dto.toString());

		request.setAttribute("data", dto);
	}
}