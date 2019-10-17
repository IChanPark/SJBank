<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="subTitle">사용자 리스트</div>
<div class='scrollB'>
<table class="info_table">
<tr >
	<th>아이디</th>
	<th>이름</th>
	<th>전화번호</th>
	<th>직업군</th>
	<th>주소</th>
	<th>우편번호</th>
	<th>상태</th>
	<th>가입일</th>
	<th>탈퇴</th>
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<tr>
		<td class = "uid">${dto.id }</td>
		<td>${dto.name }</td>
		<td>${dto.tel }</td>
		<td>${dto.job_group }</td>
		<td>${dto.addr }</td>
		<td>${dto.postal_code }</td>
		<td>${dto.status }</td>
		<td>${dto.register_date }</td>
		<td>${dto.end_date }</td>
	</tr>
</c:forEach>
</table>
</div>