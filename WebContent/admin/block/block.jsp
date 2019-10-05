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
				
			},
			error:function(qqq){
				alert(" 에러인가11");
			}
			
		});
	}
});
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
	$("#tot").empty();
	
	$("#tot").append($("<tr><td>번호</td><td>id</td>"+
			"<td>이름</td><td>전화번호</td><td>이메일</td><td>계정상태</td><td>상태변경</td></tr>"));
	
	$.each(qqq,function(i,e){
		var row = $("<tr data-product-name='"+e.id+"'></tr>");
		row.append($("<td>"+i+"</td>"));
		row.append($("<td id='det'>"+e.id+"</td>"));
		row.append($("<td>"+e.name+"</td>"));
		row.append($("<td>"+e.tel+"</td>"));
		row.append($("<td>"+e.email+"</td>"));
		row.append($("<td>"+e.status+"</td>"));
	 	//<a href="#" class="ser" >검색하기</a>onclick='ajaxGo(\"userblock\",{id:"+e.id+"}) 
		//var btn = $("<td  onclick='ajaxGo(\"userblock\",{id:"+e.id+"})' >상태변경</td>");
		//row.append($("<td onclick='ajaxGo(\"userblock\",{id:"+e.id+"})'>상태변경</td>"));
		row.append($("<td onclick='detail(this)' data-menu-name='admin/block/userblock'>상태변경</td>"));
		/* <a href="#" data-menu-name="admnin/Server">서버 관리</a>
		<a href="#" data-menu-name="admin/Userblock">상태변경</a> */
		$("#tot").append(row);
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


function detail(me) {
	gogo =  "admin/block/userblock.jsp";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	product : $(me).parent('[data-product-name]').data("product-name")},
		dataType:'json',
		success:function(sss){
		
			$("#tot").empty();
			var row = "";
			row += "<tr><td>아이디</td><td>이름</td><td>현재상태</td></tr>";
			row += "<tr ><td id='statusid'>"+sss.id+"</td>";
			row += "<td>"+sss.name+"</td>";
			row += "<td>"+sss.status+"</td>";
			row += "</tr>";
			
			row += "<tr><td colspan='3' align='center'>-</td></tr>";
			
			row += "<tr><td colspan='3' align='center'>상태변경선택</td></tr>";
			row +="<tr>";
			row +="<td colspan='3' align='center'><select id='selectstatus' >";
			row +=	"<option>활성</option>";
			row +=	"<option>비활성</option>";
			row +=	"</select></td>"; 
			row += "</tr>";
			
			
			row += "<tr><td colspan='3' align='center'>-</td></tr>";
			
			row += "<tr onclick='statuschange(this)' ><td colspan='3' align='center'>등록</td></tr>";
			
			$("#tot").append(row);
			
		
		},
		error:function(sss){
			alert(" 에러인가detail");
		
		}	
	});
};


function statuschange(me) {
	
	gogo =  "admin/block/statuschange.jsp";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	statusid : $("#statusid").text(),
				type : $("#selectstatus").val()},
		dataType:'json',
		success:function(sss){
			
			listGo(sss);
		
		},
		error:function(sss){
			alert(" 에러인가detail");
		
		}	
	});
};
</script>
</head>
<body>
<table border="" id="tot">
</table>
</body>
</html>