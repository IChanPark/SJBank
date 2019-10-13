<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("mainUrl", (String)request.getAttribute("mainUrl")); %>
<script>
alert('<%=request.getAttribute("msg")%>');
history.go(-1);
</script>