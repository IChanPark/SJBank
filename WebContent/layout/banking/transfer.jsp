<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
$(document).ready(function(){

	$('.ToConfirm').click(function(){
		var addBox = $("<div> 주의사항 비슷한 무언가</div>");
		var btn = $("<div><button onclick=fff() >이체하기</button></div>");
	
		addBox.append(btn);
		$("#mid").append(addBox);
		$('.ToConfirm').hide(); 
	});	
	
	$("#target").change(function(){
		$("#transfer_receive").val($(this).val());
	});
});

function fff(){
	
	var f=document.paging;     
	f.hid_t.value = "banking/Trs";
    f.method="post";
    f.submit();
};

 </script>

<div class="subTitle">출금정보</div>
<table border="2">
	<tr>
		<td>출금계좌정보</td>
		<td><select name="acc">
			<c:forEach var="dto" items="${data }" varStatus="no">
				<option value=${dto.account_number }>${dto.account_number } [${dto.alias }]</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>계좌비밀번호</td>
		<td><input type="text" name="accpw" /></td>
	</tr>
</table>
<br>
<br>
<div class="subTitle">입금정보</div>
<table border="">
	<tr>
		<td>입금은행 :</td>
		<td><input type="text" name="transfer_receive" id="transfer_receive" maxlength="50"
			/ value="직접입력"> <select id="target">
				<option>SJ은행</option>
				<option>하나은행</option>
				<option>신한은행</option>
				<option>새마을금고</option>
				<option>농협</option>
				<option>국민은행</option>
		</select></td>
	</tr>
	<tr>
		<td>입금계좌정보 :</td>
		<td><input type="text" name="toAcc" /></td>
	</tr>
	<tr>
		<td>이체금액 :</td>
		<td><input type="text" name="money" /></td>
	</tr>
	<tr>
		<td>받는통장 메모 :</td>
		<td><input type="text" name="to_memo" /></td>
	</tr>
	<tr>
		<td>내 통장 메모 :</td>
		<td><input type="text" name="memo" /></td>
	</tr>
</table>
<br>
<br>
<div class="subTitle">부가정보</div>
<table>
	<tr>
		<td>CMS코드 :</td>
		<td><input type="text" name="cms" /></td>
	</tr>
</table>

<table>
	<tr>
		<td><input type="button" value="다음" class="ToConfirm" /></td>
	</tr>
</table>
