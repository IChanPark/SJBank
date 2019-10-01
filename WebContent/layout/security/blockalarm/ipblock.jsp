<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
$(document).ready(function(){

	
	$('.apply').click(function(){
		
		var addBox = $("<br><br><br><div> 보안매체 정보입력<br><br>"+"OTP 입력창 <input type='text' /></div>");
		
		var btn = $("<div><input type='button' value='완료' /></div>");
		addBox.append(btn);
		
		$("#mid").append(addBox);
		$('.apply').hide();		
		
	});
});



</script>


<div class="subTitle">IP차단 신청</div>

<div>ip차단 서비스란?</div>    
<div>내용입니다</div>    
    
    
<input type="button" class="apply" value="신청하기"/>