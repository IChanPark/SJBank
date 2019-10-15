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
	ArrayList<Transfer_reserveDTO> dto = Transfer_reserveDAO.getInstance().SearchDate(
			request.getParameter("start"), request.getParameter("end"), request.getParameter("sort"));

	for (int i = 0; i < dto.size(); i++) {
		map.put("seq", dto.get(i).getSeq() + "");
		map.put("account_number", dto.get(i).getAccount_number());
		map.put("to_account_number", dto.get(i).getTo_account_number());
		map.put("sum", dto.get(i).getSum() + "");
		map.put("time", dto.get(i).getTimeStr());
		map.put("memo", dto.get(i).getMemo());
		map.put("to_memo", dto.get(i).getTo_memo());
		map.put("cms", dto.get(i).getCms());
		map.put("status", dto.get(i).getStatus());
		map.put("scheduled_date", dto.get(i).getScheduled_date());
		map.put("register_date", dto.get(i).getRegister_dateStr());

		json += gson.toJson(map);
		if (i < dto.size() - 1)
			json += ",";
	}
	json += "]";
	out.print(json);
%>

