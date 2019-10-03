<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready(function(){
	
	$("button[name=goNext]").click(function(){
		alert("이벤트발생?!!!");	
		$("#tot").html("");
		
		var title =$("<div class='subTitle'> 정보입력</div>");	
		$("#tot").append(title);
		
		var tt=("<table><tr><td>지연시간</td><td>"+"	<input type='radio' name='dalayTime' value='3'/>3:00"
			+"<input type='radio' name='dalayTime' value='4'/>4:00"
			+"<input type='radio' name='dalayTime' value='5'/>5:00"
			+"</td></tr><tr><td>즉시이체 가능금액</td>"
			+" <td><input type='text' />원 이하</td></tr></table>"
		);
		
		 $("#tot").append(tt);
		 
		 var title = $("<br><br><table border=''><tr><td>선택</td><td>No</td><td>은행명</td><td>계좌번호</td><td>입금계좌별명</td></tr>")
		 var title2=("<c:forEach var='t' step='1' begin='1' end='5'>"+
		 	"<tr><td><input type='checkbox' /></td>"+
		 	"<td>${t }</td>"+
			"<td><input type='text' name ='bankName'/></td>"+
			"<td><input type='text' name ='bankName'/></td>"+
			"<td><input type='text' name = 'accountAlias'/></td></tr></c:forEach></table>");
		title.append(title2);
		 title.append("<div onclick='regGo()'>신청</div>");
		 $("#tot").append(title);
		 
	});
	
});
function regGo(){
	
	alert("??????");
	$("#tot").html("");
	var lastApply=$("<div>보안정보 입력</div>");
	
	lastApply.append("<div><input type='text' /></div>");
	lastApply.append("<button onclick=complete()>입력</button>")
	$("#tot").append(lastApply);
	
};

function complete(){
	
	alert("왔다다아아아아아");
	
	var f=document.paging; 
	    
	f.hid_t.value = "security/delaytrs/Complete";
	f.accountNumber.value = t;
	f.method="post";
	f.submit();
	
};

</script>
<div id ="tot">
<table>
	<tr>
		<td><input type="checkbox" />약관같은 무언가
		</td>
	</tr>
</table>

<br><br>
 
<button name="goNext">다음</button>
</div>



