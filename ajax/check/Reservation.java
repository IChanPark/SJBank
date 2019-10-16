package check;

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

import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;
import jdbc.Transfer.Transfer_reserveDAO;
import jdbc.Transfer.Transfer_reserveDTO;


@WebServlet("/Reservation")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	
		System.out.println("servelet Reservation");
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();
		String json ="[";
		ArrayList<Transfer_reserveDTO> dto = Transfer_reserveDAO.getInstance().AccSearchResTrs(
				request.getParameter("acc"),request.getParameter("start"), 
				request.getParameter("end"), request.getParameter("sort"));

		for (int i = 0; i < dto.size(); i++) {
			map.put("seq", dto.get(i).getSeq() + "");
			map.put("account_number", dto.get(i).getAccount_number());
			map.put("to_account_number", dto.get(i).getTo_account_number());
			map.put("sum", dto.get(i).getSum() + "");
			map.put("time", dto.get(i).getTimeStr());
			map.put("memo", dto.get(i).getMemo());
			map.put("to_memo", dto.get(i).getTo_memo());
			map.put("cms", dto.get(i).getCms());
			map.put("status", dto.get(i).getStatus());
			map.put("scheduled_date", dto.get(i).getScheduled_date());
			map.put("register_date", dto.get(i).getRegister_dateStr());

			json += gson.toJson(map);
			if (i < dto.size() - 1)
				json += ",";
		}
		json += "]";
		out.print(json);
	}

}
