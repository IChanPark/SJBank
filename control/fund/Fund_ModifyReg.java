package fund;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Fund.Fund_InfoDAO;
import jdbc.Fund.Fund_InfoDTO;

@WebServlet("/product/fund/ModifyReg")
public class Fund_ModifyReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();

		String	status	= request.getParameter("status"),
				product	= request.getParameter("product"),
				product_info = request.getParameter("product_info"),
				id = "god";

		System.out.println("status : "+status+" product : "+product+" product_info : "+product_info+" id : " +id);

		Fund_InfoDTO dto = new Fund_InfoDTO();

		dto.setStatus(status);
		dto.setProduct(product);
		dto.setProduct_info(product_info);
		dto.setId(id);

		Fund_InfoDAO.getInstance().update_Product_info(dto); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
