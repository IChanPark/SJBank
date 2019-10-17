<%@page import="jdbc.Account.AccountDAO"%>
<%@page import="jdbc.Account.AccountDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="java.util.Map"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%
System.out.println("account_number : "+request.getParameter("account_number"));
System.out.println("account_pw : "+request.getParameter("account_pw"));
System.out.println("account_Id : "+adto.getId());


System.out.println("uid : "+request.getParameter("uid"));
System.out.println("name : "+request.getParameter("name"));
System.out.println("tel : "+request.getParameter("tel"));
System.out.println("email : "+request.getParameter("email1")+"@"+request.getParameter("email2"));
System.out.println("pw : "+request.getParameter("pw"));

%> --%>
    
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
// AccountDTO adto = new AccountDTO();
UserDTO dto = new UserDTO();
UserDTO adto = new UserDTO();
String json="";

// adto.setAccount_number(request.getParameter("account_number"));
// adto.setPw(request.getParameter("account_pw"));

// adto=AccountDAO.getInstance().searchAcc(adto);

dto.setId(request.getParameter("uid"));
adto = UserDAO.getInstance().selectId(request.getParameter("uid"));

dto.setName(adto.getName());
dto.setTel(adto.getTel());
dto.setEmail(adto.getEmail());
dto.setPw(request.getParameter("pw"));

 	UserDAO.getInstance().updatePw(dto);

 	
	map.put("pw", dto.getPw());
// 	map.put("pw", request.getParameter("id"));
	json += gson.toJson(map);
	
out.print(json);
%>
