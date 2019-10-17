<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br><br><br><br><br>
<script>
var stop=false;

$(document).ready(function(){
	$("#search").click(function(){
// 		alert("실행중");
	var searchName = /[가-힣]{1,6}/g;
	var searchEmail = /[0-9a-zA-Z]{2,50}/g;
	var searchTel = /([0-9]{2,3}[-][0-9]{3,4}[-][0-9]{3,4})/g;
	
	
	if(!searchName.test($("#name").val()) ){
		alert("이름을 다시 확인해주세요.");
		return;
	}if(!searchEmail.test($("#email1").val()) ){
		alert("이메일을 다시 확인해주세요.");
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
			email1 : $("input[name='email1']").val(),
			email2 : $("select[name='email2']").val()
			
		},
		dataType:'json',
		success:function(qqq){
				var row = "찾는아이디 : <input type='text' id='uid' value="+qqq.id+" readOnly />"+
				"<br><br><a href='#' onclick='goMenu(this)' data-menu-name='service/LoginMain'>로그인</a>";
		
				if(qqq.id != null){
				
				$("#tot").append(row);
					 stop=true;
					 $("#search").remove();
				}else{
					alert("검색된 아이디가 없습니다.");
				}
			
		},
		error:function(qqq){
// 			console.log("오류");
// 			$("#tot").remove();
			alert("잘못된 정보입니다.\r다시 확인해주세요");
		
// 			stop=false;
		}
	});
// 	 stop=true;
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

<table>

<tr>
	<td>이름</td>
	<td> : <input type="text" name="name" id="name" placeholder="한글만 최대6자리" onKeyup="this.value=this.value.replace(/[^가-힣]/g,'');" /></td>
</tr>

<tr>
	<td>이메일</td>
	<td> : <input type="text" name="email1" id="email1" placeholder="영문,숫자만 입력" onKeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');" />@
		<select name="email2" id="email2">
			<option>naver.com</option>
			<option>daum.net</option>
			<option>gmail.com</option>
			<option>nate.com</option>
			<option>yahoo.com</option>
		</select> 
	</td>	
</tr>

<tr>
	<td>전화번호</td>
	<td> : <input type="text" name="tel" id="tel" placeholder="010-xxxx-xxxx" onKeyup="this.value=this.value.replace(/([^0-9-])/g,'');"  /></td> 
</tr>

</table>

<div id="search" style=" height: 20px; width: 33px;">찾기</div>
<div id="tot"></div>
<br>
<div id="main">메인화면</div>