package deposit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_infoDTO;

@WebServlet("/product/deposit/ModifyReg")
public class Deposit_ModifyReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();

		String	status	= request.getParameter("status"),
				product	= request.getParameter("product"),
				deposits_info = request.getParameter("product_info"),
				id = "god";

		System.out.println("status : "+status+" product : "+product+" deposits_info : "+deposits_info+" id : " +id);

		Deposits_infoDTO dto = new Deposits_infoDTO();

		dto.setStatus(status);
		dto.setProduct(product);
		dto.setDeposits_info(deposits_info);
		dto.setId(id);

		Deposits_infoDAO.getInstance().update_Deposits_info(dto); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
