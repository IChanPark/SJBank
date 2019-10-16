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

import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.Fund.FundDAO;
import jdbc.Fund.FundDTO;
import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;
import util.New_Account;

@WebServlet("/product/fund/JoinReg")
public class Fund_JoinReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");	//한글처리
		PrintWriter out = response.getWriter();
		Map<String,String> map = new HashMap<String,String>();
		Gson gson = new Gson();

		AccountDTO accDTO = new AccountDTO();
		FundDTO depDTO = new FundDTO();

		String	userid	= (String)request.getSession().getAttribute("userID"),
				newAcc	= New_Account.getInstance().getAccount(),
				myAcc	= request.getParameter("account_number"),
				product	= request.getParameter("product");

		String 	targe	= "SJ은행",
				cms		= "",
				status	= "",
				memo  	= "펀드가입";
		
		float	price_modify =Float.parseFloat(request.getParameter("price_modify"));
		
		int		sum = Integer.parseInt(request.getParameter("sum")),
				buynum = (int)(sum/price_modify); //구매량		

		float 	exchange=	buynum*price_modify, //구매한량 * 현재가격(평가금액)
				rest = sum-exchange, //나머지돈
				nowmoney = exchange+rest; //출금가능돈(평가금액)
		
		AccountDTO myAccDTO = AccountDAO.getInstance().selectAccount(myAcc);
		UserDTO userDTO = UserDAO.getInstance().selectId(userid);

		int mysum = myAccDTO.getSum();
		
		if(myAccDTO.getPw().equals(request.getParameter("pw"))) {
		
			Transfer_logDTO transDTO = new Transfer_logDTO();
			transDTO.setAccount_number(myAcc);
			transDTO.setTarget(targe);
			transDTO.setFeetype("송금");
			transDTO.setTo_account_number(newAcc);
			transDTO.setReceived(userDTO.getName());
			transDTO.setSum((long)sum);
			transDTO.setFee(500);
			transDTO.setCms(cms);
			transDTO.setMemo(memo);
			transDTO.setTo_memo("");
			//--------------------------------------------------- 받는이 로그 
			if((mysum-sum)<0) {
				status ="실패";
				map.put("status", "금액부족");
			}
			
			transDTO.setStatus(status);
			Transfer_logDAO.getInstance().insert(transDTO);
				
			if((mysum-sum)>0) {
				myAccDTO.setSum(mysum-sum);
				AccountDAO.getInstance().updateMoney(myAccDTO);
				
				transDTO.setAccount_number(newAcc);
				transDTO.setTarget(targe);
				transDTO.setFeetype("입금");
				transDTO.setTo_account_number(myAcc);
				transDTO.setReceived(userDTO.getName());
				transDTO.setSum((long)sum);
				transDTO.setFee(0);
				transDTO.setCms(cms);
				transDTO.setMemo(memo);
				transDTO.setTo_memo("");
				transDTO.setStatus("성공");
				Transfer_logDAO.getInstance().insert(transDTO);
	
				accDTO.setAccount_number(newAcc);
				accDTO.setType("펀드");//종류
				accDTO.setSum(sum);
				accDTO.setAlias(request.getParameter("alias"));
				accDTO.setId(userid);
				accDTO.setPw(request.getParameter("newPW"));
		
				depDTO.setAccount_number(newAcc);
				depDTO.setId(userid);
				depDTO.setProduct(product);
				depDTO.setFluctuation((float)0.5);
				depDTO.setAmount((float)sum);
				depDTO.setExchange(exchange);
				depDTO.setNowmoney(nowmoney);
				depDTO.setBuynum(buynum);
				depDTO.setRest(rest);
				depDTO.setPrice_modify(price_modify);
		
				AccountDAO.getInstance().insert(accDTO);
				FundDAO.getInstance().insert(depDTO);
				map.put("status", "성공");
				map.put("product", product);
				map.put("newAcc", newAcc);
				map.put("type", request.getParameter("type"));
			}
		} else {
			map.put("status", "실패");
		}
		String json = gson.toJson(map);
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
