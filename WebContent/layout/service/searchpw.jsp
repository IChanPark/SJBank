<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>

$(document).ready(function(){
	$("#change").click(function(){
		alert("실행중");
		aaa();
	});
});

function aaa(){
	$.ajax({	//라디오 버튼 
		url:"layout/service/sid.jsp",
		type:'post',
		data:{
			account_number : $("input[name='account_number']").val(),
			pw : $("input[name='account_pw']").val(),
			
			id : $("input[name='id']").val,
			name : $("input[name='name']").val(),
			tel : $("input[name='tel']").val(),
			email : $("input[name='email']").val()
			
		},
		dataType:'json',
		success:function(qqq){
				alert("성공");
				var row = $("<tr><td>"+qqq.pw+"</td></tr>");

				$("#tot").append(row);

		},
		error:function(qqq){
			console.log("오류");
		}
	});
};
</script>

<div class="child">
	<table>

	<tr>
		<td>계좌 번호</td>
		<td> : <input type="text" name="account_number" /></td>
	</tr>

	<tr>
		<td>계좌 비밀번호</td>
		<td> : <input type="text" name="account_pw" /></td>
	</tr>
	
	<tr>
		<td>아이디</td>
		<td> : <input type="text" name="id" /></td>
	</tr>

	<tr>
		<td>이름</td>
		<td> : <input type="text" name="name" /></td>
	</tr>

	<tr>
		<td>이메일</td>
		<td> : <input type="text" name="email" /></td>
	</tr>

	<tr>
		<td>전화번호</td>
		<td> : <input type="text" name="tel" /></td>
	</tr>
	
	<tr>
		<td>변경할 비밀번호</td>
		<td> : <input type="text" name="pw" /></td>
	</tr>
		
	</table>
</div>

<div id="change">
	변경
</div>

<div id="tot"></div>