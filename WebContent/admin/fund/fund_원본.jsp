<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery-3.4.1.min.js"></script>

<a href="#" data-menu-name="admin/Fund/FundAdd" id="login">등록하기</a>
<div class="subTitle">펀드 상품</div>
<table class="AccInfo">
<tr >
	<td>상품명</td>
	<td>초기기준가</td>
	<td>수정기준가</td>
	<td>유형</td>
	<td>과세여부</td>
	<td>국내/해외</td>
	<td>상품속성 </td>
	<td>선취수수료 </td>
	<td>년보수</td>
	<td>운용사</td>
	<td>섹터</td>
	<td>상태 </td>
	<td>펀드등록일</td>
	<td>펀드폐지일</td>
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	
	<tr>
		<td>${dto.product }</td>
		<td>${dto.price }</td>
		<td>${dto.price_modify }</td>
		<td>${dto.type }</td>
		<td>${dto.tax }</td>
		<td>${dto.area}</td>
		<td>${dto.property }</td>
		<td>${dto.first_fee }</td>
		<td>${dto.fee }</td>
		<td>${dto.management }</td>
		<td>${dto.sector }</td>
		<td>${dto.status }</td>
		<td>${dto.register_date }</td>
		<td>${dto.end_date }</td>
	</tr>
</c:forEach>
</table>