<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();

Deposits_infoDTO setDTO = new Deposits_infoDTO();
setDTO.setProduct(request.getParameter("product"));

Deposits_infoDTO dto = Deposits_infoDAO.getInstance().selectProUse(setDTO);

map.put("sel_type", dto.getType());
map.put("product", dto.getProduct());
map.put("product_info", dto.getDeposits_info());
map.put("month", dto.getMonth());
map.put("interest_type",dto.getInterest_type());
map.put("tax", dto.getTax());
map.put("min_sum",dto.getMin_sum()+"");
map.put("max_sum",dto.getMax_sum()+"");
map.put("retention",dto.getRetention());
map.put("partialization",dto.getPartialization());
map.put("min_interest", dto.getMin_interest()+"");
map.put("max_interest", dto.getMax_interest()+"");
map.put("preferential",dto.getPreferential());
map.put("prf_content",dto.getPrf_content());
map.put("prf_interest",dto.getPrf_interest());

String json = gson.toJson(map);
out.print(json);
System.out.println(dto.getDeposits_info());
%>