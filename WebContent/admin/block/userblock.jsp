
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
System.out.println(request.getParameter("ddd"));
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";
UserDTO dto = 

UserDAO.getInstance().selectId(request.getParameter("ddd"));


	map.put("id", dto.getId()+"");
	map.put("name", dto.getName());

	
	json += gson.toJson(map);
	
json+="]";
out.print(json);
%>