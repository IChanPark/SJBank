<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%request.setCharacterEncoding("UTF-8"); %>
<script>
	$(document).ready(function() {
		$('#ToConfirm').click(function() {
				$(".indata").attr("readOnly",true);
			
				var text = $("<div class='subTitle'>이체정보"
				+ "<table border=''><tr><td>이체예정일시</td><td>출금계좌</td>"
				+ "<td>입금은행<br>입금계좌</td><td>받는분</td><td>이체금액(원)</td>"
				+ "<td>수수료(원)</td><td>받는통장<br>메모</td>	<td>CMS코드</td></tr>"
				+ "<tr><td>"+$('#time').val()+"<br>"+$('input[name="scheduled_date"]:checked').val()+"</td><td>"+$('#acc').val()+"</td><td>"+$('#toAcc').val()+"</td><td></td><td></td><td>"
				+ "</td><td></td><td></td></tr></table></div>");
				var addBox = $("<div> 주의사항 비슷한 무언가<br><div class='subTitle'>보안매체 정보입력</div><br>"
					+ "OTP 입력창 <input type='text' /></div>");
				var btn = $("<div><button onclick=goReg()>완료</button></div>");
				addBox.append(btn);
				text.append(addBox);
				$("#mid").append(text);
				$(this).hide();
	});
});

function goReg(){

	var f=document.paging; 
    
	f.hid_t.value = "banking/transfer/ReservationReg";
	
    f.method="post";
    f.submit();
	
};
	
</script>

<div class="subTitle">예약이체 등록</div>

<div clsss="subTitle">출금정보</div>
<table border="" class="indata">
	<tr>
		<td>출금계좌번호</td>
		<td><input type="text" name="acc" id="acc" class="indata"/></td>
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
		<td><input type="text" name="bank" id="bank" class="indata"/></td>
	</tr>
	<tr>
		<td>입금계좌번호</td>
		<td><input type="text" name="toAcc" id="toAcc" class="indata"/></td>
	</tr>
	<tr>
		<td>이체금액</td>
		<td><input type="text" name="sum" id="sum" class="indata"/></td>
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
		<td><input type="text" name="to_memo"  class="indata"/></td>
	</tr>
	<tr>
		<td>내통장 메모</td>
		<td><input type="text" name="memo" class="indata"/></td>
	</tr>
	<tr>
		<td>CMS코드</td>
		<td><input type="text" name="cms"  class="indata"/></td>
	</tr>
</table>
<br><br>
<div align="center">
	<input type="button" class="subTitle" id="ToConfirm" value="다음" />
</div>