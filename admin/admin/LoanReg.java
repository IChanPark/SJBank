package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Loan.Loan_InfoDAO;
import jdbc.Loan.Loan_InfoDTO;

public class LoanReg  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Loan_InfoDTO dto = new Loan_InfoDTO();
		
		String i = request.getParameter("prf_interest");
	
		dto.setProduct(request.getParameter("product"));
		dto.setProduct_info(request.getParameter("product_info"));
		dto.setMin_interest(Float.parseFloat(request.getParameter("min_interest")));
		dto.setMin_interest(Float.parseFloat(request.getParameter("max_interest")));
		dto.setMonth(Integer.parseInt(request.getParameter("month")));
		dto.setType(request.getParameter("type"));
		dto.setloanlimit(Long.parseLong(request.getParameter("loanlimit")));
		dto.setPreferential(request.getParameter("preferential"));
		dto.setPrf_content(request.getParameter("prf_content"));
		dto.setPrf_interest(i);
		dto.setStatus(request.getParameter("status"));

		float ff = 0;
		for (String s : i.split("#"))
			ff += Float.parseFloat(s);
		
		
		dto.setMax_interest(dto.getMin_interest()+ff);
		System.out.println(dto.toString());
		Loan_InfoDAO.getInstance().insert(dto); 
		request.setAttribute("mainUrl", "admin/inc/main");
	}
}