<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri ="http://java.sun.com/jsp/jstl/core" %>

<script>
$(document).ready(function(){

	$('.check').click(function(){
		alert("눌림")
		/* alert("눌림");
		var f=document.paging; 
		f.method="post";
	    f.action="layout/banking/check/test.jsp"
	    f.submit(); */
		
	    $.ajax({	//라디오 버튼 
			url:"layout/banking/check/delayList.jsp",
			type:'post',
			data:{
				account : $("select[name=account]").val(),
			},
			dataType:'json',
			success:function(qqq){
				
				$.each(qqq,function(i,e){
					var row = $("<tr><td>"+e.account_number+"</td>");
					row.append($("<td>"+e.register_date+"</td>"));
					row.append($("<td>"+e.memo+"</td>"));
					row.append($("<td>"+e.to_memo+"</td>"));
					row.append($("<td>"+e.trs_time+"</td></tr>"));
					$("#tot").append(row);
				});
			},
			error:function(qqq){
				console.log("오류오류");
			}
		});
	});
	    
	    
});



</script>

<div class="subTitle">지연이체</div>


<table border="">
	<tr>
		<td>계좌번호</td>
		<td>
			<select name="account">
				<c:forEach var="dto" items="${data }" >
					<option>${dto.account_number }</option>
				</c:forEach>
			</select>			
		</td>
		<td>조회구분</td><td> <input type="radio" name="division" value="y"/>정상 <input type="radio" name="division" value="전체"/>전체 <input type="radio" name="division" value="n"/>해지</td>
	</tr>
	<tr>
		<td colspan="4" align="center"><div class="check" />조회</td>

	</tr>
</table>
<br>
<br>

<div class="subTitle">지연이체 조회결과</div>
<br>
<br>



<table border="" id="tot">
	<tr>
		<td>선택</td>
		<td>이체예정일시</td>
		<td>출금계좌</td>
		<td>입금은행<br>입금계좌</td>
		<td>받는분</td>
		<td>이체금액(원)</td>
		<td>수수료(원)</td>
		<td>받는통장<br>메모</td>
		<td>내통장<br>메모</td>
		<td>CMS코드</td>
	</tr>
</table>


<br>
<br>

<div align="center"><button class="subTitle" >지연이체 취소</button>
</div>