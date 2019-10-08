
<%@page import="jdbc.Loan.LoanDAO"%>
<%@page import="jdbc.Loan.Loan_logDAO"%>
<%@page import="jdbc.Loan.Loan_logDTO"%>
<%@page import="jdbc.Loan.LoanDTO"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="jdbc.Transfer.Transfer_logDTO"%>
<%@page import="jdbc.Transfer.Transfer_logDAO"%>
<%@page import="jdbc.Transfer.Transfer_autoDTO"%>
<%@page import="jdbc.Transfer.Transfer_autoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Account.AccountDTO"%>
<%@page import="jdbc.Account.AccountDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();

AccountDTO accDTO = new AccountDTO();
LoanDTO depDTO = new LoanDTO();
Transfer_autoDTO autoDTO = new Transfer_autoDTO();

String	userid = (String)request.getSession().getAttribute("userID"),
		//newAcc = "010-1111-1111-162",
		myAcc = request.getParameter("account_number"),
		product=request.getParameter("product");

int sum = Integer.parseInt(request.getParameter("sum"));
AccountDTO myAccDTO = AccountDAO.getInstance().selectAccount(myAcc);
UserDTO userDTO = UserDAO.getInstance().selectId(userid);


int mysum = myAccDTO.getSum();

myAccDTO.setSum(mysum-sum);

AccountDAO.getInstance().updateMoney(myAccDTO);

Loan_logDTO LoanDTO = new Loan_logDTO();
LoanDTO.setAccount_number(myAcc);
LoanDTO.setSum(sum);
LoanDTO.setRemain(sum);
LoanDTO.setStatus("성공");

Loan_logDAO.getInstance().insert(LoanDTO); 


depDTO.setAccount_number(myAcc);
depDTO.setId(userid);
depDTO.setProduct(product);
depDTO.setSum(sum);
//depDTO.setPeriod(month);

System.out.println("dddd "+request.getParameter("auto"));

/* if(((String)request.getParameter("auto")).equals("신청")){
autoDTO.setAccount_number(myAcc);
autoDTO.setTo_account_number(newAcc);
autoDTO.setSum(sum);
autoDTO.setPeriod("한달");
autoDTO.setStart_dateStr(request.getParameter("startDate"));
autoDTO.setFinish_dateStr(request.getParameter("finish_date"));
autoDTO.setLast_day("말일이체안함");
autoDTO.setMemo("예금");
autoDTO.setTo_memo("");
Transfer_autoDAO.getInstance().insert(autoDTO); 
}*/

System.out.println("acc \t"+accDTO);
System.out.println("dep \t"+depDTO);
//System.out.println("auto \t"+autoDTO);

AccountDAO.getInstance().insert(accDTO);
LoanDAO.getInstance().insert(depDTO);



/* Deposits_infoDTO setDTO = new Deposits_infoDTO();
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

String myAcc = "";
String alias ="";
for (int i = 0; i < accDTO.size(); i++) {
	myAcc+= accDTO.get(i).getAccount_number();
	alias += accDTO.get(i).getAlias();
	if(i < accDTO.size()-1){
		myAcc +="#";
		alias +="#";	
	}
}
map.put("account_number",myAcc);
map.put("alias",alias); */
String json = gson.toJson(map);
out.print(json);
%>