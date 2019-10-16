package deposit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;

@WebServlet("/product/sumChk")
public class SumChk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();
		
		String 	account_number	= request.getParameter("account_number"),
				accPW			= request.getParameter("accPW"),
				sum				= "",
				status			= "실패";
		
		AccountDTO myAccDTO = AccountDAO.getInstance().selectAccount(account_number);
		
		if(myAccDTO.getPw().equals(accPW)) {
			status = "성공";
			sum	= myAccDTO.getSum()+"";
		}
		map.put("status", status);
		map.put("sum", sum);
		
		String json = gson.toJson(map);
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
