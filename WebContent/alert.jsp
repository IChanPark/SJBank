<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
alert('<%=request.getAttribute("msg")%>');
location.href="<%=request.getAttribute("goUrl")%>";
</script>