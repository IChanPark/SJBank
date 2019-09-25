<%@page import="jdbc.Menu.MenuDAO"%>
<%@page import="jdbc.Menu.MenuDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form name="paging" action="index.jsp"/>
<input type="hidden" name="type" />
<!-- post데이터 전송용 form과 input -->

<div id="top">
	<div id="info"><img alt="" src="img/logo.png" height="40px" display="block" margin="0px auto"/>
	<a href="#" data-menu-name="login" id="login">로그인</a>
	</div>
</div>
<div class="TitleMenu">
	<ul class="MainUl">
	<% for(MenuDTO d0 : MenuDAO.getInstance().selectPrnts("",0)) { %> 
		<li>
		<a href="#" data-menu-name="<%=d0.getName().substring(0,1).toUpperCase()+d0.getName().substring(1)%>">
		<%=d0.getKor_name() %></a>
			<div class="SubMenu">
				<ul class="SubUl">
				<% for(MenuDTO d1 : MenuDAO.getInstance().selectPrnts(d0.getName(),1)) { %>		
					<div class="LowMenu">
						<li>
						<a href="#" data-menu-name="<%=d0.getName()+"/"+(d1.getName().substring(0,1).toUpperCase()+d1.getName().substring(1))%>">
						<%=d1.getKor_name() %></a>
						</li>
						<% for(MenuDTO d2 : MenuDAO.getInstance().selectPrnts(d0.getName(),d1.getName(),2)) { %>			
							<li>
							<a href="#" data-menu-name="<%=d0.getName()+"/"+d1.getName()+"/"+(d2.getName().substring(0,1).toUpperCase()+d2.getName().substring(1))%>">
							<%=d2.getKor_name() %></a>
							</li>
						<% } %>
					</div>
					<% if(d1.getSort() == 3){%>
				</ul>
				<ul class="SubUl">
					<% }%>
				<% } %>
				</ul>
			</div>
		</li>
	<%} %>
		<li id ="srch">
		<a href="#" data-menu-name="search">검색<img alt="" src="img/search_1.png" height="20px" display="block" margin="0px auto"/></a>
		</li>
	</ul>
</div>