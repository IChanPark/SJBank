<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Saving.Saving_infoDTO"%>
<%@page import="jdbc.Saving.Saving_infoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="#" data-menu-name="admin/SavingAdd" id="login">등록하기</a>
<div class="subTitle">적금 상품</div>
<table class="AccInfo">
<tr >
	<td>상품명</td>
	<td>최저 연이자</td>
	<td>최고 연이자</td>
	<td>만기 개월</td>
	<td>가입타입</td>
	<td>정기/비정기</td>
	<td>이자지급방식 </td>
	<td>과세여부 </td>
	<td>우대구분 </td>
	<td>우대조건 내용</td>
	<td>우대이자율</td>
	<td>월납입 최소금액 </td>
	<td>월납입 최대금액</td>
	<td>일부해지가능여부</td>
	<td>재예치가능여부</td>
	<td>상태</td>
	<td>상품등록일</td>
	<td>상품삭제일</td>
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<tr>
		<td>${dto.product }</td>
		<td>${dto.min_interest }</td>
		<td>${dto.max_interest }</td>
		<td>${dto.month }</td>
		<td>${dto.type }</td>
		<td>${dto.regular}</td>
		<td>${dto.jnterest_type }</td>
		<td>${dto.tax }</td>
		<td>${dto.preferential }</td>
		<td>${dto.prf_content }</td>
		<td>${dto.prf_interest }</td>
		<td>${dto.min_sum }</td>
		<td>${dto.max_sum }</td>
		<td>${dto.partialization }</td>
		<td>${dto.retention }</td>
		<td>${dto.status }</td>
		<td>${dto.register_date }</td>
		<td>${dto.end_date }</td>
	</tr>
</c:forEach>
</table>