<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
$(document).ready(function(){

	$('.check').click(function(){
		
		alert("눌림");
		var f=document.paging; 
		 
	    f.method="post";
	    f.action="layout/banking/check/test.jsp"
	    f.submit();
	
		
	});
});



</script>
<table border="">
	<tr>
		<td>조회기간 선택</td>
		<td><input type="date" name="start" />~<input type="date" name="end"/></td>
	</tr>
	<tr>
		<td>조회결과 순서</td>
		<td><input type="radio" name="sort" value="최근거래순"/>최근거래순 <input type="radio"  name="sort" value="과거거래순"/>과거거래순</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="조회" class="check" /></td>
	</tr>
</table>

<br>
<br>
<br>
<br>

<table border="">
	<tr>
		<td>선택</td>
		<td>이체예정일시</td>
		<td>출금계좌</td>
		<td>입금은행<br>입금계좌</td>
		<td>받는분</td>
		<td>이체금액(원)</td>
		<td>수수료(원)</td>
		<td>받는통장<br>메모</td>
		<td>CMS코드</td>
	</tr>
</table>


<br>
<br>

<div align="center"><button class="subTitle" >예약이체 취소</button>
</div>