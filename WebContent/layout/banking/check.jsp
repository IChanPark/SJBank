<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="subTitle">입/출금 계좌</div>
<table class="AccInfo">
<tr >
	<td>계좌명</td>
	<td>계좌번호</td>
	<td>신규일</td>
	<td>최근거래일</td>
	<td>잔액</td>
	<td>업무</td>
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<c:if test="${dto.type=='deposit' }">
		<tr>
			<td>${dto.type }</td>
			<td>${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td>${dto.register_date }</td>
			<td><fmt:formatNumber value="${dto.sum }" pattern="#,###원"/></td>
			<td><a href="#" data-menu-name="banking/Detail" accountNumber="${dto.account_number }">asd</a></td>
		</tr>
	</c:if>
</c:forEach>
</table>

<div class="subTitle">예금/적금/신탁 계좌</div>
<table class="AccInfo">
	<tr>
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
			<td><fmt:formatNumber value="${dto.sum }" pattern="#,###원"/></td>
			<td><a href="#" data-menu-name="Detail">asfasf</a></td>
		</tr>
	</c:if>
</c:forEach>
</table>

<!-- <tr align="right">
		<td  colspan="6"><a href="InsertForm">???</a></td>  
	</tr> -->
