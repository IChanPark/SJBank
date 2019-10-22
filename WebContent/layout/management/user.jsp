<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="subTitle">고객정보 변경</div>
<br>
<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호</div><div class='infoMain_Value'>
<input type="password" id="pw" name="pw" /></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>전화번호</div><div class='infoMain_Value'>
<input type="text" id ="tel" name="tel" value="${data.tel }"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>성별</div><div class='infoMain_Value'>
<input type="radio" name="gen" value="남" checked>남<input type="radio" name="gen" value="여" >여</div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>직업</div><div class='infoMain_Value'>
<select name="position" ><option>학생</option><option>군인</option><option>회사원</option>
<option>주부</option><option>교수</option><option>백수</option></select></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>우편번호</div><div class='infoMain_Value'>
<input type="text" id="sample4_postcode" name="zipcode" placeholder="우편번호" readonly="readonly" value="${data.postal_code }">
<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>우편번호</div><div class='infoMain_Value'>
<input type="text" id="sample4_roadAddress" name="addr" placeholder="도로명주소" readonly="readonly" value="${fn:split(data.addr,'/')[0]}">
<input type="text" id="sample4_detailAddress" name="datail" placeholder="상세주소" value="${fn:split(data.addr,'/')[1]}"></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button onclick="allChk()">변경</button></div></div>

<script>
$(document).ready(function(){

	$('#pwChk').click(function(){
		

		alert($('input[name=pw]').val());
		alert($('input[name=pw1]').val());
		var testPw = /^[0-9]{4}$/;
		alert(testPw.test($('input[name=pw]').val()));
		if(!testPw.test($('input[name=pw]').val()))
		{
			alert("계좌 비밀번호 오류");
			return;
		} 
		
	    if($('input[name=pw]').val()==$('input[name=pw1]').val()){
	    	alert("	번호가 일치 합니다. ");
	    }
	    else{
	    	alert("번호가 일치 하지 않습니다.");
	    	return;
	    }
	    // 입력값이 있을경우에만 실행
	});	
});

function addChk(){
	
	//alert($('#sample4_postcode').val());
	if($('#sample4_postcode').val() == ""){
		alert("주소미입력");
		return false;
	}else 
		return true;
}

function allChk() {	//메뉴 이동용
	if( addChk() && ev()  && ev5()){
		alert("수정되었습니다.");
		document.paging.hid_t.value = "management/Changed/Update";
		document.paging.submit();	
	}
}; 

function ev(){if(!check(/^[\d]{4}$/,$('#pw'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
//function ev1(){if(!check(/^[\d]{4}$/,$('#simple_pw'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev5(){if(!check(/^(010)-([0-9]{3,4})-([0-9]{4})$/,$('input[name=tel]'),'형식에 맞게 입력해주세요.')){return false;}else{return true;}};	
</script>