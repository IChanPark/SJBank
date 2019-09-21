<%@page import="jdbc.Menu.MenuDAO"%>
<%@page import="jdbc.Menu.MenuDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- 스플릿 사용도 -->
	
<link rel="stylesheet" type="text/css" href="css/bank_top.css" />
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/Script.js"></script>
<style>
.fo{
	background-color: #555;
	border-bottom: 1px solid #000;
	border-right: 1px solid #000;
}

</style>
<script>
	$(tot).addClass("dover");
	$(document).ready(function(){	
		$("a").on("click",function(){
			$(this).addClass("dover");
			/* $(this).css({
				background: "none",
				border:  "none"
			}); */
			
		});
	}
</script>


<div id="menu0"><div>SJBank</div></div>
<form class ="fo" action="Index"></form>
<div class="TitleMenu">
	<ul class="MainUl">
	<% for(MenuDTO dto : MenuDAO.getInstance().selectPrnts("",0)) { %> 
		<li><a href="Reg?page=<%=dto.getName()%>"><%=dto.getKor_name() %></a>
			<div class="SubMenu">
				<ul class="SubUl">
				<% for(MenuDTO d1 : MenuDAO.getInstance().selectPrnts(dto.getName(),1)) { %>		
					<div class="LowMenu">
						<li><a href="#"><%=d1.getKor_name() %></a></li>
						<% for(MenuDTO d2 : MenuDAO.getInstance().selectPrnts(dto.getName(),d1.getName(),2)) { %>			
							<li><a href="Reg?page=<%=dto.getName()+"/"+d1.getName()+"/"+d2.getName()%>"><%=d2.getKor_name() %></a></li>
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