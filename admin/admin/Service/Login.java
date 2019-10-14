package admin.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Management.ManagementDAO;
import jdbc.Management.ManagementDTO;

public class Login  implements M_Action{
   
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ManagementDTO dto = ManagementDAO.getInstance().selectId(request.getParameter("id"));
      HttpSession session = request.getSession();
      String url = "admin/service/loginmain";
      
      if(dto != null && dto.getPw().equals(request.getParameter("pw"))) {
         url = "admin/inc/main";
         session.setAttribute("adminID", dto.getId());
         session.setMaxInactiveInterval(20*60);
         session.setAttribute("current_Page", "admin/inc/main");
      }
      
      request.setAttribute("mainUrl", url);
      request.setAttribute("data", dto);
   }
}