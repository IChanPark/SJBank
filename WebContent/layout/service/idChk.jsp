<%@page import="jdbc.User.UserDTO"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
System.out.println(request.getParameter("id"));
UserDTO dto = UserDAO.getInstance().selectId(request.getParameter("id"));

if(dto!=null)
	map.put("id", dto.getId());
else 
	map.put("id","");

	
String json = gson.toJson(map);

System.out.print(json);
out.print(json);

%>