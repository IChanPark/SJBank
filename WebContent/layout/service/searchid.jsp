<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<table>

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

</table>

<script>
$(document).ready(function(){
	$("#search").click(function(){
		alert("????");
		aaa();
	});
});

function aaa(){
	$.ajax({	//라디오 버튼 
		url:"layout/service/sid.jsp",
		type:'post',
		data:{
			name : $("input[name='name']").val(),
			tel : $("input[name='tel']").val(),
			email : $("input[name='email']").val()
		},
		dataType:'json',
		success:function(qqq){
				alert("성공");
				var row = $("<tr><td>"+qqq.id+"</td></tr>");
			
				$("#tot").append(row);
			
		},
		error:function(qqq){
			console.log("오류오류");
		}
	});
};
</script>
<div id="search">찾기	</div>
<div id="tot"></div>