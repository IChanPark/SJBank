<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();

Deposits_infoDTO setDTO = new Deposits_infoDTO();
setDTO.setProduct(request.getParameter("product"));

Deposits_infoDTO dto = Deposits_infoDAO.getInstance().selectProUse(setDTO);

map.put("product", dto.getProduct());
map.put("deposits_info", dto.getDeposits_info());
map.put("min_interest", dto.getMin_interest()+"");
map.put("max_interest", dto.getMax_interest()+"");
map.put("month", dto.getMonth()+"");
map.put("type", dto.getType());
map.put("interest_type",dto.getInterest_type());
map.put("tax", dto.getTax());
map.put("preferential",dto.getPreferential());
map.put("prf_content",dto.getPrf_content());
map.put("prf_interest",dto.getPrf_interest());
map.put("partialization",dto.getPartialization());
map.put("retention",dto.getRetention());
map.put("min_sum",dto.getMin_sum()+"");
map.put("max_sum",dto.getMax_sum()+"");
map.put("register_date",dto.getRegister_dateStr());

String json = gson.toJson(map);

out.print(json);
%>