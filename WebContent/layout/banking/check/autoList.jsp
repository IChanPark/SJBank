<%@page import="jdbc.Transfer.Transfer_autoDAO"%>
<%@page import="jdbc.Transfer.Transfer_autoDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="jdbc.Transfer.Transfer_reserveDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Transfer.Transfer_reserveDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";
ArrayList<Transfer_autoDTO> dto = 
Transfer_autoDAO.getInstance().Search(
		request.getParameter("account"), request.getParameter("division")
		); 

for (int i = 0; i < dto.size(); i++) {
	map.put("seq", dto.get(i).getSeq()+"");
	map.put("account_number", dto.get(i).getAccount_number());
	map.put("to_account_number", dto.get(i).getTo_account_number());
	map.put("sum", dto.get(i).getSum()+"");
	map.put("period", dto.get(i).getPeriod());
	map.put("start_date", dto.get(i).getStart_dateStr());
	map.put("finish_date", dto.get(i).getFinish_dateStr());
	map.put("last_date", dto.get(i).getLast_day());
	map.put("memo", dto.get(i).getMemo());
	map.put("to_memo", dto.get(i).getTo_memo());
	map.put("status", dto.get(i).getStatus());
	map.put("register_date", dto.get(i).getRegister_dateStr());
	map.put("end_date", dto.get(i).getend_dateStr());
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
%>

