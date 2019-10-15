package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;

public class Login  implements M_Action{
   
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      UserDTO dto = UserDAO.getInstance().selectId(request.getParameter("id"));
      HttpSession session = request.getSession();
      String url = "layout/service/loginmain";
      
      if(dto != null && dto.getPw().equals(request.getParameter("pw")) && dto.getStatus().equals("활성")) {
         url = "main";
         session.setAttribute("userID", dto.getId());
         session.setMaxInactiveInterval(20*60);
         session.setAttribute("current_Page", "main");
      }
      
      request.setAttribute("mainUrl", url);
      request.setAttribute("data", dto);
   }
}