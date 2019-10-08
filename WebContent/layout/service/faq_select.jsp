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

FaqDTO setDTO = new FaqDTO();
//setDTO.setTitle(request.getParameter("title"));
setDTO.setType(request.getParameter("type"));

ArrayList<FaqDTO> dto = null;
//타입 타이틀 검색
/* if(!setDTO.getTitle().equals("") && !setDTO.getType().equals(""))
	dto = FaqDAO.getInstance().selectLikeAnd(setDTO);
//타입만 검색
else */
	if(!setDTO.getType().equals(""))
	dto = FaqDAO.getInstance().selectType(setDTO);
	else 
		dto = FaqDAO.getInstance().list();

//타이틀만 검색
//else if(!setDTO.getTitle().equals(""))
//	dto = FaqDAO.getInstance().selectLikePro(setDTO);
//else  
//	dto = FaqDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("title", dto.get(i).getTitle());
	map.put("type", dto.get(i).getType());
	map.put("content", dto.get(i).getContent());
	map.put("id", dto.get(i).getId());
	map.put("register_date",dto.get(i).getRegister_dateStr());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
System.out.println(json);
%>