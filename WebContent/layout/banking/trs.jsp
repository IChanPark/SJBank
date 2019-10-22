<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
$(document).ready(function(){
	
	
	
});

function goCheck(){
	
	var f=document.paging;     
	f.hid_t.value = "banking/Check";
    f.method="post";
    f.submit();
};


function goTransfer(){
	
	var f=document.paging;     
	f.hid_t.value = "banking/Transfer";
    f.method="post";
    f.submit();
};

</script>

<div class="subTitle">계좌번호 ${data.to_account_number }로 ${data.sum }원의 이체가 ${data.status }하였습니다.
<br> 이용해 주셔서 감사합니다.</div>


<div style="width: 100%; margin-top: 100px; ">
<div id="ToCheck" onclick = "goCheck()" style="float: left; font-size: 30px"> 조회하기 </div>
<div id= "ToTransfer"onclick = "goTransfer()" style="float: left; margin-left: 50%; font-size: 30px"> 계속이체하기 </div>
</div>