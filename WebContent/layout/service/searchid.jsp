<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class='subTitle'>아이디 찾기</div>
<br>
<div id="set1">
<div class='infoMain_Info'><div class='infoMain_Type'>이름</div><div class='infoMain_Value'>
<input type="text" name="name" id="name" placeholder="한글만 최대6자리" onKeyup="this.value=this.value.replace(/[^가-힣]/g,'');" /></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>전화번호</div><div class='infoMain_Value'>
<input type="text" name="tel" id="tel" placeholder="010-xxxx-xxxx" onKeyup="this.value=this.value.replace(/([^0-9-])/g,'');"  /></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button id='search'>찾기</button><button data-menu-name="service/LoginMain" >돌아가기</button></div></div>
</div>
<script>
var stop=false;

$(document).ready(function(){
	$("#search").click(function(){
// 		alert("실행중");
	var searchName = /[가-힣]{1,6}/g;
	var searchTel = /([0-9]{2,3}[-][0-9]{3,4}[-][0-9]{3,4})/g;

	if(!searchName.test($("#name").val()) ){
		alert("이름을 다시 확인해주세요.");
		return;
	}if(!searchTel.test($("#tel").val()) ){
		alert("전화번호를 다시 확인해주세요");
		return;
	}
	
	aaa();
	
	});
});
 
function aaa(){
	var go = "layout/service/sid.jsp";
	if(!stop){
		
	$.ajax({	//라디오 버튼 
		url:go,
		type:'post',
		data:{
			name : $("input[name='name']").val(),
			tel : $("input[name='tel']").val(),
		},
		dataType:'json',
		success:function(qqq){
			$("#set1").empty();
			var row = "";
			row	+= "<div class='infoMain_Info'><div class='infoMain_Type'>찾은 아이디</div><div class='infoMain_Value'>";
			row += "<input type='text' id='uid' value="+qqq.id+" readOnly /></div></div>"
			row	+= "<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>";
			row += "<button onclick='goMenu(this)' data-menu-name='service/LoginMain'>로그인 화면으로</button></div></div>"
			
			if(qqq.id != null){
			
			$("#set1").append(row);
				 stop=true;
				 $("#search").remove();
			}else{
				alert("검색된 아이디가 없습니다.");
			}
		},
		error:function(qqq){
			alert("잘못된 정보입니다.\r다시 확인해주세요");
		}
	});
	}
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