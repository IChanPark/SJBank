<%@page import="jdbc.Saving.Saving_infoDAO"%>
<%@page import="jdbc.Saving.Saving_infoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";
ArrayList<Saving_infoDTO> dto = 
/* Deposits_infoDAO.getInstance().listin(request.getParameter("ddd")); */
Saving_infoDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("product", dto.get(i).getProduct());
	map.put("min_interest", dto.get(i).getMin_interest()+"");
	map.put("max_interest", dto.get(i).getMax_interest()+"");
	map.put("month", dto.get(i).getMonth()+"");
	map.put("type", dto.get(i).getType());
	map.put("tax", dto.get(i).getTax());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
System.out.print(json);
%>