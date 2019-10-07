<%@page import="jdbc.User.User_mymenuDAO"%>
<%@page import="jdbc.User.User_mymenuDTO"%>
<%@page import="jdbc.Menu.MenuDAO"%>
<%@page import="jdbc.Menu.MenuDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String,String> map = new HashMap<String,String>();
Map<String,String> map2 = new HashMap<String,String>();
User_mymenuDTO dto2 = User_mymenuDAO.getInstance().selectId((String)request.getSession().getAttribute("userID"));
map2.put("mymenu1", dto2.getMymenu1());
map2.put("mymenu2", dto2.getMymenu2());
map2.put("mymenu3", dto2.getMymenu3());
map2.put("mymenu4", dto2.getMymenu4());
map2.put("mymenu5", dto2.getMymenu5()); 

Gson gson = new Gson();

String json ="[";
ArrayList<MenuDTO> dto = 
MenuDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("kor_name", dto.get(i).getKor_name());
	map.put("name", dto.get(i).getName()+"");
	map.put("type", dto.get(i).getType()+"");
	map.put("depth", dto.get(i).getDepth()+"");
	map.put("sort", dto.get(i).getSort()+"");
	map.put("status", dto.get(i).getStatus());
	 
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json +=",";
 json += gson.toJson(map2);
json+="]";

out.print(json);

%>