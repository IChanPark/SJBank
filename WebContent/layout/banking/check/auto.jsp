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
		<td>계좌번호</td>
		<td><input type="text" /></td>
		<td>조회구분</td><td> <input type="radio" name="division" value="정상"/>정상 <input type="radio" name="division" value="전체"/>전체 <input type="radio" name="division" value="해지"/>해지</td>
	</tr>
	<tr>
		<td colspan="4" align="center"><input type="button" value="조회" class="check" /></td>
		
	</tr>
</table>


<br>
<br>
<br>
<br>

<table border="">
	<tr>
		<td>선택</td>
		<td>입금은행<br>입금계좌</td>
		<td>받는분</td>
		<td>이체금액(원)</td>
		<td>이체기간</td>
		<td>해지일자</td>
		<td>이체일자</td>
		<td>이체주기</td>
		<td>받는통장<br>메모</td>
		<td>내통장<br>메모</td>
		<td>업무</td>
	</tr>
</table>


<br>
<br>

<div align="center"><button class="subTitle" >자동이체 취소</button>
</div>