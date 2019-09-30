package management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class change  implements M_Action{

		public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			UserDTO dto = UserDAO.getInstance().selectId((String)request.getSession().getAttribute("userID"));
			
			System.out.println(dto.toString());
			
			request.setAttribute("data", dto);
		}
	}