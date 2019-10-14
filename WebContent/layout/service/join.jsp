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
			<td><input type="password" name="pw1" id="pw1"/><input type="button" value="비밀번호확인" onclick="pwChk()" id=pwChk></td>		
		</tr>		
		<tr>	
			<td>간편 비밀 번호</td>
			<td><input type="password" name="simple_pw" /><input type="button" value="중복확인" onclick="simpw()"></td>
		</tr>
		<tr>	
			<td>계좌비밀번호</td>
			<td><input type="password" name="acc_pw" /></td>			
		</tr>	
		<tr>
		<td>이름</td>							
			<td><input type="text" name="name" maxlength="50"/>		
		</tr>
		<tr>	
			<td>전화번호</td>
			<td><input type="text" name="tel" /></td>
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
				<input type="text" name="email1" maxlength="50"/>@
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
				<div><div onclick="allChk()">가입</div><button>취소</button></div>			
			</td>
		</tr>
	</table>

<script>
var idchk = false,
	addchk = false;
function addChk(){
	if($('#sample4_postcode').val() == ""){
		alert("주소미입력");
		return addchk = false;
	}else 
		return addchk = true;
}	

function checkId(){
	
	
	var aaa = $('input[name=id]').val();
	
	
	
	
	if(aaa == "")
    {
        alert("ID를 입력 하시오.");
        aaa.value="";
        aaa.focus();
        return seccion;
    }	

}



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


async function allChk() {	//메뉴 이동용
 
	 idchk = true;
	 addchk=true;
	 await idChk();
	
	 await addChk();
	
	if(idchk!=false && addchk!=false){
		document.paging.hid_t.value = "service/joinReg";
		document.paging.submit();
	}
}; 


	
	
function idChk() {
	
	var zzz = /^[a-z0-9A-Z가-힣]{3,8}$/;
	alert("적용되나요?");
	if(!zzz.test($('input[name=id]').val()))
	{
		alert("id 오류");
		return;
	} 
	
	
	
	
	
	gogo = "layout/service/idChk.jsp";
	$.ajax({	
	url:gogo,
	type:'post',
	data:{	 id 	: $('input[name=id]').val()},
	dataType:'json',
	success:function(qqq){
		
		
		if(qqq.id != "" || $('input[name=id]').val()==""){
			alert("사용할 수 없는 아이디 입니다.");
			alert(qqq.id);
			$('input[name=id]').val("");
			$('input[name=id]').focus();
			return idchk = false;
		}else if(qqq.id = "" || $('input[name=id]').val()!=""){
			alert("사용할 수 있는 아이디 입니다.");
			$('input[name=id]').val("");
			$('input[name=id]').focus();
			 return idchk = true; 
		}else return;
	},error:function(qqq){
		alert("에러발생");
	}	
		
	});	
};	
	
</script>