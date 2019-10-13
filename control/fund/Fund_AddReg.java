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

@WebServlet("/product/fund/AddReg")
public class Fund_AddReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		String	type = request.getParameter("sel_type"),
				product = request.getParameter("product"),
				product_info = request.getParameter("product_info"),
				management = request.getParameter("management"),
				tax = request.getParameter("tax"),
				area = request.getParameter("area"),
				sector = request.getParameter("sector"),
				id = "god";
		float 	price = 1000.0f,
				price_modify = price,//수정기준가는 기준가랑 동일하게 
				first_fee = Float.parseFloat(request.getParameter("first_fee")),
				fee = Float.parseFloat(request.getParameter("fee"));

		System.out.print("sel_type : "+type+" product : "+product+" price_modify : "+price_modify+" product_info : "+product_info+" management : "+management);
		System.out.print(" tax : "+tax+" area : "+area+" first_fee : "+first_fee+" fee : "+fee);
		System.out.println(" sector : "+sector+" id : "+id +" price : "+price);

		Fund_InfoDTO dto = new Fund_InfoDTO();

		dto.setType(type);
		dto.setProduct(product);
		dto.setProduct_info(product_info);
		dto.setPrice_modify(price_modify);
		dto.setManagement(management);
		dto.setTax(tax);
		dto.setArea(area);
		dto.setProperty("");
		dto.setSector(sector);
		dto.setPrice_modify(price_modify);
		dto.setPrice(price);
		dto.setFirst_fee(first_fee);
		dto.setFee(fee);
		dto.setId(id);

		Fund_InfoDAO.getInstance().insert(dto); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
