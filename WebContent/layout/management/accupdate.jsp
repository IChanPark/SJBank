<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<h1 class="subTitle">계좌 정보</h1>
<input type="hidden"  value = "${data.account_number }" name ="acc"/>
<table>
	<tr>
		<td>계좌번호</td>
		<td>${data.account_number }</td>
	</tr>
	<tr>
		<td>계좌 비밀번호</td>
		<td><input type="text" name="pw" /></td>
	</tr>
	<tr>
		<td>계좌 별명 :</td>
		<td><input type="text" value="${data.alias }" name="alias" /></td>
	</tr>
	<tr>
		<td>계좌 상태</td>
		<td><select name="status" id="status">
				<option value="${data.status  }">${data.status  }</option>
				<c:choose>
					<c:when test="${data.status  =='활성'}">
						<option value="비활성">비활성</option>
					</c:when>
				</c:choose>
		</select></td>
	</tr>
	<tr>
		<td>간편패스워드 입력 : </td>
		<td><input type="text" name ="simple_pw"/></td>
	</tr>
</table>

<button data-menu-name="management/Accchange">변경</button>
