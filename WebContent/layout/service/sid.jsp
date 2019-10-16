<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="java.util.Map"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println("name :"+request.getParameter("name"));

System.out.println("tel :"+request.getParameter("tel"));
System.out.println("email1 :"+request.getParameter("email1"));
System.out.println("email2 :"+request.getParameter("email2"));

%>    

<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
UserDTO dto = new UserDTO();
String json="";

dto.setName(request.getParameter("name"));
dto.setTel(request.getParameter("tel"));
dto.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));
dto=UserDAO.getInstance().searchId(dto); 


	map.put("id", dto.getId());
	json += gson.toJson(map);
	
out.print(json);
%>
