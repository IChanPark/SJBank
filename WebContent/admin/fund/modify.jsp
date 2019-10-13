<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();

Fund_InfoDTO setDTO = new Fund_InfoDTO();
setDTO.setProduct(request.getParameter("product"));

Fund_InfoDTO dto = Fund_InfoDAO.getInstance().selectPro(setDTO);

map.put("status", dto.getStatus());
map.put("product", dto.getProduct());
map.put("product_info", dto.getProduct_info());
map.put("price", dto.getPrice()+"");
map.put("sel_type", dto.getType());
map.put("management",dto.getManagement());
map.put("tax", dto.getTax());
map.put("area",dto.getArea());
map.put("property",dto.getProperty());
map.put("first_fee",dto.getFirst_fee()+"");
map.put("fee",dto.getFee()+"");
map.put("sector",dto.getSector());

String json = gson.toJson(map);
out.print(json);
%>