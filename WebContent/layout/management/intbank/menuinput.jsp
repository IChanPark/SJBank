<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
ArrayList<String> aaa = new ArrayList<String>();

for(String s : request.getParameter("list").split(",")){
	aaa.add(s);
}

for(String s : aaa){
	System.out.println(s);
}

User_mymenuDTO dto3 = new User_mymenuDTO();

dto3.setId((String)request.getSession().getAttribute("userID"));
dto3.setMymenu1(aaa.get(0));
dto3.setMymenu2(aaa.get(1));
dto3.setMymenu3(aaa.get(2));
dto3.setMymenu4(aaa.get(3));
dto3.setMymenu5(aaa.get(4));

User_mymenuDAO.getInstance().insert(dto3);

%>