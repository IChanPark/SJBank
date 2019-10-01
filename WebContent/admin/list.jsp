<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="jdbc.Customer.Customer_faqDTO"%>
<%@page import="jdbc.Customer.Customer_faqDAO"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
System.out.println(request.getParameter("nn"));

Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="";
Customer_faqDTO dto = Customer_faqDAO.getInstance().selectSeq(Integer.parseInt(request.getParameter("nn") )) ;


	map.put("seq", dto.getSeq()+"");
	map.put("id", dto.getId());
	map.put("title", dto.getTitle());
	map.put("content", dto.getContent());
	map.put("type", dto.getType());
	map.put("status", dto.getStatus());
	map.put("register_date", dto.getRegister_dateStr());
	json += gson.toJson(map);
	
out.print(json);
%>
