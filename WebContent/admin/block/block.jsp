<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래제한</title>
<script>
    
	
$(document).ready(function(){
	var tot;
	tot = $("#tot");			
	ajaxGo("blockuserlist",{});

	function ajaxGo(goUrl, getData){
		//alert("54321");
		$.ajax({
			url:"admin/block/"+goUrl+".jsp",
			type:'get',
			data: getData,
			dataType:'json',  ////json을 안하면 문자열로 , json 처리하면 object로 묶어서 받음
			success:function(qqq){
				if(goUrl=="blockuserlist")
					listGo(qqq);
				
				 if(goUrl=="userblock")
					userblockGo(qqq);
				 
			},
			error:function(qqq){
				alert(" 에러인가");
				tot.html(qqq.responseText);
			}
			
		});
	}
	
/* function userblockGo(qqq){
	alert(" 블록들어옴");
	tot.html("");
	tot.append($("<tr><td>id</td><td>이름</td><td>상태 변경사유</td><td>변경할 상태</td></tr>"));
	var row = $("<tr></tr>");
	$.each(qqq,function(i,e){
		var row = $("<tr></tr>");
		row.append($("<td>"+i+"</td>"));
		row.append($("<td>"+e.id+"</td>"));
		row.append($("<td>"+e.name+"</td>"));
		
		tot.append(row);
	});
	

	var col = $("<td colspan=2 align='right' ></td>");
	col.append($("<button onclick='ajaxGo(\"userlist\",{})' >리스트로</button>"));
	row.append(col);
	
	
	
	tot.append(row);
} */

function listGo(qqq){
	 tot.html(""); 
	
	tot.append($("<tr><td>번호</td><td>id</td>"+
			"<td>이름</td><td>전화번호</td><td>이메일</td><td>계정상태</td><td>상태변경</td></tr>"));
	
	$.each(qqq,function(i,e){
		var row = $("<tr></tr>");
		row.append($("<td>"+i+"</td>"));
		row.append($("<td id='det'>"+e.id+"</td>"));
		row.append($("<td>"+e.name+"</td>"));
		row.append($("<td>"+e.tel+"</td>"));
		row.append($("<td>"+e.email+"</td>"));
		row.append($("<td>"+e.status+"</td>"));
	 	//<a href="#" class="ser" >검색하기</a>onclick='ajaxGo(\"userblock\",{id:"+e.id+"}) 
		//var btn = $("<td  onclick='ajaxGo(\"userblock\",{id:"+e.id+"})' >상태변경</td>");
		//row.append($("<td onclick='ajaxGo(\"userblock\",{id:"+e.id+"})'>상태변경</td>"));
		row.append($("<td onclick='goMenu(this)' data-menu-name='admin/Block/Userblock'>상태변경</td>"));
		/* <a href="#" data-menu-name="admnin/Server">서버 관리</a>
		<a href="#" data-menu-name="admin/Userblock">상태변경</a> */
		tot.append(row);
		//$("#tttt").append($("<td><a href=\"#\" data-menu-name=\"admin/Userblock\">상태변경</a></td>"));
	});
}

/* $(".change").on("click", function() {
	alert("?????");
	
	$.ajax({
		url:"admin/userblock.jsp",
		type:'get',
		data:{ddd : $('#det').val()},
		dataType:'json',
		success:function(qqq){
			alert(" 블록들어옴");
			$("#tot").empty();
			tot.append($("<tr><td>id</td><td>이름</td><td>상태 변경사유</td><td>변경할 상태</td></tr>"));
			$.each(qqq,function(i,e){
				var row = $("<tr></tr>");
					row.append($("<td>"+e.id+"</td>"));
					row.append($("<td>"+e.name+"</td>"));
				$("#tot").append(row);
			});
		},
		error:function(qqq){
			console.log("오류오류");
			console.log(qqq);
		}
	});
}); */

});
function goMenu(qq) {	//메뉴 이동용
	var f=document.paging; 
    f.hid_t.value = $(qq).data("menu-name"); 
    f.method="post";
    f.submit();
};
</script>
</head>
<body>
<table border="" id="tot">
</table>
</body>
</html>