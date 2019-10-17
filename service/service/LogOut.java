package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;

public class LogOut  implements M_Action{
   
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession session = request.getSession();
      //session.invalidate();
      session.removeAttribute("userID");
      request.setAttribute("mainUrl", "main");
   }
}