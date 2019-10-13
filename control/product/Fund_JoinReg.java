package product;

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

		String	userid = (String)request.getSession().getAttribute("userID"),
				newAcc = New_Account.getInstance().getAccount(),
				myAcc = request.getParameter("account_number"),
				product=request.getParameter("product");

		int sum = Integer.parseInt(request.getParameter("sum"));

		System.out.println(request.getParameter("price_modify"));

		float price_modify =Float.parseFloat(request.getParameter("price_modify"));
		int buynum = (int)(sum/price_modify); //구매량

		float exchange=	buynum*price_modify; //구매한량 * 현재가격(평가금액)
		float rest = sum-exchange; //나머지돈
		float nowmoney = exchange+rest; //출금가능돈(평가금액)
		AccountDTO myAccDTO = AccountDAO.getInstance().selectAccount(myAcc);
		UserDTO userDTO = UserDAO.getInstance().selectId(userid);


		int mysum = myAccDTO.getSum();

		myAccDTO.setSum(mysum-sum);

		AccountDAO.getInstance().updateMoney(myAccDTO);

		/* Transfer_logDTO transDTO = new Transfer_logDTO();
		transDTO.setAccount_number(myAcc);
		transDTO.setSelf("본인");
		transDTO.setTarget("SJ은행");
		transDTO.setTo_account_number(newAcc);
		transDTO.setReceived(userDTO.getName());
		transDTO.setSum((long)sum);
		transDTO.setFee(500);
		transDTO.setCms("");
		transDTO.setMemo("예금");
		transDTO.setTo_memo("");
		transDTO.setStatus("성공");

		Transfer_logDAO.getInstance().insert(transDTO); */

		accDTO.setAccount_number(newAcc);
		accDTO.setType(request.getParameter("accType"));//종류
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

		System.out.println("acc \t"+accDTO);
		System.out.println("dep \t"+depDTO);

		AccountDAO.getInstance().insert(accDTO);
		FundDAO.getInstance().insert(depDTO);
		String json = gson.toJson(map);
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
