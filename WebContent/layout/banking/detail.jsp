<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="subTitle">상세</div>
<table class="AccInfo">
<tr >
	<td>계좌명</td><td colspan="3">${data.alias }</td>
</tr><tr>
	<td>고객명</td><td>${data.id }</td><td>계좌번호</td><td >${data.account_number }</td>
	</tr><tr>
	<td>신규일</td><td colspan="3" align="left" float="left">${data.register_date }</td>
	
	</tr><tr>
	<td>잔액</td><td><fmt:formatNumber value="${data.sum }" pattern="#,###원"/></td>
	<td>출금가능금액</td><td><fmt:formatNumber value="${data.sum }" pattern="#,###원"/></td>
		
	</tr><tr>
	<td>업무</td>
</tr>	
</table>

<div class="subTitle">내역</div>
<table class="AccInfo">
<tr>
</tr>
<c:forEach var="lo" items="${log }" varStatus="no">
<tr>
	<td>계좌번호</td><td>${lo.account_number }</td>
	<td>거래처</td><td>${lo.target }</td>
	<td>받는이</td><td>${lo.received }</td>
	<td>거래액</td><td><fmt:formatNumber value="${lo.sum }" pattern="#,###원"/></td>
	<td>메모</td><td>${lo.memo }</td>		
</tr>
</c:forEach>
</table>

<!-- <tr align="right">
		<td  colspan="6"><a href="InsertForm">???</a></td>  
	</tr> -->
