<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class=subTitle>OTP 관리</div>
<table>
	<c:forEach var="dto" items="${data }" varStatus="no">
	<c:if test="${dto.status=='활성' }">
	<tr>
		<td>등록OTP</td>	<td>${dto.type }</td>
	</tr>
	<tr>
		<td>상태</td>	<td>${dto.status } </td>
	</tr>
	<tr>
		<td>일련번호</td>	<td>${dto.serial }</td>
	</tr>
	</c:if>
</c:forEach>
</table>