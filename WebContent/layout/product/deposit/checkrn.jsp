<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>입/출금 계좌</h2>
<table border="" width="100%">
<tr align="center">
	<td>계좌명</td>
	<td>계좌번호</td>
	<td>신규일</td>
	<td>최근거래일</td>
	<td>잔액</td>
	<td>업무</td>
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<c:if test="${dto.type=='저축예금' }">
		<tr>
			<td>${dto.type }</td>
			<td>${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td>${dto.register_date }</td>
			<td>${dto.sum }</td>
			<td><a href="Detail?id=${dto.id }">조회</a></td>
		</tr>
	</c:if>
</c:forEach>
</table>
<h2>예금/적금/신탁 계좌</h2>
<table border="" width="100%">
	<tr align="center">
		<td>계좌명</td>
		<td>계좌번호</td>
		<td>신규일</td>
		<td>최근거래일</td>
		<td>잔액</td>
		<td>업무</td>
	</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<c:if test="${dto.type=='펀드' }">
		<tr>
			<td>${dto.type }</td>
			<td>${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td>${dto.register_date }</td>
			<td>${dto.sum }</td>
			<td><a href="Detail?id=${dto.id }">조회</a></td>
		</tr>
	</c:if>
</c:forEach>

</table>

<!-- <tr align="right">
		<td  colspan="6"><a href="InsertForm">???</a></td>  
	</tr> -->
