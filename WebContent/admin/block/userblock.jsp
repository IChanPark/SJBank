<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
System.out.println(request.getParameter("product"));
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json;
UserDTO dto = 

UserDAO.getInstance().selectId(request.getParameter("product"));
	
	map.put("id", dto.getId()+"");
	map.put("name", dto.getName());
	map.put("status", dto.getStatus());
	json = gson.toJson(map);
	System.out.println(json);

out.print(json);
%>