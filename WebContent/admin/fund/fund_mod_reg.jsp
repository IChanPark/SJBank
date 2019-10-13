<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리

String
status	= request.getParameter("status"),
product	= request.getParameter("product"),
product_info = request.getParameter("product_info"),
id = "god";

System.out.println("status : "+status+" product : "+product+" product_info : "+product_info+" id : " +id);

Fund_InfoDTO dto = new Fund_InfoDTO();

dto.setStatus(status);
dto.setProduct(product);
dto.setProduct_info(product_info);
dto.setId(id);

Fund_InfoDAO.getInstance().update_Product_info(dto); 
%>
