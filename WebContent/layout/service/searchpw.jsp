<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br><br><br><br><br>
<script>

$(document).ready(function(){
	$("#change").click(function(){
		//alert("실행중");
		aaa();
		
		
	});
});

function aaa(){
	
// 	alert($("#account_number").val() +","+
// 			$("#account_pw").val()+","+
// 			$("#id").val()+","+
// 			$("#name").val()+","+
// 			$("#tel").val()+","+
// 			$("#email").val()+","+
// 			$("#pw").val()
// 	);

	
	$.ajax({
			url:"layout/service/spw.jsp",
			type:'post',
			data:{
				'account_number' : $("#account_number").val(),
				'account_pw' : $("#account_pw").val(),
				'id' : $("#id").val(),
				'name' : $("#name").val(),
				'tel' : $("#tel").val(),
				'email' : $("#email").val(),
				'pw' : $("#pw").val()
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
		<td> : <input type="text" id="account_number" /></td>
	</tr>

	<tr>
		<td>계좌 비밀번호</td>
		<td> : <input type="text" id="account_pw" /></td>
	</tr>
	
	<tr>
		<td>아이디</td>
		<td> : <input type="text" id="id" /></td>
	</tr>

	<tr>
		<td>이름</td>
		<td> : <input type="text" id="name" /></td>
	</tr>

	<tr>
		<td>이메일</td>
		<td> : <input type="text" id="email" /></td>
	</tr>

	<tr>
		<td>전화번호</td>
		<td> : <input type="text" id="tel" /></td>
	</tr>
	
	<tr>
		<td>변경할 비밀번호</td>
		<td> : <input type="text" id="pw" /></td>
	</tr>
		
	</table>
</div>

<div id="change">
	변경
</div>

<div id="tot"></div>