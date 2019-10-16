package calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdbc.Deposit.Deposits_typeDAO;
import jdbc.Fund.Fund_typeDAO;
import jdbc.Saving.Saving_typeDAO;

@WebServlet("/calc/Product_type")
public class Calc_Product_type extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");	//한글처리
		Gson gson = new Gson();
		String json ="";
		String product = request.getParameter("product");

		if (product.equals("deposit"))//예금
			json = gson.toJson(Deposits_typeDAO.getInstance().list());	
		else if(product.equals("save"))//적금
			json = gson.toJson(Saving_typeDAO.getInstance().list());
		else if(product.equals("fund"))//적금
			json = gson.toJson(Fund_typeDAO.getInstance().list());

		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
