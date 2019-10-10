<%@page import="jdbc.Transfer.Transfer_logDAO"%>
<%@page import="jdbc.Transfer.Transfer_logDTO"%>
<%@page import="jdbc.Account.AccountDTO"%>
<%@page import="jdbc.Account.AccountDAO"%>
<%@page import="jdbc.Transfer.Transfer_autoDTO"%>
<%@page import="jdbc.Transfer.Transfer_autoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%System.out.println(request.getParameter("acc")+"  start애요"); 
String acc =request.getParameter("acc");
String startday = request.getParameter("start");
String endday = request.getParameter("end");
%>


<%
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
String json ="[";
ArrayList<Transfer_logDTO> dto = Transfer_logDAO.getInstance().selectANbyDay(acc, startday, endday); 

for (int i = 0; i < dto.size(); i++) {
	map.put("account_number", dto.get(i).getAccount_number());
	map.put("feetype", dto.get(i).getFeetype());
	map.put("target", dto.get(i).getTarget());
	map.put("to_account_number", dto.get(i).getTo_account_number());
	map.put("received", dto.get(i).getReceived());
	map.put("sum", dto.get(i).getSum()+"" );
	map.put("fee", dto.get(i).getFee()+"" );
	map.put("cms", dto.get(i).getCms() );
	map.put("memo", dto.get(i).getMemo() );
	map.put("to_memo", dto.get(i).getTo_memo() );
	map.put("status", dto.get(i).getStatus() );
	map.put("register_date", dto.get(i).getRegister_dateStr() );
	
	
	json += gson.toJson(map);
	if(i < dto.size()-1)
		json +=",";
}
json+="]";
out.print(json);
%>

