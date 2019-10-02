package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;

@WebServlet("/admin.jsp")
public class Admin_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");	//한글처리
			HttpSession session = request.getSession();
			
			if(request.getParameter("hid_t") != null) {
				String service = ""; 
				String url = request.getParameter("hid_t");
				
				for (String s : url.split("/")) 
					service+=s+".";

				url = url.toLowerCase();
				service = service.substring(0, service.length()-1);

				request.setAttribute("mainUrl", url); //template에서 포워딩할 주소 세팅
			
				System.out.println("url : "+url);
				System.out.println("service : "+service);
				if(admin.Util.Exception_Group.getInstance().check(service)) { // 속하지 않는다면 실행
					M_Action action = (M_Action)(Class.forName(service).newInstance());
					action.execute(request, response);
				}
			} else
				request.setAttribute("mainUrl", "admin/inc/main");
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("template_Admin.jsp"); //여기로 보내
			dispatcher.forward(request, response);
		} catch (Exception e) {e.printStackTrace();} 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}