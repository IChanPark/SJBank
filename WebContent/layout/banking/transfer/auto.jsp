<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script>
$(document).ready(function(){
	
	$("#ToConfirm").click(function(){
		
		$("#mid").html("");
		var text =$("<div>주의사항이 들어갈겁니다 아무튼</div>");
		text.append($("<br><br><table border=''><tr><td>출금계좌정보</td><td><input type='text' name='acc'/></td></tr>"+
		"<tr><td>계좌비밀번호</td><td><input type='text' name='accpw'/></td></tr></table>"));

			
	
		var inaccount=$("<div class='subTitle'>입금정보</div>");
		
		inaccount.append($("<table><tr><td>입금은행</td><td></td></tr>"));
		inaccount.append($("<tr><td>입금계좌번호</td><td><input type='text' name ='toAcc'/></td></tr>"));
		inaccount.append($("<tr><td>이체금액</td><td><input type='text' name='sum' /></td></tr>"));
		inaccount.append($("<tr><td>이체주기</td><td><input type='text' name='period'/></td></tr>"));
		inaccount.append($("<tr><td>이체종류</td><td></td></tr>"));
		inaccount.append($("<tr><td>이체시작일/이체종료일<input type='date' name='start' />~<input type='date' name='end' /></td><td></td></tr>"));
		inaccount.append($("<tr><td>휴일이체구분</td><td></td></tr>"));
		inaccount.append($("<tr><td>말일이체여부</td><td></td></tr>"));
		inaccount.append($("<tr><td>Cms</td><td><input type='text' name='cms'/></td></tr>"));
		inaccount.append($("<tr><td>받는통장 메모</td><td><input type='text' name='to_memo'/></td></tr>"));
		inaccount.append($("<tr><td>내통장 메모</td><td><input type='text' name='memo' /></td></tr></table>"));
		
		
		$("#mid").append("<div class='subTitle'>출금정보</div>");
		$("#mid").append($("<br><br>"));
		$("#mid").append(text);
		$("#mid").append($("<br><br>"));
		var btn = $("<div><input type='button' value='다음' onclick='fff()' id='first' /></div>");
		$("#mid").append(inaccount);
		$("#mid").append(btn);
		});
	

});
function fff(){
	
	alert("????");
	$('input').remove('#first');
	var autoInfo =$("<br><br><div class='subTitle'>자동이체정보</div>");
	autoInfo.append($("<br><br><table border=''><tr><td>출금계좌</td><td>입금은행<br>입금계좌</td><td>받는분</td>	<td>이체금액</td><td>이체기간</td><td>이체주기</td><td>받는통장메모</td><td>내통장메모</td></tr>"+
	"<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr></table>"));
	
	$("#mid").append(autoInfo);
	
	var inputSecurityInfo=$("<br><br><div class='subTitle'>보안매체 정보입력</div>");
	inputSecurityInfo.append($("<br><div>OTP 번호입력 : <input type='text' /></div>"));
	$("#mid").append(inputSecurityInfo);
	
	var btn = $("<br><br><div><button onclick= goReg() >완료</button></div>");
	
	$("#mid").append(btn);
}

function goReg(){
	

	var f=document.paging; 
    
	f.hid_t.value = "banking/transfer/autoReg";
	
    f.method="post";
    f.submit();
	
	
};

 </script>


<div class="subTitle">자동이체 등록</div>

<table>
<div>계좌간/타행자동이체 약관 중 계좌간 자동이체(자동이체 방법,지급자금부족 때의 처리 등),
타행 자동이체(약관의 적용,신청 변경 밎 해지 등), 출금우선순위 등 약관에 동의하셔야 합니다.
</div>
<td>위 약관에 동의하십니까?</td> <td align="right"> <input type="radio" name="sign" value="동의하지않음"/>동의하지않음
<input type="radio" name="sign" value="동의함"/>동의함</td>
</table>
<div><input type="button" value="다음" id="ToConfirm"/></div>