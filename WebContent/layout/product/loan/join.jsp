<%@page import="jdbc.Loan.Loan_InfoDAO"%>
<%@page import="jdbc.Loan.Loan_InfoDTO"%>
<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
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
AccountDTO DataDTO = new AccountDTO();
DataDTO.setId((String)request.getSession().getAttribute("userID"));
DataDTO.setType("예금");
ArrayList<AccountDTO> accDTO = AccountDAO.getInstance().selectID_Type(DataDTO);

Loan_InfoDTO setDTO = new Loan_InfoDTO();
setDTO.setProduct(request.getParameter("product"));
System.out.println(request.getParameter("product"));
Loan_InfoDTO dto = Loan_InfoDAO.getInstance().selectProUse(setDTO);
map.put("product", dto.getProduct());
map.put("product_info", dto.getProduct_info());
map.put("min_interest", dto.getMin_interest()+"");
map.put("max_interest", dto.getMax_interest()+"");
map.put("month", dto.getMonth()+"");
map.put("type", dto.getType());
map.put("loanlimit",dto.getloanlimit()+"");
map.put("preferential",dto.getPreferential());
map.put("prf_content",dto.getPrf_content());
map.put("prf_interest",dto.getPrf_interest());

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
%>