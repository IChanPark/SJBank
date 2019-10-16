<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br><br><br><br><br>
<script>
var stop=false;

$(document).ready(function(){

	$("#search").click(function(){

		var searchAccnum = /[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}$/;
		var searchAccpw = /^[0-9]{4}$/;
		
		if(!searchAccnum.test($("#account_number").val()) ){
			alert("계좌번호를 다시 확인해주세요.");
			return;
		}if(!searchAccpw.test($("#account_pw").val()) ){
			alert("비밀번호가 틀렸습니다.\n다시 확인해주세요");
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
// 					alert("성공");
// 					 qqq.id+"<br><br><a href='#' data-menu-name=' '>돌아가기</a>";
										//onKeyup='this.value=this.value.replace(/[^ㄱ-힣]/g,'');'  
															//onKeyup='this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');' 
															//onKeyup='this.value=this.value.replace(/([^0-9-])/g,'');'
					if(qqq.id != null){
						$("#set1").remove();
				var row = "<tr><td>아이디</td><td> : <input type='text' id='uid' value="+qqq.id+" readOnly /></td></tr>"+
						"<tr><td>이름</td><td> : <input type='text' name='name' id='name' placeholder='한글만'  /></td></tr>"+
						"<tr><td>이메일</td><td> : <input type='text' name='email1' id='email1' placeholder='영문, 숫자만'/>@"+
							"<select name='email2' id='email2'>"+
								"<option>naver.com</option>"+
								"<option>daum.net</option>"+
								"<option>gmail.com</option>"+
								"<option>nate.com</option>"+
								"<option>yahoo.com</option>"+
							"</select></td></tr>"+
						"<tr><td>전화번호</td>"+
						"<td> : <input type='text' name='tel' id='tel' placeholder='010-0000-0000' /></td></tr>"+
						"<tr><td>변경할 비밀번호</td><td> : <input type='text' id='pw' placeholder='비밀번호 4자리' /></td></tr>"+
						"<tr><td onclick='bbb()'>변경</td></tr>"+     
						"<br><a href='#' onclick='goMenu(this)' data-menu-name='service/LoginMain'>로그인</a>";
					
				$("#tot").append(row);
					}else{
						alert("찾기에 실패했습니다.");
					}

			},
			error:function(qqq){
// 				console.log("오류");
// 				alert("찾기에 실패했습니다.");
				var row ="<br><a href='#' onclick='goMenu(this)' data-menu-name='service/LoginMain'>돌아가기</a>";
				
				$("#tot").append(row);

			}
		});
	 
};    

function bbb(){
	
	var searchName = /[ㄱ-힣]{2,10}/g;
	var searchEmail = /[^0-9a-zA-Z]{0,50}/g;
	var searchTel = /([0-9]{2,3}[-][0-9]{3,4}[-][0-9]{3,4})/g;
	var searchPw = /^[0-9]{4}$/;
	
	if(!searchName.test($("#name").val()) ){
		alert("이름을 다시 확인해주세요.");
		return;
	}if(!searchEmail.test($("#email1").val()+"@"+$("#email2").val()) ){
		alert("이메일을 다시 확인해주세요.");
		return;
	}if(!searchTel.test($("#tel").val()) ){
		alert("전화번호를 다시 확인해주세요");
		return;
	}if(!searchPw.test($("#pw").val()) ){
		alert("비밀번호를 양식에 맞춰주세요.");
		return;
	}
	
	
	$.ajax({
			url:"layout/service/cpw.jsp",
			type:'post',
			data:{
				
				'uid' : $("#uid").val(),
				'name' : $("#name").val(),
				'tel' : $("#tel").val(),
				'email1' : $("#email1").val(),
				'email2' : $("#email2").val(),
				'pw' : $("#pw").val()
			},
			dataType:'json',
			success:function(qqq){
					alert("변경 성공");
				var row ="<br><a href='#' onclick='goMenu(this)' data-menu-name='service/LoginMain'>로그인</a>";
				
				$("#tot").append(row);

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
<div id="set1">
	<table>
	
		<tr>
			<td>계좌 번호</td>
			<td> : <input type="text" id="account_number" /></td>
		</tr>
	
		<tr>
			<td>계좌 비밀번호</td>
			<td> : <input type="password" id="account_pw" /></td>
		</tr>
			
	</table>
	<div id="search" style=" height: 20px; width: 33px;">찾기</div>
</div>

<div id="tot"></div>