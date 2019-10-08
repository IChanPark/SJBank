<%@page import="jdbc.Service.QnaDAO"%>
<%@page import="jdbc.Service.QnaDTO"%>
<%@page import="jdbc.Service.FaqDAO"%>
<%@page import="jdbc.Service.FaqDTO"%>
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

ArrayList<QnaDTO> dto = QnaDAO.getInstance().list();


for (int i = 0; i < dto.size(); i++) {
	map.put("title", dto.get(i).getTitle());
	map.put("type", dto.get(i).getType());
	map.put("content", dto.get(i).getContent());
	map.put("register_date",dto.get(i).getRegister_dateStr());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
System.out.println(json);
%>