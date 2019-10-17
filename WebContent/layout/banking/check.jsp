<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function(){
	$(".accountNumber").click(function() {	//메뉴 이동용
		var t=$(this).val();
		var f=document.paging; 
		f.hid_t.value = "banking/Detail";
		f.accountNumber.value = t;
	    f.method="post";
	    f.submit();
	});
});
</script>
<input type="hidden" name="accountNumber" />
<div class="subTitle">입/출금 계좌</div>
<table class="info_table">
<tr >
	<th>계좌명</th>
	<th>별명</th>
	<th>계좌번호</th>
	<th>신규일</th>
	<th>잔액</th>
	<th>상태</th>
	<th>업무</th>
</tr>

<c:forEach var="dto" items="${data }" varStatus="no">
	<c:if test="${dto.type=='예금' }">
		<tr>
			<td>${dto.type }</td>
			<td>${dto.alias }</td>
			<td>${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td><fmt:formatNumber value="${dto.sum }" pattern="#,###원"/></td>
			<td>${dto.status }</td>
			<td><button type="button" class="accountNumber" value="${dto.account_number }">상세</button></td>
		</tr>
		<c:set var="dsum" value="${dsum=dsum*1+dto.sum*1 }" />
	</c:if>
</c:forEach>
<c:if test="${empty dsum }">
<tr>
	<td colspan="6" align="center">개설된 계좌가 없습니다.</td>
</tr>
</c:if>
</table>
<br>
<div align="right">
입/출금계좌 총액<fmt:formatNumber value="${dsum }" pattern=" #,###원"/>
</div>
<div class="subTitle">상품 계좌</div>
	<table class="info_table">
	<tr>
		<th>계좌명</th>
		<th>계좌번호</th>
		<th>신규일</th>
		<th>최근거래일</th>
		<th>잔액</th>
		<th>업무</th>
	</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<c:if test="${dto.type!='예금' }">
		<tr>
			<td>${dto.type }</td>
			<td class="ddd">${dto.account_number }</td>
			<td>${dto.register_date }</td>
			<td>${dto.register_date }</td>
			<td><fmt:formatNumber value="${dto.sum }" pattern="#,###원"/></td>
			<td><button type="button" class="accountNumber" value="${dto.account_number }">상세</a></td>
		</tr>
		<c:set var="psum" value="${psum=psum*1+ dto.sum*1 }" />
	</c:if>
	
</c:forEach>
<c:if test="${empty psum }">
<tr>
	<td colspan="6" align="center">개설된 계좌가 없습니다.</td>
</tr>
</c:if>
</table>
<br>
<div align="right">
상품 계좌 총액 <fmt:formatNumber value="${psum }" pattern="  #,###원"/>
</div>

