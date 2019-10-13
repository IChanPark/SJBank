package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import util.Exception_Group;
import util.Exception_Menu;

@WebServlet("/SJBank")
public class User_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");	//한글처리
			HttpSession session = request.getSession();
			String param = request.getParameter("hid_t");
			
			if(!(param == null || param.equals(""))) {
				String service = ""; 
				String url = request.getParameter("hid_t");   
				session.setAttribute("accountNumber", request.getParameter("accountNumber"));
				for (String s : url.split("/")) 
					service+=s+".";
				url = "layout/"+url.toLowerCase();
				service = service.substring(0, service.length()-1);
				System.out.println("url : "+url+"\n"+"service : "+service);
				if(session.getAttribute("userID")==null && Exception_Menu.getInstance().check(url)) {
					request.setAttribute("msg", "로그인이 필요한 서비스 입니다.");
					request.setAttribute("goUrl", "SJBank");
					RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp"); 
					dispatcher.forward(request, response);
					return;
				}
				session.setAttribute("Previous_page", session.getAttribute("Current_Page"));   //이전페이지
				session.setAttribute("Current_Page", url);      //현재페이지
				request.setAttribute("mainUrl", url); //template에서 포워딩할 주소 세팅
				if(Exception_Group.getInstance().check(service)) { // 클래스 예외처리 여부
					M_Action action = (M_Action)(Class.forName(service).newInstance());
					action.execute(request, response);
				}
				System.out.println("정상");
			} else if(session.getAttribute("Previous_page") != null &&
					session.getAttribute("userID")!=null) 
				{
					request.setAttribute("mainUrl", "main");
					System.out.println("이전");
				}
				else {
					request.setAttribute("mainUrl", "main");
					System.out.println("비정상");
				}
			System.out.println("최종");
			RequestDispatcher dispatcher = request.getRequestDispatcher("template.jsp"); //여기로 보내
			dispatcher.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp"); 
			dispatcher.forward(request, response);
		
		} 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}