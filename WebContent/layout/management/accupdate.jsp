<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready(function(){
	$('#goChange').click(function(){
		var testSimpw = /[0-9]{4,6}/g;
		var testPw = /[0-9]{4}/g;
		var testAlias = /[0-9ㄱ-힣a-zA-Z]/g
		
		if(!testSimpw.test($("#user_pw").val()) )
		{
			alert("사용자 비밀번호 오류");
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
<div class="subTitle">계좌 정보</div>
<br>
<div class='infoMain_Info'><div class='infoMain_Type'>계좌번호</div><div class='infoMain_Value'>
<input type="text"  value = "${data.account_number }" name ="acc" readonly="readonly"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>계좌 비밀번호</div><div class='infoMain_Value'>
<input type="password" name="pw" id="pw" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" /></div></div>	
<div class='infoMain_Info'><div class='infoMain_Type'>계좌 별명</div><div class='infoMain_Value'>	
<input type="text" value="${data.alias }" name="alias" id="alias" onKeyup="this.value=this.value.replace(/[^0-9ㄱ-힣a-zA-Z]/g,'');"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>계좌 상태</div><div class='infoMain_Value'>		
<select name="status" id="status">
	<option value="${data.status  }">${data.status  }</option>
		<c:choose><c:when test="${data.status  =='활성'}">
			<option value="비활성">비활성</option>
		</c:when></c:choose>
</select></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>사용자 비밀번호</div><div class='infoMain_Value'>
<input type="password" name ="user_pw" id="user_pw" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button id="goChange">변경</button></div></div>
