<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="jdbc.Saving.Saving_typeDAO"%>
<%@page import="jdbc.Deposit.Deposits_typeDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리

String
product	=	request.getParameter("product"),
deposits_info = request.getParameter("product_info"),
id = "god";

System.out.println("product : "+product+" deposits_info : "+deposits_info+" id : " +id);

Deposits_infoDTO dto = new Deposits_infoDTO();

dto.setProduct(product);
dto.setDeposits_info(deposits_info);
dto.setId(id);

Deposits_infoDAO.getInstance().update_Deposits_info(dto); 
%>
