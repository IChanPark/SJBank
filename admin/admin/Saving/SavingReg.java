package admin.Saving;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Saving.Saving_infoDAO;
import jdbc.Saving.Saving_infoDTO;

public class SavingReg  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Saving_infoDTO dto = new Saving_infoDTO();
		
		String i = request.getParameter("prf_interest");
		
		dto.setProduct(request.getParameter("product"));
		dto.setProduct_info(request.getParameter("product_info"));
		dto.setMin_interest(Float.parseFloat(request.getParameter("min_interest")));
		dto.setMax_interest(Float.parseFloat(request.getParameter("max_interest")));
		dto.setMonth(Integer.parseInt(request.getParameter("month")));
		dto.setType(request.getParameter("type"));
		dto.setRegular(request.getParameter("regular"));
		dto.setInterest_type(request.getParameter("interest_type"));
		dto.setTax(request.getParameter("tax"));
		dto.setPreferential(request.getParameter("preferential"));
		dto.setPrf_content(request.getParameter("prf_content"));
		dto.setPrf_interest(i);
		dto.setPartialization(request.getParameter("partialization"));
		dto.setRetention(request.getParameter("retention"));
		dto.setStatus(request.getParameter("status"));
		dto.setMin_sum(Integer.parseInt(request.getParameter("min_sum")));
		dto.setMax_sum(Integer.parseInt(request.getParameter("max_sum")));

		float ff = 0;
		for (String s : i.split("#"))
			ff += Float.parseFloat(s);
		
		
		dto.setMax_interest(dto.getMin_interest()+ff);
		
		Saving_infoDAO.getInstance().insert(dto); 
		request.setAttribute("mainUrl", "admin/inc/main");
	}
}