<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- data-menu-name="management/Accchange" -->
<script>

$(document).ready(function(){

	$('#goChange').click(function(){
		var testSimpw = /[0-9]{4,6}/g;
		var testPw = /[0-9]{4}/g;
		var testAlias = /[0-9ㄱ-힣a-zA-Z]/g
		
		if(!testSimpw.test($("#simple_pw").val()) )
		{
			alert("간편 비밀번호 오류");
			return;
		}
		
		if(!testPw.test($("#pw").val()) )
		{
			alert("비밀번호 오류");
			return;
		}
		
		if(!testAlias.test($("#alias").val()) )
		{
			alert("계좌별명 오류");
			return;
		}
		
		document.paging.hid_t.value = "management/Accchange";
		document.paging.submit();
	});
});
</script>
<h1 class="subTitle">계좌 정보</h1>
<input type="hidden"  value = "${data.account_number }" name ="acc"/>
<table>
	<tr>
		<td>계좌번호</td>
		<td>${data.account_number }</td>
	</tr>
	<tr>
		<td>계좌 비밀번호</td>
		<td><input type="text" name="pw" id="pw" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" /></td>
	</tr>
	<tr>
		<td>계좌 별명 :</td>
		<td><input type="text" value="${data.alias }" name="alias" id="alias" onKeyup="this.value=this.value.replace(/[^0-9ㄱ-힣a-zA-Z]/g,'');"/></td>
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
		<td><input type="text" name ="simple_pw" id="simple_pw" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/></td>
	</tr>
</table>

<button  id="goChange">변경</button>
