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

%>    

<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
UserDTO dto = new UserDTO();
String json="";

dto.setName(request.getParameter("name"));
dto.setTel(request.getParameter("tel"));
dto=UserDAO.getInstance().searchId(dto); 

	map.put("id", dto.getId());
	json += gson.toJson(map);
	
out.print(json);
%>
