<%@page import="jdbc.Fund.Fund_typeDAO"%>
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

if (product.equals("deposit"))//예금
	json = gson.toJson(Deposits_typeDAO.getInstance().list());	
else if(product.equals("save"))//적금
	json = gson.toJson(Saving_typeDAO.getInstance().list());
else if(product.equals("fund"))//적금
	json = gson.toJson(Fund_typeDAO.getInstance().list());

out.print(json);
%>
