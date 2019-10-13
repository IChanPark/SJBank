package deposit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_infoDTO;

@WebServlet("/product/deposit/Modify")
public class Deposit_Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();

		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();

		Deposits_infoDTO setDTO = new Deposits_infoDTO();
		setDTO.setProduct(request.getParameter("product"));

		Deposits_infoDTO dto = Deposits_infoDAO.getInstance().selectPro(setDTO);

		map.put("status", dto.getStatus());
		map.put("sel_type", dto.getType());
		map.put("product", dto.getProduct());
		map.put("product_info", dto.getDeposits_info());
		map.put("month", dto.getMonth());
		map.put("interest_type",dto.getInterest_type());
		map.put("tax", dto.getTax());
		map.put("min_sum",dto.getMin_sum()+"");
		map.put("max_sum",dto.getMax_sum()+"");
		map.put("retention",dto.getRetention());
		map.put("partialization",dto.getPartialization());
		map.put("min_interest", dto.getMin_interest()+"");
		map.put("max_interest", dto.getMax_interest()+"");
		map.put("preferential",dto.getPreferential());
		map.put("prf_content",dto.getPrf_content());
		map.put("prf_interest",dto.getPrf_interest());

		String json = gson.toJson(map);
		out.print(json);
		System.out.println(dto.getDeposits_info());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
