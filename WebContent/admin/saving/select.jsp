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

Saving_infoDTO setDTO = new Saving_infoDTO();
setDTO.setProduct(request.getParameter("title"));
setDTO.setType(request.getParameter("type"));

ArrayList<Saving_infoDTO> dto = null;
//타입 타이틀 검색
if(!setDTO.getProduct().equals("") && !setDTO.getType().equals(""))
	dto = Saving_infoDAO.getInstance().selectLikeAnd(setDTO);
//타입만 검색
else if(!setDTO.getType().equals(""))
	dto = Saving_infoDAO.getInstance().selectType(setDTO);
//타이틀만 검색
else if(!setDTO.getProduct().equals(""))
	dto = Saving_infoDAO.getInstance().selectLikePro(setDTO);
else 
	dto = Saving_infoDAO.getInstance().list();

for (int i = 0; i < dto.size(); i++) {
	map.put("product", dto.get(i).getProduct());
	map.put("min_interest", dto.get(i).getMin_interest()+"");
	map.put("max_interest", dto.get(i).getMax_interest()+"");
	map.put("month", dto.get(i).getMonth());
	map.put("type", dto.get(i).getType());
	map.put("tax", dto.get(i).getTax());
	map.put("register_date",dto.get(i).getRegister_dateStr());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
%>