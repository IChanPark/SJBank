
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";
ArrayList<UserDTO> dto = 
UserDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("id", dto.get(i).getId());
	map.put("name", dto.get(i).getName()+"");
	map.put("tel", dto.get(i).getTel()+"");
	map.put("email", dto.get(i).getEmail()+"");
	map.put("status", dto.get(i).getStatus());
	
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
%>