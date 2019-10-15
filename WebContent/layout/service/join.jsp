<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<h2>회원가입</h2> 
	<table border="">
		<tr>
			<td>ID</td>							
			<td><input type="text" name="id" maxlength="50"/><input type="button" value="중복확인" onclick="idChk()"></td>
			
		</tr>
		<tr>	
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw"/></td>		
		</tr>	
		<tr>	
			<td>비밀번호 중복확인</td>
			<td><input type="password" name="pw1" id="pw1" onfocusout='ev()'/>		
		</tr>		
		<tr>	
			<td>간편 비밀 번호</td>
			<td><input type="password" name="simple_pw" onfocusout='ev3()' /></td>
		<tr>	
			<td>계좌비밀번호</td>
			<td><input type="password" name="acc_pw" onfocusout='ev4()' /></td>			
		</tr>	
		<tr>
		<td>이름</td>							
			<td><input type="text" name="name" maxlength="50" onfocusout='ev2()'  id="name" />		
		</tr>
		<tr>	
			<td>전화번호</td>
			<td><input type="text" name="tel" onfocusout='ev5()' /></td>
		</tr>
		<tr>
		<td>성별</td>
		<td>
			<input type="radio" name="gen" value="남" checked>남
            <input type="radio" name="gen" value="여" checked>여
		</td>
		</tr>
		<tr>	
			<td>이메일</td>
			<td>
				<input type="text" name="email1" maxlength="50" onfocusout='ev6()'/>@
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
			<td>	<select name="position" >
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
		<tr>
			<td>우편번호</td>
			<td><input type="text" id="sample4_postcode" name="zipcode" placeholder="우편번호" readonly="readonly">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></td>
		</tr>
		<tr>
		    <td>주소</td>
			<td><input type="text" id="sample4_roadAddress" name="addr" placeholder="도로명주소" readonly="readonly">
			<input type="text" id="sample4_detailAddress" name="datail" placeholder="상세주소"> </td>
		</tr>
		<tr>	
	
		
			<td colspan="2" align="right">			
				<div><div onclick="allChk()">가입</div><input type="reset" value="취소"/></div>			
			</td>
		</tr>
	</table>

<script>
var idChk;


function addChk(){
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
  	$('select[name=email2]').after("<div style='color: #d6bb50'>"+message+"</div>");
};


/* $(document).ready(function(){

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
 */

 function allChk() {	//메뉴 이동용
	
	
	if(idChk&&ev()&&ev2()&&ev3()&&ev4()&&ev5()&&ev6()){
		alert("드뎌");
		document.paging.hid_t.value = "service/joinReg";
		document.paging.submit();
	}
}; 


function ev(){if(!check2(/^[\d]{4}$/,$('#pw'),$('#pw1'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev2(){if(!check(/^[가-힣]{1,6}$/,$('#name'),'6자리 한글만 입력가능합니다.')){return false;}else{return true;}};
function ev3(){if(!check(/^[\d]{4}$/,$('input[name=simple_pw]'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};	
function ev4(){if(!check(/^[\d]{4}$/,$('input[name=acc_pw]'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};	
function ev5(){if(!check(/^(010)-([0-9]{3,4})-([0-9]{4})$/,$('input[name=tel]'),'10자리 숫자만 입력가능합니다.')){return false;}else{return true;}};	
function ev6(){if(!check3(/^[A-Za-z0-9_-]{4,7}$/,$('input[name=email1]'),'형식에 맞게 입력해주세요.')){return false;}else{return true;}};
		
function idChk() {
	
/* 	var zzz = /^[a-z0-9A-Z가-힣]{3,6}$/;
	alert("ID를 확인 하겠습니다.");
	if(!zzz.test($('input[name=id]').val()))
	{
		alert("id 오류");
		return;
	}  */
	
	
	gogo = "layout/service/idChk.jsp";
	$.ajax({	
	url:gogo,
	type:'post',
	data:{	 id 	: $('input[name=id]').val()},
	dataType:'json',
	success:function(qqq){
		if(qqq.id != "" || $('#id').val()==""){
			alert(qqq.id + "이미 존재하는 아이디 입니다");
			$('input[name=id]').val("");
			$('input[name=id]').focus();
			idChk=false;
			return false;
		}else if(qqq.id == "" || $('#id').val()!=""){		
			alert("통과 입니다."); 
			idChk=true;
			return true; 
		}
	},error:function(qqq){
		alert("에러발생");
	}	
	});	
};	
	
</script>