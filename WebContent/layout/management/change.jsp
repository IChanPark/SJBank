<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<br>
<h1>고객정보 변경</h1> <!-- updateUser -->
	<table border="">

		<tr>	
			<td>비밀번호</td>
			<td><input type="text" name="pw" value="${data.pw } " onfocusout='ev()'/></td>			
		</tr>
		<tr>
			<td>간편 비밀 번호</td>
			<td><input type="text" name="simple_pw" value="${data.simple_pw } " onfocusout='ev1()' /></td>
		</tr>

		<tr>	
			<td>전화번호</td>
			<td><input type="text" name="tel" value="${data.tel }" onfocusout='ev2()'/></td>
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
				<input type="text" name="email1" maxlength="50"  value="${fn:split(data.email,'@')[0]}" onfocusout='ev3()'/>@
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
				<div><div onclick="allChk()">수정</div><input type="reset" value="취소"/></div>
				
			</td>
		</tr>
	</table>
<script>	

 function check1(re, what, message) {
    if(re.test(what.val())) {
    	$('select[name=email2]').next().remove();
        return true;
    }
    what.val('');
  	//what.focus();
  	$('select[name=email2]').next().remove();
  	$('select[name=email2]').after("<div style='color: #d6bb50'>"+message+"</div>");
};
 
function addChk(){
	if($('#sample4_postcode').val() == ""){
		alert("주소미입력");
		return false;
	}else 
		return true;
}


function allChk() {	//메뉴 이동용
	
	
	if(ev()&&ev2()&&ev3()){
		alert("변경되었습니다.");
		document.paging.hid_t.value = "management.Changed/Update";
		document.paging.submit();
	}
};

function ev(){if(!check(/^[\d]{4}$/,$('input[name=pw]'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};	
function ev1(){if(!check(/^[\d]{4}$/,$('input[name=simple_pw]'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};	
function ev2(){if(!check(/^(010)-([0-9]{3,4})-([0-9]{4})$/,$('input[name=tel]'),'010-xxxx-xxxx 같은 양식으로 입력하시오.')){return false;}else{return true;}};
function ev3(){if(!check1(/^[A-Za-z0-9_-]{4,7}$/,$('input[name=email1]'),'형식에 맞게 입력해주세요.')){return false;}else{return true;}};


var gogo = "layout/managment/user.jsp";

$.ajax({	
url:gogo,
type:'post',
data:{	 pw 	: $('input[name=pw]').val(),
		simple_pw : $('input[name=simple_pw]').val(),
		tel : $('input[name=tel]').val(),
		email1 : $('input[name=email1]').val()
		
	
	}
});	

 
 
</script> 
	
	
	
	