<%@page import="util.New_Account"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="jdbc.Transfer.Transfer_logDTO"%>
<%@page import="jdbc.Transfer.Transfer_logDAO"%>
<%@page import="jdbc.Deposit.DepositsDAO"%>
<%@page import="jdbc.Deposit.DepositsDTO"%>
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
DepositsDTO depDTO = new DepositsDTO();
Transfer_autoDTO autoDTO = new Transfer_autoDTO();

String	userid = (String)request.getSession().getAttribute("userID"),
		newAcc = New_Account.getInstance().getAccount() ,
		myAcc = request.getParameter("account_number"),
		product=request.getParameter("product");

String 	targe = "SJ은행",
		cms	  = "",
		status= "성공",
		memo  = "예금가입"; 
		
int sum = Integer.parseInt(request.getParameter("sum"));
AccountDTO myAccDTO = AccountDAO.getInstance().selectAccount(myAcc);
UserDTO userDTO = UserDAO.getInstance().selectId(userid);

int mysum = myAccDTO.getSum();

AccountDAO.getInstance().updateMoney(myAccDTO);

// --------------------------------------------------- 보내는이 로그 
Transfer_logDTO transDTO = new Transfer_logDTO();
transDTO.setAccount_number(myAcc);
transDTO.setTarget(targe);
transDTO.setFeetype("송금");
transDTO.setTo_account_number(newAcc);
transDTO.setReceived(userDTO.getName());
transDTO.setSum(-(long)sum);
transDTO.setFee(500);
transDTO.setCms(cms);
transDTO.setMemo(memo);
transDTO.setTo_memo("");
transDTO.setStatus(status);
Transfer_logDAO.getInstance().insert(transDTO);
//--------------------------------------------------- 받는이 로그 
if((mysum-sum)<0) {
	status ="실패";
} else {
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
}

accDTO.setAccount_number(newAcc);
accDTO.setType("예금");//종류
accDTO.setSum(sum);
accDTO.setAlias(request.getParameter("alias"));
accDTO.setId(userid);
accDTO.setPw(request.getParameter("newPW"));

depDTO.setAccount_number(newAcc);
depDTO.setId(userid);
depDTO.setPrduct(product);
depDTO.setPreferential("임시");
depDTO.setInterest(2.2F);
depDTO.setType(request.getParameter("type"));

System.out.println("acc \t"+accDTO);
System.out.println("dep \t"+depDTO);
AccountDAO.getInstance().insert(accDTO);
DepositsDAO.getInstance().insert(depDTO);

String json = gson.toJson(map);
out.print(json);
%>