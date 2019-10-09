<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="subTitle">상세</div>
<table class="AccInfo">
	<tr>
		<td>계좌명</td>
		<td colspan="3">${data.alias }</td>
	</tr>
	<tr>
		<td>고객아이디</td>
		<td>${data.id }</td>
		<td>계좌번호</td>
		<td>${data.account_number }</td>
	</tr>
	<tr>
		<td>신규일</td>
		<td colspan="3" align="left" float="left">${data.register_date }</td>

	</tr>
	<tr>
		<td>잔액</td>
		<td><fmt:formatNumber value="${data.sum }" pattern="#,###원" /></td>
		<td>출금가능금액</td>
		<td><fmt:formatNumber value="${data.sum }" pattern="#,###원" /></td>
	</tr>
</table>

<div class="subTitle">내역</div>
<table class="AccInfo">
	<tr>
		<td>거래처</td>
		<td>거래대상계좌번호</td>
		<td>받는이</td>
		<td>거래액</td>
		<td>수수료</td>
		<td>메모</td>
		<td>보낸메모</td>
		<td>거래일</td>
		<td>거래상태</td>
	</tr>
	<c:forEach var="lo" items="${log }" varStatus="no">
		<tr>
			<td>${lo.target }</td>
			<td>${lo.to_account_number }</td>
			<td>${lo.received }</td>
			<td><fmt:formatNumber value="${lo.sum }" pattern="#,###원" /></td>
			<td>${lo.fee }</td>
			<td>${lo.memo }</td>
			<td>${lo.to_memo }</td>
			<td>${lo.register_date }</td>
			<td>${lo.status }</td>
			<c:set var="count" value="${count=count+1 }" />
		</tr>
	</c:forEach>
	<c:if test="${empty count }">
		<tr>
			<td colspan="7" align="center">거래내역이 없습니다.</td>
		</tr>
	</c:if>
</table>

<!-- <tr align="right">
		<td  colspan="6"><a href="InsertForm">???</a></td>  
	</tr> -->
