<%@page import="jdbc.Account.AccountDAO"%>
<%@page import="jdbc.Account.AccountDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
System.out.println(request.getParameter("account_number"));
System.out.println(request.getParameter("account_pw"));

%>
    
<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
AccountDTO dto = new AccountDTO();
String json="";

dto.setAccount_number(request.getParameter("account_number"));
dto.setPw(request.getParameter("account_pw"));
dto=AccountDAO.getInstance().searchAcc(dto);


	map.put("id", dto.getId());
// 	map.put("pw", request.getParameter("id"));
	json += gson.toJson(map);
	
out.print(json);
%>
