<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
#con{width:1000px;margin :0 auto; height: 1200px; border-left: 1px solid white; border-right: 1px solid white;}
#bot{height:40px; background: #404040;}
</style>   
<% if(request.getAttribute("mainUrl") == null){
	request.setAttribute("mainUrl","mid");
}%>

</head>
<body>
	<div><jsp:include page="layout/inc/top.jsp" /></div>
	<div id = con><jsp:include page="${mainUrl }.jsp" /></div>
	<div id=bot><jsp:include page="layout/inc/bottom.jsp" /></div>
</body>
</html>