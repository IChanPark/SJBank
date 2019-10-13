package product;

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

import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.Deposit.Deposits_infoDAO;
import jdbc.Deposit.Deposits_infoDTO;

@WebServlet("/layout/product/deposit/Join")
public class Deposit_Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();
		AccountDTO DataDTO = new AccountDTO();
		DataDTO.setId((String)request.getSession().getAttribute("userID"));
		DataDTO.setType("예금");
		ArrayList<AccountDTO> accDTO = AccountDAO.getInstance().selectID_Type(DataDTO);

		Deposits_infoDTO setDTO = new Deposits_infoDTO();
		setDTO.setProduct(request.getParameter("product"));
		System.out.println(request.getParameter("product"));

		Deposits_infoDTO dto = Deposits_infoDAO.getInstance().selectProUse(setDTO);

		map.put("product", dto.getProduct());
		map.put("deposits_info", dto.getDeposits_info());
		map.put("min_interest", dto.getMin_interest()+"");
		map.put("max_interest", dto.getMax_interest()+"");
		map.put("month", dto.getMonth());
		map.put("type", dto.getType());
		map.put("interest_type",dto.getInterest_type());
		map.put("tax", dto.getTax());
		map.put("preferential",dto.getPreferential());
		map.put("prf_content",dto.getPrf_content());
		map.put("prf_interest",dto.getPrf_interest());
		map.put("partialization",dto.getPartialization());
		map.put("retention",dto.getRetention());
		map.put("min_sum",dto.getMin_sum()+"");
		map.put("max_sum",dto.getMax_sum()+"");
		map.put("register_date",dto.getRegister_dateStr());

		String acc = "";
		String alias ="";
		for (int i = 0; i < accDTO.size(); i++) {
			acc+= accDTO.get(i).getAccount_number();
			alias += accDTO.get(i).getAlias();
			if(i < accDTO.size()-1){
				acc +="#";
				alias +="#";	
			}
		}
		map.put("account_number",acc);
		map.put("alias",alias);
		String json = gson.toJson(map);
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
