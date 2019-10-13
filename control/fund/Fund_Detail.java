package fund;

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

import jdbc.Fund.Fund_InfoDAO;
import jdbc.Fund.Fund_InfoDTO;

@WebServlet("/product/fund/Detail")
public class Fund_Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();

		Fund_InfoDTO setDTO = new Fund_InfoDTO();
		setDTO.setProduct(request.getParameter("product"));

		Fund_InfoDTO dto = Fund_InfoDAO.getInstance().selectProUse(setDTO);

		map.put("product", dto.getProduct());
		map.put("product_info", dto.getProduct_info());
		map.put("price", dto.getPrice()+"");
		map.put("price_modify", dto.getPrice_modify()+"");
		map.put("area", dto.getArea());
		map.put("type", dto.getType());
		map.put("property",dto.getProperty());
		map.put("tax", dto.getTax());
		map.put("first_fee",dto.getFirst_fee()+"");
		map.put("fee",dto.getFee()+"");
		map.put("management",dto.getManagement());
		map.put("sector",dto.getSector());
		map.put("register_date",dto.getRegister_dateStr());

		String json = gson.toJson(map);

		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
