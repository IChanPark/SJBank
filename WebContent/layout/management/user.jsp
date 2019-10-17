<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="subTitle">고객정보 변경</div>
<br>
	<table border="">

		<tr>	
			<td>비밀번호</td>
			<td><input type="text" id="pw" name="pw" /></td>			
		</tr>
		<tr>
			<td>간편 비밀 번호</td>
			<td><input type="text" id = "simple_pw"  name="simple_pw" /></td>
		</tr>

		<tr>	
			<td>전화번호</td>
			<td><input type="text" id ="tel" name="tel" value="${data.tel }"/></td>
		</tr>
		<tr>
		<td>성별</td>
		<td>
			<input type="radio" name="gen" value="남" checked>남
            <input type="radio" name="gen" value="여" >여
		</td>
		</tr>
		<tr>	
			<td>이메일</td>
			<td>
				<input type="text" id = "email1" name="email1" maxlength="50"  value="${fn:split(data.email,'@')[0]}"/>@
				<select name="email2">
					<option>naver.com</option>
					<option>daum.net</option>
					<option>gmail.com</option>
					<option>nate.com</option>
					<option>yahoo.com</option>
				</select>
			</td>	
		</tr>		
		<tr>	
			<td>직업</td>
			<td>	
				<select name="position" >
					<option>학생</option>
					<option>군인</option>
					<option>회사원</option>
					<option>주부</option>
					<option>교수</option>
					<option>백수</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td><input type="text" id="sample4_postcode" name="zipcode" placeholder="우편번호" readonly="readonly" value="${data.postal_code }">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></td> 
		</tr>
		<tr>
		    <td>주소</td>
			<td><input type="text" id="sample4_roadAddress" name="addr" placeholder="도로명주소" readonly="readonly" value="${fn:split(data.addr,'/')[0]}">
			<input type="text" id="sample4_detailAddress" name="datail" placeholder="상세주소" value="${fn:split(data.addr,'/')[1]}"> </td>
		</tr>

		<tr>
		
			<td colspan="2" align="right">
				
				<div onclick="allChk()">변경</div>
			</td>
		</tr>
	</table>
	
	

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


function check3(re, what, message) {
    if(re.test(what.val())) {
    	$('select[name=email2]').next().remove();
        return true;
    }
    what.val('');
  	//what.focus();
  	$('select[name=email2]').next().remove();
  	$('select[name=email2]').after("<div style='color: #4375DB'>"+message+"</div>");
};


 function allChk() {	//메뉴 이동용
	 
	
	if( addChk() && ev()  && ev1() && ev5() && ev6()){
		alert("수정되었습니다.");
		document.paging.hid_t.value = "management/Changed/Update";
		document.paging.submit();
	
	}
}; 


function ev(){if(!check(/^[\d]{4}$/,$('#pw'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev1(){if(!check(/^[\d]{4}$/,$('#simple_pw'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};

function ev5(){if(!check(/^(010)-([0-9]{3,4})-([0-9]{4})$/,$('input[name=tel]'),'형식에 맞게 입력해주세요.')){return false;}else{return true;}};	
function ev6(){if(!check3(/^[A-Za-z0-9_]{4,7}$/,$('input[name=email1]'),'형식에 맞게 입력해주세요.')){return false;}else{return true;}};

</script>
	
	