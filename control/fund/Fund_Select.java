package fund;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

@WebServlet("/product/fund/Select")
public class Fund_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();
		String json ="[";

		Fund_InfoDTO setDTO = new Fund_InfoDTO();
		setDTO.setProduct(request.getParameter("title"));
		setDTO.setType(request.getParameter("type"));

		ArrayList<Fund_InfoDTO> dto = null;
		//타입 타이틀 검색
		if(!setDTO.getProduct().equals("") && !(setDTO.getType()==null || setDTO.getType().equals("")))
			dto = Fund_InfoDAO.getInstance().selectLikeAnd(setDTO);
		//타입만 검색
		else if(!(setDTO.getType()==null || setDTO.getType().equals("")))
			dto = Fund_InfoDAO.getInstance().selectType(setDTO);
		//타이틀만 검색
		else if(!setDTO.getProduct().equals(""))
			dto = Fund_InfoDAO.getInstance().selectLikePro(setDTO);
		else 
			dto = Fund_InfoDAO.getInstance().list();

		for (int i = 0; i < dto.size(); i++) {
			map.put("product", dto.get(i).getProduct());
			map.put("price", dto.get(i).getPrice()+"");
			map.put("price_modify", dto.get(i).getPrice_modify()+"");
			map.put("fee", dto.get(i).getFee()+"");
			map.put("type", dto.get(i).getType());
			map.put("tax", dto.get(i).getTax());
			
			json += gson.toJson(map);
			if(i < dto.size()-1)
				json +=",";
		}

		json+="]";
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
