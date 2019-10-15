<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready(function(){

	$('.check').click(function(){
		/* alert("눌림");
		var f=document.paging; 
		f.method="post";
	    f.action="layout/banking/check/test.jsp"
	    f.submit(); */
		
	    $.ajax({	//라디오 버튼 
			url:"layout/banking/check/reservationList.jsp",
			type:'post',
			data:{
				start : $("input[name=start]").val(),
				  end : $("input[name=end]").val(),
				  sort : $('input[name="sort"]:checked').val()
			},
			dataType:'json',
			success:function(qqq){
				
				$.each(qqq,function(i,e){
					var row = $("<tr><td>"+e.account_number+"</td>");
					row.append($("<td>"+e.register_date+"</td>"));
					row.append($("<td>"+e.memo+"</td>"));
					row.append($("<td>"+e.to_memo+"</td>"));
					row.append($("<td>"+e.time+"</td>"));
					row.append($("<td onclick=cancel("+e.seq+")> "+"여기"+"</td>"));
					row.append($("<td>"+e.seq+"</td></tr>"));
					
					$("#tot").append(row);
				});
			},
			error:function(qqq){
				console.log("오류오류");
			}
		});
	});
});
function cancel(qqq)
{
	document.paging.hid_t.value = "banking/check/Cancel";
	document.paging.seq.value=qqq;
	document.paging.submit();
}
</script>
<input type="hidden" name ="seq" />
<input type="hidden" name ="type" value="reserve" />
<div class="subTitle">예약이체 </div>
<table border="">
	<tr>
		<td>조회기간 선택</td>
		<td><input type="date" name="start" />~<input type="date" name="end"/></td>
	</tr>
	<tr>
		<td>조회결과 순서</td>
		<td><input type="radio" name="sort" value="desc"/>최근거래순 <input type="radio"  name="sort" value="asc"/>과거거래순</td>
	</tr>
	<tr>
		<td>계좌 선택</td>
		<td><select name="acc" id="acc">
		<c:forEach items="${data }" var = "dto">
		<option value="numbers">${dto.account_number }</option>
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><div class="check" />조회</td>
	</tr>
</table>
<br><br>
<div class="subTitle">예약이체 조회결과</div>
<br><br>
<table border="" id="tot">
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
<br><br>
<div align="center"><button class="subTitle" >예약이체 취소</button>
</div>