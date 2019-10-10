<%@page import="jdbc.Calc.JungDAO"%>
<%@page import="jdbc.Calc.JungDTO"%>
<%@page import="java.util.ArrayList"%>
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

ArrayList<JungDTO> dto = JungDAO.getInstance().month();

for (int i = 0; i < dto.size(); i++) {
	 
	map.put("date", dto.get(i).getDateStr());
	map.put("count", dto.get(i).getCount()+"");
	map.put("product", dto.get(i).getProduct());
	map.put("type", dto.get(i).getType());
	map.put("sum", dto.get(i).getSum()+"");
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}

json+="]";
out.print(json);
System.out.println(json);
%>