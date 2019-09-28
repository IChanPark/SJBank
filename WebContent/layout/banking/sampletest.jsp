<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>script</title>
<script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>   
<script>
alert("?!!!!!");

$(document).ready(function(){
	var cnt = 0;

	///추가
	$('#add').click(function(){
		cnt++;
		var addBox = $("<div class='box' id='box"+cnt+"'></div>");
		addBox.html($("#ttt").val());
		
		
		var btn = $("<button onclick='delGo("+cnt+")'>삭제</button>");
		addBox.append(btn);
		
		$("#tot").append(addBox);
	});
	
});
$("#abcd").click(function{
	
	alert("!!!!!!!!");
	
	
});

</script>
</head>
<body>
<table>
	<tr>
		<td><button id="add">aaa</button></td>
	</tr>
</table>
<div id="tot"></div>
</body>
</html>