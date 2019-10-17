<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class='subTitle'>회원가입</div>
<br>  
<div class='infoMain_Info'><div class='infoMain_Type'>아이디</div><div class='infoMain_Value'>
<input type="text" name="id" maxlength="50" placeholder="영문+숫자 조합6자리"/><button onclick="idChk()">중복확인</button></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호</div><div class='infoMain_Value'>
<input type="password" name="pw" id="pw" placeholder="숫자4자리입력하세요"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호 확인</div><div class='infoMain_Value'>
<input type="password" name="pw1" id="pw1" placeholder="숫자4자리입력하세요" onfocusout='ev()'/></div></div>		
<div class='infoMain_Info'><div class='infoMain_Type'>생성될 계좌비밀번호</div><div class='infoMain_Value'>		
<input type="password" name="acc_pw" placeholder="숫자4자리입력하세요" onfocusout='ev4()' /></div></div>		
<div class='infoMain_Info'><div class='infoMain_Type'>이름</div><div class='infoMain_Value'>
<input type="text" name="name" maxlength="50" placeholder="한글6까지만입력" onfocusout='ev2()'  id="name" /></div></div>		
<div class='infoMain_Info'><div class='infoMain_Type'>전화번호</div><div class='infoMain_Value'>
<input type="text" name="tel" placeholder="ex)010-xxxx-xxxx" onfocusout='ev5()' /></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>성별</div><div class='infoMain_Value'>
<input type="radio" name="gen" value="남" checked>남<input type="radio" name="gen" value="여" checked>여</div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>직업</div><div class='infoMain_Value'>
<select name="position" ><option>학생</option><option>군인</option><option>회사원</option>
<option>주부</option><option>교수</option><option>백수</option></select></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>우편번호</div><div class='infoMain_Value'>
<input type="text" id="sample4_postcode" name="zipcode" placeholder="우편번호" readonly="readonly">
<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>주소</div><div class='infoMain_Value'>
<input type="text" id="sample4_roadAddress" name="addr" placeholder="도로명주소" readonly="readonly">
<input type="text" id="sample4_detailAddress" name="datail" placeholder="상세주소"></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button onclick="allChk()">가입</button><button data-menu-name="service/LoginMain">취소</button></div></div>			
<script>
var idChktf=false;
var idChkdone=false;

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
 

 function allChk() {	//메뉴 이동용
	 
	
	if(idChktf && addChk() && ev() && ev2() && ev4() && ev5()){
		alert("가입이 완료되었습니다.");
		document.paging.hid_t.value = "service/joinReg";
		document.paging.submit();
	}else if(idChkdone==false){
		alert("ID 중복 체크해주세요");
	}
}; 


function ev(){if(!check2(/^[\d]{4}$/,$('#pw'),$('#pw1'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev2(){if(!check(/^[가-힣]{1,6}$/,$('#name'),'양식에맞게입력해주세요.')){return false;}else{return true;}};	
function ev4(){if(!check(/^[\d]{4}$/,$('input[name=acc_pw]'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};	
function ev5(){if(!check(/^(010)-([0-9]{3,4})-([0-9]{4})$/,$('input[name=tel]'),'형식에 맞게 입력해주세요.')){return false;}else{return true;}};	
		
function idChk() {
	
 	var zzz = /^[a-z0-9A-Z가-힣]{3,6}$/;
	if(!zzz.test($('input[name=id]').val()))
	{
		alert("id 오류");
		return;
	}  
	
	if($('input[name=id]').val()!=null)
			
	
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
			idChktf=false;
			idChkdone=true;
			//return false;
		}else if(qqq.id == "" || $('#id').val()!=""){		
			alert("통과 입니다."); 
			idChktf=true;
			idChkdone=true;
			//return true; 
		}
	},error:function(qqq){
		alert("에러발생");
	}	
	});	

};	
	
</script>