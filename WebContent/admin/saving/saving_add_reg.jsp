<%@page import="jdbc.Saving.Saving_infoDAO"%>
<%@page import="jdbc.Saving.Saving_infoDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리

String type = request.getParameter("sel_type"),
product = request.getParameter("product"),
deposits_info = request.getParameter("product_info"),
month = request.getParameter("month"),
interest_type = request.getParameter("interest_type"),
tax = request.getParameter("tax"),
retention = request.getParameter("retention"),
partialization = request.getParameter("partialization"),
preferential = request.getParameter("preferential"),
prf_content = request.getParameter("prf_content"),
prf_interest = request.getParameter("prf_interest"),
id = "god";
float
min_interest = Float.parseFloat(request.getParameter("min_interest")),
max_interest = Float.parseFloat(request.getParameter("max_interest"));
int
min_sum = Integer.parseInt(request.getParameter("min_sum")),
max_sum = Integer.parseInt(request.getParameter("max_sum"));

System.out.print("sel_type : "+type+" product : "+product+" deposits_info : "+deposits_info+" month : "+month+" interest_type : "+interest_type);
System.out.print(" tax : "+tax+" retention : "+retention+" partialization : "+partialization+" preferential : "+preferential+" prf_content : "+prf_content);
System.out.println(" prf_interest : "+prf_interest+" id : "+id);

Saving_infoDTO dto = new Saving_infoDTO();

dto.setType(type);
dto.setProduct(product);
dto.setProduct_info(deposits_info);
dto.setMonth(month);
dto.setInterest_type(interest_type);
dto.setTax(tax);
dto.setMin_interest(min_interest);
dto.setMax_interest(max_interest);
dto.setRetention(retention);
dto.setPartialization(partialization);
dto.setMin_sum(min_sum);
dto.setMax_sum(max_sum);
dto.setPreferential(preferential);
dto.setPrf_content(prf_content);
dto.setPrf_interest(prf_interest);
dto.setId(id);

Saving_infoDAO.getInstance().insert(dto); 
%>
