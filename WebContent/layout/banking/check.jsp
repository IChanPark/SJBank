<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function(){
	$(".accountNumber").click(function() {	//메뉴 이동용
		alert("여기왔었음");
		var t=$(".accountNumber").val();
		
		alert(t);
		
		var f=document.paging; 
		f.type.value = "banking/Detail";
	    f.accountNumber.value = t;
	    
	    
	    f.method="post";
	    f.submit();
		
	});
});
</script>
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
			<td class = "ddd" value = "${dto.account_number }">${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td>${dto.register_date }</td>
			<td><fmt:formatNumber value="${dto.sum }" pattern="#,###원"/></td>
			<td><button type="button" class="accountNumber" value="${dto.account_number }">상세</a></td>
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
	<c:if test="${dto.type=='fund' }">
		<tr>
			<td>${dto.type }</td>
			<td class="ddd">${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td>${dto.register_date }</td>
			<td><fmt:formatNumber value="${dto.sum }" pattern="#,###원"/></td>
			<td><a href="#" data-menu-name="banking/Detail" name="accountNumber" value="${dto.account_number }" > 상세</a></td>
		</tr>
	</c:if>
</c:forEach>
</table>

<!-- <tr align="right">
		<td  colspan="6"><a href="InsertForm">???</a></td>  
	</tr> -->
