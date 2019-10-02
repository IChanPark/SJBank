package admin.Fund;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Fund.Fund_InfoDAO;
import jdbc.Fund.Fund_InfoDTO;

public class FundReg  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Fund_InfoDTO dto = new Fund_InfoDTO();
		
		
		dto.setProduct(request.getParameter("product"));
		dto.setProduct_info(request.getParameter("product_info"));
		dto.setPrice(Float.parseFloat(request.getParameter("price")));
		dto.setPrice_modify(Float.parseFloat(request.getParameter("price_modify")));
		dto.setType(request.getParameter("type"));
		dto.setArea(request.getParameter("area"));
		dto.setProperty(request.getParameter("property"));
		dto.setFirst_fee(Float.parseFloat(request.getParameter("first_fee")));
		dto.setFee(Float.parseFloat(request.getParameter("fee")));
		dto.setManagement(request.getParameter("management"));
		dto.setSector(request.getParameter("sector"));
		dto.setStatus(request.getParameter("status"));
		dto.setTax(request.getParameter("tax"));

		
		Fund_InfoDAO.getInstance().insert(dto); 
		
		request.setAttribute("mainUrl", "admin/inc/main");
	}
}