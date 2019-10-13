package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_infoDTO;

@WebServlet("/product/deposit/AddReg")
public class Deposit_AddReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		String	type = request.getParameter("sel_type"),
				product = request.getParameter("product"),
				deposits_info = request.getParameter("product_info"),
				month = request.getParameter("month"),
				interest_type = request.getParameter("interest_type"),
				tax = request.getParameter("tax"),
				retention = request.getParameter("retention"),
				partialization = request.getParameter("partialization"),
				preferential = request.getParameter("preferential"),
				prf_content = request.getParameter("prf_content"),
				prf_interest = request.getParameter("prf_interest"),
				id = "god";
		float	min_interest = Float.parseFloat(request.getParameter("min_interest")),
				max_interest = Float.parseFloat(request.getParameter("max_interest"));
		int		min_sum = Integer.parseInt(request.getParameter("min_sum")),
				max_sum = Integer.parseInt(request.getParameter("max_sum"));

		System.out.print("sel_type : "+type+" product : "+product+" deposits_info : "+deposits_info+" month : "+month+" interest_type : "+interest_type);
		System.out.print(" tax : "+tax+" retention : "+retention+" partialization : "+partialization+" preferential : "+preferential+" prf_content : "+prf_content);
		System.out.println(" prf_interest : "+prf_interest+" id : "+id);

		Deposits_infoDTO dto = new Deposits_infoDTO();

		dto.setType(type);
		dto.setProduct(product);
		dto.setDeposits_info(deposits_info);
		dto.setMonth(month);
		dto.setInterest_type(interest_type);
		dto.setTax(tax);
		dto.setMin_interest(min_interest);
		dto.setMax_interest(max_interest);
		dto.setRetention(retention);
		dto.setPartialization(partialization);
		dto.setMin_sum(min_sum);
		dto.setMax_sum(max_sum);
		dto.setPreferential(preferential);
		dto.setPrf_content(prf_content);
		dto.setPrf_interest(prf_interest);
		dto.setId(id);

		Deposits_infoDAO.getInstance().insert(dto); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
