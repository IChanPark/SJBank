<%@page import="jdbc.Loan.Loan_InfoDAO"%>
<%@page import="jdbc.Loan.Loan_InfoDTO"%>
<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";

Loan_InfoDTO setDTO = new Loan_InfoDTO();
setDTO.setProduct(request.getParameter("title"));
setDTO.setType(request.getParameter("type"));

ArrayList<Loan_InfoDTO> dto = null;
//타입 타이틀 검색
if(!setDTO.getProduct().equals("") && !setDTO.getType().equals(""))
	dto = Loan_InfoDAO.getInstance().list();
//타입만 검색
else if(!setDTO.getType().equals(""))
	dto = Loan_InfoDAO.getInstance().list();
//타이틀만 검색
else if(!setDTO.getProduct().equals(""))
	dto = Loan_InfoDAO.getInstance().list();
else 
	dto = Loan_InfoDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("product", dto.get(i).getProduct());
	map.put("min_interest", dto.get(i).getMin_interest()+"");
	map.put("month", dto.get(i).getMonth()+"");
	map.put("loanlimit", dto.get(i).getloanlimit()+"");
	map.put("type", dto.get(i).getType());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}

json+="]";
out.print(json);
%>