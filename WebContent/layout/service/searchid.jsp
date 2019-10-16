<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
var stop=false;

$(document).ready(function(){
	$("#search").click(function(){
// 		alert("실행중");
	var searchName = /[ㄱ-힣]{2,10}/g;
	var searchEmail = /[^0-9a-zA-Z]{0,50}/g;
	var searchTel = /([0-9]{2,3}[-][0-9]{3,4}[-][0-9]{3,4})/g;
	
	
	if(!searchName.test($("#name").val()) ){
		alert("이름 오류");
		return;
	}if(!searchEmail.test($("#email1").val()+"@"+$("#email2").val()) ){
		alert("이메일 오류");
		return;
	}if(!searchTel.test($("#tel").val()) ){
		alert("전화번호 오류");
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
// 				alert("성공");
				var row = "찾는아이디 : "+qqq.id+
				"<br><br><a href='#' onclick='goMenu(this)' data-menu-name='service/LoginMain'>로그인</a>";
			
				$("#tot").append(row);
				
				//go = "service/joinReg";
			
		},
		error:function(qqq){
// 			console.log("오류");
		}
	});
	 stop=true;
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
	<td> : <input type="text" name="name" id="name" onKeyup="this.value=this.value.replace(/[^ㄱ-힣]/g,'');" /></td>
</tr>

<tr>
	<td>이메일</td>
	<td> : <input type="text" name="email1" id="email1" onKeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');" />@
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
	<td> : <input type="text" name="tel" id="tel" onKeyup="this.value=this.value.replace(/([^0-9-])/g,'');"  /></td> 
</tr>

</table>

<div id="search">찾기	</div>
<br>
<div id="tot"></div>
