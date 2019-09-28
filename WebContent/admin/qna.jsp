<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

$(document).ready(function() {
	$(".uid").on("click", function() {
		
		//var b = $(this).text();
		$.ajax({
			url:"admin/list.jsp",
			type:'get',
			data:{nn : $(this).text()},
			 ////json을 안하면 문자열로 , json 처리하면 object로 묶어서 받음
			success:function(qqq){
				console.log(qqq);
			},
			error:function(qqq){
				$("#tot").html(qqq.responseText);
			}
			
		});
	});
});


</script>

<div class="subTitle">사용자 리스트</div>
<table class="AccInfo">
<tr >
	<td>아이디</td>
	<td>이름</td>
	<td>전화번호</td>
	<td>이메일</td>
	<td>직업군</td>
	<td>주소</td>
	<td>우편번호</td>
	<td>계정상태</td>
	<td>가입일</td>
	<td>탈퇴일</td>
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<tr>
		<td class = "uid">${dto.id }</td>
		<td>${dto.name }</td>
		<td>${dto.tel }</td>
		<td>${dto.email }</td>
		<td>${dto.job_group }</td>
		<td>${dto.addr }</td>
		<td>${dto.postal_code }</td>
		<td>${dto.status }</td>
		<td>${dto.register_date }</td>
		<td>${dto.end_date }</td>
	</tr>
</c:forEach>
</table>