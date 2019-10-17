<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class='subTitle'>비밀번호 재설정</div>
<br>
<div id="set1">
<div class='infoMain_Info'><div class='infoMain_Type'>계좌 번호</div><div class='infoMain_Value'>
<input type="text" id="account_number" placeholder="###-####-####-####" maxlength="18" onKeyup="this.value=this.value.replace(/([^0-9-])/g,'');"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>계좌 비밀번호</div><div class='infoMain_Value'>
<input type="password" id="account_pw" placeholder="숫자4자리를 입력해주세요" maxlength="4"/></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button id='search'>찾기</button><button data-menu-name="service/LoginMain" >돌아가기</button></div></div>
</div>

<script>
var stop=false;
$(document).ready(function(){
	$('#search').on("click", function(){

		var searchAccnum = /[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}$/;
		var searchAccpw = /^[0-9]{4}$/;
		
		if(!searchAccnum.test($("#account_number").val()) ){
			alert("계좌번호를 다시 확인해주세요.");
			return;
		}
		if(!searchAccpw.test($("#account_pw").val()) ){
			alert("비밀번호를 다시 확인해주세요");
			return;
		}
		aaa();
	});
});

function aaa(){

		
	$.ajax({
		url:"layout/service/spw.jsp",
		type:'post',
		data:{
			'account_number' : $("#account_number").val(),
			'account_pw' : $("#account_pw").val()
		}, 
		dataType:'json',
		success:function(qqq){
			if(qqq.id != null){
				$("#set1").empty();
				var row ='';	
				row += "<div class='infoMain_Info'><div class='infoMain_Type'>아이디</div><div class='infoMain_Value'>";
				row += "<input name='title' type='text' id='uid' value="+qqq.id+" readOnly></div></div>";
				row += "<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호설정</div><div class='infoMain_Value'>";
				row += "<input type='password' id='pw' maxlength='4'onKeyup='this.value=this.value.replace(/([^0-9-])/g,''/></div></div>";
				row += "<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>";
				row += "<button onclick='bbb()'>변경</button><button data-menu-name='service/LoginMain'>취소</button></div></div>";
		 
				$("#set1").append(row);
			}else{
				alert("찾기에 실패했습니다.");
			}
		},
		error:function(qqq){				
			$("#tot").append(row);
		}
	});
	 
};    

function bbb(){
	var searchPw = /[0-9]{4}/g;

	if(!searchPw.test($("#pw").val()) ){
		alert("변경할 비밀번호를 다시 확인해주세요.");
		return;
	}
	
	$.ajax({
			url:"layout/service/cpw.jsp",
			type:'post',
			data:{
				
				'uid' : $("#uid").val(),
				'pw' : $("#pw").val()
			},
			dataType:'json',
			success:function(qqq){
				alert("변경 성공");
				$('#loga').remove();
				var row ="";
				row += "<div id='loga'><div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>";
				row += "<button onclick='goMenu(this)' data-menu-name='service/LoginMain'>로그인 페이지로</button></div></div></div>";

				$("#set1").append(row);
			},
			error:function(qqq){
				alert("변경 실패");
			}
		});

};

function goMenu(qq) {	//메뉴 이동용
	var menu = $(qq).data("menu-name"); 
	
	var go ='<form name="pag" action="SJBank" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+menu+"'></input>";
	go +="</form>";
	$("#mid").append(go);
	document.pag.submit(); 
};

</script>