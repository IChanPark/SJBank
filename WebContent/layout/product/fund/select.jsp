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
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";

Fund_InfoDTO setDTO = new Fund_InfoDTO();
setDTO.setProduct(request.getParameter("title"));
setDTO.setType(request.getParameter("type"));

ArrayList<Fund_InfoDTO> dto = null;
//타입 타이틀 검색
if(!setDTO.getProduct().equals("") && !setDTO.getType().equals(""))
	dto = Fund_InfoDAO.getInstance().list();
//타입만 검색
else if(!setDTO.getType().equals(""))
	dto = Fund_InfoDAO.getInstance().list();
//타이틀만 검색
else if(!setDTO.getProduct().equals(""))
	dto = Fund_InfoDAO.getInstance().list();
else 
	dto = Fund_InfoDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("product", dto.get(i).getProduct());
	map.put("price", dto.get(i).getPrice()+"");
	map.put("price_modify", dto.get(i).getPrice_modify()+"");
	map.put("fee", dto.get(i).getFee()+"");
	map.put("type", dto.get(i).getType());
	map.put("tax", dto.get(i).getTax());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}

json+="]";
out.print(json);
%>