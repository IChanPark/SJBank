<%@page import="jdbc.Saving.Saving_typeDAO"%>
<%@page import="jdbc.Deposit.Deposits_typeDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Gson gson = new Gson();
String json ="";

String product = request.getParameter("product");

json = gson.toJson(Deposits_typeDAO.getInstance().list());	

out.print(json);
%>
