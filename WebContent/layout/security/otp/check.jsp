<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 


<div class=subTitle>OTP 관리</div>
<table>
	<c:forEach var="dto" items="${data }" varStatus="no">
	<tr>
		<td>등록OTP</td>	<td>${dto.type }</td>
	</tr>
	<tr>
		<td>상태</td>	<td>${dto.status } </td>
	</tr>
	<tr>
		<td>일련번호</td>	<td>${dto.serial }</td>
	</tr>
	
</c:forEach>
<c:if test="${empty data }">
<tr>
	<td>등록된 OTP가 없습니다.</td>
</tr>
</c:if>
<tr>
	<td><button data-menu-name = "security/otp/Inactive">OTP정지하기</button></td>
</tr>
</table>