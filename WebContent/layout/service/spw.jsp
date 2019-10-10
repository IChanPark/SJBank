<%@page import="jdbc.Account.AccountDAO"%>
<%@page import="jdbc.Account.AccountDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="java.util.Map"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

System.out.println("oo");

%>
    
<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
UserDTO dto = new UserDTO();
AccountDTO adto = new AccountDTO();
String json="";

adto.setAccount_number(request.getParameter("account_number"));
adto.setPw(request.getParameter("account_pw"));
adto=AccountDAO.getInstance().searchAcc(adto);

dto.setId(request.getParameter("id"));
dto.setName(request.getParameter("name"));
dto.setTel(request.getParameter("tel"));
dto.setEmail(request.getParameter("email"));
dto.setPw(request.getParameter("pw"));



 if(dto.getId().equals(adto.getId())){
 	UserDAO.getInstance().updatePw(dto);
}

	map.put("pw", dto.getPw());
// 	map.put("pw", request.getParameter("id"));
	json += gson.toJson(map);
	
out.print(json);
%>
