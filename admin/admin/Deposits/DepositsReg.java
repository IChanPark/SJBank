package admin.Deposits;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_infoDTO;

public class DepositsReg  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Deposits_infoDTO dto = new Deposits_infoDTO();
		
		String i = request.getParameter("prf_interest");
		
		dto.setProduct(request.getParameter("product"));
		dto.setMin_interest(Float.parseFloat(request.getParameter("min_interest")));
		dto.setMonth(request.getParameter("month"));
		dto.setType(request.getParameter("type"));
		dto.setInterest_type(request.getParameter("interest_type"));
		dto.setTax(request.getParameter("tax"));
		dto.setPreferential(request.getParameter("preferential"));
		dto.setPrf_content(request.getParameter("prf_content"));
		dto.setPrf_interest(i);
		dto.setPartialization(request.getParameter("partialization"));
		dto.setRetention(request.getParameter("retention"));
		dto.setMin_sum(Integer.parseInt(request.getParameter("min_sum")));
		dto.setMax_sum(Integer.parseInt(request.getParameter("max_sum")));

		float ff = 0;
		for (String s : i.split("#"))
			ff += Float.parseFloat(s);
		
		
		dto.setMax_interest(dto.getMin_interest()+ff);
		
		Deposits_infoDAO.getInstance().insert(dto); 
		request.setAttribute("mainUrl", "admin/inc/main");
	}
}