package saving;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdbc.Saving.Saving_typeDAO;

@WebServlet("/product/saving/Type")
public class Saving_Type extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json ="";

		json = gson.toJson(Saving_typeDAO.getInstance().list()); 

		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}