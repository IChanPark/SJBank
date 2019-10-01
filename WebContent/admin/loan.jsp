<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Loan.LoanDAO"%>
<%@page import="jdbc.Loan.LoanDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="#" data-menu-name="admin/LoanAdd" id="login">등록하기</a>
<div class="subTitle">대출 상품</div>
<table class="AccInfo">
<tr >
	<td>상품명</td>
	<td>최저 금리</td>
	<td>최고 금리</td>
	<td>만기 개월</td>
	<td>대출 종류</td>
	<td>대출한도</td>
	<td>우대구분 </td>
	<td>우대조건 내용</td>
	<td>우대금리</td>
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
		<td>${dto.loanlimit }</td>
		<td>${dto.preferential }</td>
		<td>${dto.prf_content }</td>
		<td>${dto.prf_interest }</td>
		<td>${dto.status }</td>
		<td>${dto.register_date }</td>
		<td>${dto.end_date }</td>
	</tr>
</c:forEach>
</table>