<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready(function(){
	$("#tot").append("<tr><td colspan='9' align='center'>거래내역이 없습니다.</td></tr>");

	$('.check').click(function(){
		
		var start = $("input[name=start]").val();
		var end = $("input[name=end]").val() ;
		 
		if(start > end )
		{
			alert("날짜 설정이 잘못 되었습니다");
			return;
		}
		
		if( (start==null || start=="") || (end==null || end==""))
		{
			alert("날짜 설정을 전부 해주세요.");
			return;
		}
	    $.ajax({	//라디오 버튼 
			url:"layout/banking/check/reservationList.jsp",
			type:'post',
			data:{
				start : $("input[name=start]").val(),
				  end : $("input[name=end]").val(),
				  acc : $("select[name=acc]").val(),
				  sort : $('input[name="sort"]:checked').val()
			},
			dataType:'json',
			success:function(qqq){
				$("#tot").html("");
				var title = $("<tr><td>No</td><td>출금계좌</td><td>이체예정일시</td><td>입금은행 입금계좌</td>");
				title.append($("<td>이체금액(원)</td><td>메모</td><td>받는통장 메모</td><td>CMS코드</td><td>기능</td></tr>"));
				var len = 0;
				$("#tot").append(title);
				$.each(qqq,function(i,e){
					var row = $("<tr><td>"+(i+1)+"</td>")
					row.append($("<td>"+e.account_number+"</td>"));
					row.append($("<td>"+e.time+"</td>"));
					row.append($("<td>"+e.to_account_number+"</td>"));
					row.append($("<td>"+e.sum+"</td>"));
					row.append($("<td>"+e.memo+"</td>"));
					row.append($("<td>"+e.to_memo+"</td>"));
					row.append($("<td>"+e.cms+"</td>"));
					row.append($("<td onclick=cancel("+e.seq+")> "+"취소하기"+"</td></tr>"));
					len++;
					
					$("#tot").append(row);
				});
				
				if(len==0)
				{
					$("#tot").append("<tr><td colspan='9' align='center'>거래내역이 없습니다.</td></tr>");
				}
				
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
		<td><input type="radio" name="sort" value="asc" checked="checked"/>최근거래순 <input type="radio"  name="sort" value="desc"/>과거거래순</td>
	</tr>
	<tr>
		<td>계좌 선택</td>
		<td><select name="acc" id="acc">
		<c:forEach items="${data }" var = "dto">
		<option>${dto.account_number }</option>
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
<table border=""  id="tot" class="AccInfo">

	<tr>
		<td>No</td>
		<td>출금계좌</td>
		<td>이체예정일시</td>
		<td>입금은행 입금계좌</td>
		<td>이체금액(원)</td>
		<td>수수료(원)</td>
		<td>받는통장 메모</td>
		<td>CMS코드</td>
		<td>기능</td>
	</tr>
</table>
<br><br>