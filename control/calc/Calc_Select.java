package calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdbc.Calc.JungDAO;
import jdbc.Calc.JungDTO;

@WebServlet("/calc/select")
public class Calc_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();
		String	json ="[";
		String	product = request.getParameter("product"),
				start_year = request.getParameter("start_year"),
				end_year = request.getParameter("end_year"),
				start_month = request.getParameter("start_month"),
				end_month = request.getParameter("end_month"),
				start_day = request.getParameter("start_day"),
				end_day = request.getParameter("end_day");

		System.out.println("getParameter : product "+product+" start_year "+start_year+", end_year "+end_year+", start_month "+start_month+", end_month "+end_month+", start_day "+start_day+", end_day "+end_day);

		ArrayList<JungDTO> dto = null; 
		String fomat = "";
		if(product.equals("모두")){
			if(start_year!=null&& end_year!=null) {
				dto = JungDAO.getInstance().year(start_year, end_year);
				fomat = "yyyy";
			} else if(start_month!=null&& end_month!=null) {	
				dto = JungDAO.getInstance().month(start_month, end_month);
				fomat ="yyyy-MM";
			} else if(start_day!=null&& end_day!=null)	{
				dto = JungDAO.getInstance().day(start_day, end_day);
				fomat ="yyyy-MM-dd";
			}
		} else {
			
			if(start_year!=null&& end_year!=null) {
				dto = JungDAO.getInstance().year(start_year, end_year, product);
				fomat = "yyyy";
			}else if(start_month!=null&& end_month!=null) {
				dto = JungDAO.getInstance().month(start_month, end_month, product);
				fomat ="yyyy-MM";
			}else if(start_day!=null&& end_day!=null) {	
				dto = JungDAO.getInstance().day(start_day, end_day, product);
				fomat ="yyyy-MM-dd";
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fomat);
		System.out.println("format : "+fomat);
		for (int i = 0; i < dto.size(); i++) {
			map.put("date", sdf.format(dto.get(i).getDate()));
			map.put("count", dto.get(i).getCount()+"");
			map.put("product", dto.get(i).getProduct());
			map.put("type", dto.get(i).getType());
			map.put("sum", dto.get(i).getSum()+"");
			json += gson.toJson(map);
			if(i < dto.size()-1)
				json +=",";
		}
		json+="]";
		out.print(json);
		System.out.println(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
