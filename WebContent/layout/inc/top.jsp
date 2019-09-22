<%@page import="jdbc.Menu.MenuDAO"%>
<%@page import="jdbc.Menu.MenuDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- 스플릿 사용도 -->
	
<link rel="stylesheet" type="text/css" href="css/bank_top.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/Menu_Fixed.js"></script>

<div id="menu0"><div>SJBank</div></div>
<form name="paging" action="index.jsp"/>
<input type="hidden" name="type" />
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
	</ul>
</div>
<script>
$(document).ready(function() {
	console.log( "Menu_Click.js!" );
	$("a[data-menu-name]").on("click", function() {	//메뉴 이동용
		var aa = $(this).data("menu-id");
		var f=document.paging; 
	    f.type.value = aa; 
	    f.method="post";
	    f.submit();
	});
});
</script>

