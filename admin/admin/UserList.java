package admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class UserList  implements M_Action{
	   
	   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      ArrayList<UserDTO> dto = UserDAO.getInstance().list();
	      request.setAttribute("data", dto); 
	}
}
