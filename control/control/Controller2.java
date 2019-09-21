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
import inf.initData;

//@WebServlet({	"/main/*", "/check/*", "/transfer/*", "/product/*", "/utlity/*",
//	 			"/security/*", "/usermanagement/*", "/customer/*"		})
@WebServlet("/aaaandex.jsp")
public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			new initData(request);
			String type = (String)request.getAttribute("type");		//대분류
			String cate = (String)request.getAttribute("cate");		//중분류
			String main = (String)request.getAttribute("main");		//소분류
			String service = type+"."+cate+"."+main;
			String url = "layout/"+type+"/"+cate+"/"+main;
			
			request.setAttribute("mainUrl", url); //template에서 포워딩할 주소 세팅
			
			ArrayList<String> nonCla = new ArrayList<String>(); //M_Action 실행안할 리트스
			
			System.out.println("service"+": "+service);
			if(!nonCla.contains(service)) { // 속하지 않는다면 실행
				M_Action action = (M_Action)(Class.forName(service).newInstance());
				action.execute(request, response);
			}
			request.setAttribute("goUrl","../index.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../../layout/move.jsp"); //여기로 보내
//			RequestDispatcher dispatcher = request.getRequestDispatcher("../../index.jsp"); //여기로 보내
				System.out.println("디스페쳐 간다");
				dispatcher.forward(request, response);
		} catch (Exception e) {e.printStackTrace();} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
