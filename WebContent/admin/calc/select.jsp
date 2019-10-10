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
String	start_year = request.getParameter("start_year"),
		end_year = request.getParameter("end_year"),
		start_month = request.getParameter("start_month"),
		end_month = request.getParameter("end_month"),
		start_day = request.getParameter("start_day"),
		end_day = request.getParameter("end_day");

System.out.println("getParameter : start_year "+start_year+", end_year "+end_year+", start_month "+start_month+", end_month "+end_month+", start_day "+start_day+", end_day "+end_day);

ArrayList<JungDTO> dto = null; 

if(start_year!=null&& end_year!=null)
	dto = JungDAO.getInstance().year(start_year, end_year);
else if(start_month!=null&& end_month!=null)	
	dto = JungDAO.getInstance().month(start_month, end_month);
else if(start_day!=null&& end_day!=null)	
	dto = JungDAO.getInstance().day(start_day, end_day);

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