<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date dd = new Date();
String tod = sdf.format(dd);

%>

<script>

$(document).ready(function() {
		
		alert("2345");
		$('#ToConfirm').click(function() {
			
			var temp = new Date();
			var today = (temp.getYear()+1900)+"-"+(temp.getMonth()+1)+"-"+temp.getDate();
			alert(today);
			alert($('#time').val());
				if($('#time').val() < today )
				{
					alert("오늘 보다 이전 날짜는 설정 할수 없습니다.");
					return;
				}
			
				$(".indata").attr("readOnly",true);
			
				var text = $("<div class='subTitle'>이체정보"
				+ "<table border=''><tr><td>이체예정일시</td><td>출금계좌</td>"
				+ "<td>입금은행<br>입금계좌</td><td>받는분</td><td>이체금액(원)</td>"
				+ "<td>수수료(원)</td><td>받는통장<br>메모</td>	<td>CMS코드</td></tr>"
				+ "<tr><td>"+$('#time').val()+"<br>"+$('input[name="scheduled_date"]:checked').val()+"</td><td>"+$('#acc').val()+"</td><td>"+$('#toAcc').val()+"</td><td>"+$('#bank').val()+"</td><td>"+$('#sum').val()+"</td><td>"
				+ "</td><td>"+$('#to_memo').val()+"</td><td>"+$('#cms').val()+"</td></tr></table></div>");
				
				var addBox = $("<div><h1 class='subTitle'>알아두세요</h1><br><br><br>-예약이체 실행시 잔액부족,입금은행장애,기타 사유로 인하여 고객님께서 등록하신 예약이체건이 미처리 될 수 있습니다.<br>당일 처리 예정시간 이후 처리결과 조회를 반드시 확인하여 주시기 바랍니다.");
				addBox.append("-예약이체시 수수료는 실제 자금이체 거래가 실행될 때 확정 됩니다. <br></div>")
				var btn = $("<button onclick=goReg()>완료</button>");
				
				addBox.append(btn);
				text.append(addBox);
				$("#mid").append(text);
				$(this).hide();
		});
		
	
		
});

function throwNum()
{
	if (!(e.keyCode >=37 && e.keyCode<=40)) {
		var v = $(this).val();
		$(this).val(v.replace(/[^a-z0-9]/gi,''));
	}
};




function goReg() {
		var f = document.paging;
		f.hid_t.value = "banking/transfer/ReservationReg";
		f.method = "post";
		f.submit();
		
};

</script>

<div class="subTitle">예약이체 등록</div>
<div clsss="subTitle">출금정보</div>
<table border="" class="indata">
	<tr>
		<td>출금계좌번호</td>
		<td>
			<select name="acc" id="acc" class="indata">
				<c:forEach var="dto" items="${data }" varStatus="no">
					<option value=${dto.account_number }>${dto.account_number } [${dto.alias }]</option>
				</c:forEach>
			</select>
		
		</td>
	</tr>
	<tr>
		<td>계좌비밀번호</td>
		<td><input type="text" name="accpw" id="accpw" class="indata"/></td>
	</tr>
</table>

<br>
<br>
<div class="subTitle">입금정보</div>
<table border="" class="indata">
	<tr>
		<td>입금은행</td>
		<td><input type="text" placeholder="숫자,영문,한글로 구성된 은행명을 적어 주세요" onkeyup="this.value=this.value.replace(/[^0-9ㄱ-힣a-zA-Z]/g,'')" name="bank" id="bank" class="indata"/></td>
	</tr>
	<tr>
		<td>입금계좌번호</td>
		<td><input type="text" placeholder="숫자 및 -로 구성된 번호를 입력해 주세요" name="toAcc" id="toAcc" class="indata"  onkeyup="this.value=this.value.replace(/[^0-9-]/g,'')" /></td>
	</tr>
	<tr>
		<td>이체금액</td>
		<td><input type="text" placeholder="계좌에 잔액이 없을시 취소 될 수 있습니다." onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" name="sum" id="sum" class="indata"/></td>
	</tr>
	<tr>
		<td>이체예정일</td>
		<td><input type="date" name="time" id= "time" class="indata"/></td>
	
	</tr>
	<tr>
		<td>이체시각설정</td>
		<td><input type="radio" name="scheduled_date" value="7:00:00" id="scheduled_date" class="indata">오전7~8시
			이체 <input type="radio" name="scheduled_date" value="9:00:00" id="scheduled_date" class="indata">오전9~10시
			이체 <input type="radio" name="scheduled_date" value="11:00:00" id="scheduled_date" class="indata">오전11~12시
			이체 <input type="radio" name="scheduled_date" value="13:00:00" id="scheduled_date" class="indata">오후1시~2시
			이체 <br> <input type="radio" name="scheduled_date" value="15:00:00" id="scheduled_date" class="indata">오후3시~4시
			이체 <input type="radio" name="scheduled_date" value="17:00:00" id="scheduled_date" class="indata">오후5시~6시
			이체 <input type="radio" name="scheduled_date" value="19:00:00" id="scheduled_date" class="indata">오후7시~8시
			이체 <input type="radio" name="scheduled_date" value="21:00:00" id="scheduled_date" class="indata">오후9시~10시
			이체</td>
	</tr>
	<tr>
		<td>받는통장 메모</td>
		<td><input type="text" name="to_memo" id="to_memo" class="indata"/></td>
	</tr>
	<tr>
		<td>내통장 메모</td>
		<td><input type="text" name="memo" id = "memo" class="indata"/></td>
	</tr>
	<tr>
		<td>CMS코드</td>
		<td><input type="text" placeholder="필수사항이 아닙니다." name="cms" id ="cms" class="indata"/></td>
	</tr>
</table>
<br><br>
<div align="center">
	<input type="button" class="subTitle" id="ToConfirm" value="다음" />
</div>