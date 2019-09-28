<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
	
	
	
	$('#ToConfirm').click(function(){
		
		var text = $("<div class='subTitle'>이체정보"+
				"<table border=''><tr><td>이체예정일시</td><td>출금계좌</td>"+
				"<td>입금은행<br>입금계좌</td><td>받는분</td><td>이체금액(원)</td>"+
				"<td>수수료(원)</td><td>받는통장<br>메모</td>	<td>CMS코드</td></tr>"+
				"<tr><td></td><td></td><td></td><td></td><td></td><td>"+
				"</td><td></td><td></td></tr></table></div>");
	
		var addBox = $("<div> 주의사항 비슷한 무언가<br><div class='subTitle'>보안매체 정보입력</div><br>"+"OTP 입력창 <input type='text' /></div>");
		
		var btn = $("<div><input type='button' value='완료' /></div>");
		addBox.append(btn);
		text.append(addBox);
		$("#mid").append(text);
	
	});
});
 </script>
    
    
    
<div clsss="subTitle">출금정보</div>
<table border="">
	<tr>
		<td>출금계좌번호</td>
		<td><input type="text" /></td>
	</tr>
	<tr>
		<td>계좌비밀번호</td>
		<td><input type="radio" name="sort" value="최근거래순"/>최근거래순 <input type="radio"  name="sort" value="과거거래순"/>과거거래순</td>
	</tr>
</table>


<br>
<br>
<div class="subTitle">입금정보</div>
<table border="">
	<tr>
		<td>입금은행</td>
		<td><input type="text" /></td>
	</tr>
	<tr>
		<td>입금계좌번호</td>
		<td><input type="text" /></td>
	</tr>
	<tr>
		<td>이체금액</td>
		<td><input type="text" /></td>
	</tr>
	<tr>
		<td>이체예정일</td>
		<td><input type="date" /></td>
	</tr>
	<tr>
		<td>이체시각설정</td>
		<td>
			<input type="radio" name="timeSet" value="오전7~8시 이체" >오전7~8시 이체
            <input type="radio" name="timeSet" value="오전9~10시 이체" >오전9~10시 이체
            <input type="radio" name="timeSet" value="오전11~12시 이체" >오전11~12시 이체
            <input type="radio" name="timeSet" value="오후1시~2시 이체" >오후1시~2시 이체
            <br>
            <input type="radio" name="timeSet" value="오후3시~4시 이체" >오후3시~4시 이체
            <input type="radio" name="timeSet" value="오후5시~6시 이체" >오후5시~6시 이체
            <input type="radio" name="timeSet" value="오후7시~8시 이체" >오후7시~8시 이체
            <input type="radio" name="timeSet" value="오후9시~10시 이체" >오후9시~10시 이체
		</td>
	</tr>
	<tr>
		<td>받는통장 메모</td>
		<td><input type="text" /></td>
	</tr>
	<tr>
		<td>내통장 메모</td>
		<td><input type="text" /></td>
	</tr>
	<tr>
		<td>CMS코드</td>
		<td><input type="text" /></td>
	</tr>
</table>
<br>
<br>

<div align="center"><input type="button" class="subTitle" id="ToConfirm" value="다음"/>
</div>



