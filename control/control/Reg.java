package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;

@WebServlet("/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getParameter("page") != null) {
				String service = ""; 
				String url = request.getParameter("page");
				for (String s : url.split("/")) 
					service+=s+".";
				
				url = "layout/"+url;
				service = service.substring(0, service.length()-1);

				request.setAttribute("mainUrl", url); //template에서 포워딩할 주소 세팅

				ArrayList<String> nonCla = new ArrayList<String>(); //M_Action 실행안할 리트스
				System.out.println("url : "+url);
				System.out.println("service : "+service);
				if(!nonCla.contains(service)) { // 속하지 않는다면 실행
					M_Action action = (M_Action)(Class.forName(service).newInstance());
					action.execute(request, response);
				}
			}
			response.sendRedirect("index.jsp");
		} catch (Exception e) {e.printStackTrace();} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
