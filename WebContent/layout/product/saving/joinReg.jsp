<%@page import="jdbc.Saving.SavingDAO"%>
<%@page import="jdbc.Saving.SavingDTO"%>
<%@page import="util.New_Account"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="jdbc.Transfer.Transfer_logDTO"%>
<%@page import="jdbc.Transfer.Transfer_logDAO"%>
<%@page import="jdbc.Transfer.Transfer_autoDTO"%>
<%@page import="jdbc.Transfer.Transfer_autoDAO"%>
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
SavingDTO svnDTO = new SavingDTO();
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
accDTO.setType("적금");//종류
accDTO.setSum(sum);
accDTO.setAlias(request.getParameter("alias"));
accDTO.setId(userid);
accDTO.setPw(request.getParameter("newPW"));

svnDTO.setAccount_number(newAcc);
svnDTO.setId(userid);
svnDTO.setProduct(product);
svnDTO.setPreferential("임시");
svnDTO.setInterest(2.2F);
svnDTO.setType(request.getParameter("type"));

System.out.println("acc \t"+accDTO);
System.out.println("dep \t"+svnDTO);
AccountDAO.getInstance().insert(accDTO);
SavingDAO.getInstance().insert(svnDTO);

String json = gson.toJson(map);
out.print(json);
%>