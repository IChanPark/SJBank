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


@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Detail() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	
		
		System.out.println("Here servelet Detail");
		 PrintWriter out = response.getWriter();
		 
		 String acc =request.getParameter("acc");
		 String startday = request.getParameter("start");
		 String endday = request.getParameter("end");
		 
		 Map<String,String> map = new HashMap<String,String>();
		 Gson gson = new Gson();
		 String json ="[";
		 ArrayList<Transfer_logDTO> dto = Transfer_logDAO.getInstance().selectANbyDay(acc, startday, endday); 

		 for (int i = 0; i < dto.size(); i++) {
		 	map.put("account_number", dto.get(i).getAccount_number());
		 	map.put("feetype", dto.get(i).getFeetype());
		 	map.put("target", dto.get(i).getTarget());
		 	map.put("to_account_number", dto.get(i).getTo_account_number());
		 	map.put("received", dto.get(i).getReceived());
		 	map.put("sum", dto.get(i).getSum()+"" );
		 	map.put("fee", dto.get(i).getFee()+"" );
		 	map.put("cms", dto.get(i).getCms() );
		 	map.put("memo", dto.get(i).getMemo() );
		 	map.put("to_memo", dto.get(i).getTo_memo() );
		 	map.put("status", dto.get(i).getStatus() );
		 	map.put("register_date", dto.get(i).getRegister_dateStr() );
		 	
		 	json += gson.toJson(map);
		 	if(i < dto.size()-1)
		 		json +=",";
		 }
		 json+="]";
		 out.print(json);
	}
}
