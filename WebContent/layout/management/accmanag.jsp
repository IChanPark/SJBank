<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="subTitle">계좌 관리</div>
<br>
<h3>계좌 선택</h3>
<select name="acc" id="acc">
<c:forEach  items="${data }" varStatus="no" var="dto">
	<option value="${dto.account_number }">${dto.account_number }</option>
</c:forEach>
</select>

<a href="#" data-menu-name="management/Accupdate">변경</a>