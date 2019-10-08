<%@page import="jdbc.Loan.Loan_InfoDAO"%>
<%@page import="jdbc.Loan.Loan_InfoDTO"%>
<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();

Loan_InfoDTO setDTO = new Loan_InfoDTO();
setDTO.setProduct(request.getParameter("product"));

Loan_InfoDTO dto = Loan_InfoDAO.getInstance().selectProUse(setDTO);

map.put("product", dto.getProduct());
map.put("product_info", dto.getProduct_info());
map.put("min_interest", dto.getMin_interest()+"");
map.put("max_interest", dto.getMax_interest()+"");
map.put("month", dto.getMonth()+"");
map.put("type", dto.getType());
map.put("loanlimit",dto.getloanlimit()+"");
map.put("preferential",dto.getPreferential());
map.put("prf_content",dto.getPrf_content());
map.put("prf_interest",dto.getPrf_interest());

map.put("register_date",dto.getRegister_dateStr());

String json = gson.toJson(map);

out.print(json);
%>