<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
$(document).ready(function(){

	$('.ToConfirm').click(function(){

		var testAccpw = /^[0-9]{4}$/g;
		var testMoney = /^[0-9]{1,9}$/g;
		var testToacc = /^[0-9]{3,4}[-][0-9]{3,4}[-][0-9]{3,7}[-][0-9]{3,7}$/g;
		var testBank = /^[0-9가-힣a-zA-Z]{1,8}$/g
		
		
		if(!testAccpw.test($("#accpw").val()) )
		{
			alert("계좌 비밀번호 오류");
			return;
		}
		
		if(!testToacc.test($("#toAcc").val() ) )
		{
			alert("보낼 계좌 오류");
			return;
		}
		
		
		if(!testMoney.test($("#money").val() ) )
		{
			alert("금액값 오류");
			return;
		}
		
		if(!testBank.test($("#transfer_receive").val() ) )
		{
			alert("보낼 은행 오류");
			return;
		}
		
		$(".indata").attr("readOnly",true);
		
		
		var addBox = $("<br><br><br><div class='subTitle'>알아두세요</div><br><br>");
		addBox.append("<div>-친구 지인 및 거래처에서 인터넷메신저 또는 휴대전화 문자메세지를 통해 송금을 요구받은 경우에는 <br>반드시 이체 전 전화로 사실관계여부를 확인해주시기 바랍니다.</div>");
		addBox.append("<div>-받는 통장메모는 받는분의 통장에 인자하여 드리는 메세지 입니다.</div>");
		addBox.append("<div>-CMS코드는 받는분이 출금인을 식별하기 위한 코드입니다. 일반적으로는 입력하실 필요가 없습니다.</div>");
		
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

<div class= 'infoBox'>
<div class='infoMain_Info'><div class='infoMain_Type'>출금계좌정보</div><div class='infoMain_Value'>
<select name="acc">
	<c:forEach var="dto" items="${data }" varStatus="no">
		<option value=${dto.account_number }>${dto.account_number } [${dto.alias }] (사용가능금액 : ${dto.sum} 원)</option>
	</c:forEach>
</select>  </div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>계좌비밀번호</div><div class='infoMain_Value'>
<input placeholder="계좌 비밀번호 4자리를 입력해 주십시오" type="password" name="accpw" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" class = "indata" id="accpw"/>
</div></div></div>

<div class="subTitle">입금정보</div>

<div class= 'infoBox'>
<div class='infoMain_Info'><div class='infoMain_Type'>입금은행</div><div class='infoMain_Value'>
<input type="hidden" name="transfer_receive" id="transfer_receive" class = "indata"  value='SJ은행' />
<select id="target"><option>SJ은행</option><option>하나은행</option><option>신한은행</option>
<option>새마을금고</option><option>농협</option><option>국민은행</option></select></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>입금계좌정보</div><div class='infoMain_Value'>
<input type="text" placeholder="-를 포함해서 입력해 주세요" name="toAcc" onKeyup="this.value=this.value.replace(/[^0-9-]/g,'');" class = "indata" id="toAcc"/>
</div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>이체금액</div><div class='infoMain_Value'>
<input type="text" placeholder="이체한도는 10억미만입니다." name="money" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" class = "indata" id="money"/>
</div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>받는통장 메모</div><div class='infoMain_Value'>
<input type="text" name="to_memo" class = "indata" onKeyup="this.value=this.value.replace(/[^0-9ㄱ-힣a-zA-Z]/g,'');"  /></td>
</div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>내 통장 메모</div><div class='infoMain_Value'>
<input type="text" name="memo" class = "indata" onKeyup="this.value=this.value.replace(/[^0-9ㄱ-힣a-zA-Z]/g,'');" /></td>
</div></div></div>
<div class="subTitle">부가정보</div>
<div class= 'infoBox'>
<div class='infoMain_Info'><div class='infoMain_Type'>CMS코드</div><div class='infoMain_Value'>
<input type="text" name="cms" class = "indata" onKeyup="this.value=this.value.replace(/[^0-9ㄱ-힣a-zA-Z]/g,'');"/></td>
</div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>이체</div><div class='infoMain_Value'>
<button class="ToConfirm">이체하기</button></div></div> </td>
</div></div></div>