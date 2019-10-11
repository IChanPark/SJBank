<%@page import="jdbc.Saving.Saving_infoDAO"%>
<%@page import="jdbc.Saving.Saving_infoDTO"%>
<%@page import="jdbc.Saving.Saving_typeDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리

String
status	= request.getParameter("status"),
product	= request.getParameter("product"),
deposits_info = request.getParameter("product_info"),
id = "god";

System.out.println("status : "+status+" product : "+product+" deposits_info : "+deposits_info+" id : " +id);

Saving_infoDTO dto = new Saving_infoDTO();

dto.setStatus(status);
dto.setProduct(product);
dto.setProduct_info(deposits_info);
dto.setId(id);

Saving_infoDAO.getInstance().update_Product_info(dto); 
%>
