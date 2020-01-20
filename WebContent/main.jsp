<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>



$(document).ready(function(){
	$('#check').click(function(){
			document.paging.hid_t.value ="banking/Check";
			document.paging.submit();
	});
	$('#transfer').click(function(){
		document.paging.hid_t.value ="banking/Transfer";
		document.paging.submit();
	});
	
	$('#reserve').click(function(){
		document.paging.hid_t.value ="banking/transfer/Reservation";
		document.paging.submit();
	});
	
	$('#faq').click(function(){
		document.paging.hid_t.value ="service/client/Faq";
		document.paging.submit();
	});
	$('#qna').click(function(){
		document.paging.hid_t.value ="service/client/Qna";
		document.paging.submit();
	});
	$('#notice').click(function(){
		document.paging.hid_t.value ="service/client/Notice";
		document.paging.submit();
	});
	
});
</script>


<style>
.main-banner{
 	position: relative;
	top : 100%;
}
#abc{ width: 1000px;}
</style>

<div><img src="img/sjbankmain.png" alt=""  id ="abc"/></div>

<!--  <div  align ="top" style="font-size: 30px; height: auto;  min-height : 100px; width : 50%;  background-color:#64FE2E; float: left;"  align = "left" >
주요서비스
<div>
<div style=" float: left; width: 33.3%; " id="transfer">즉시이체</div>
<div style=" float: left; width: 33.3%; " id="check">전체조회</div>
<div style=" float: left; width: 33.3%; " id="reserve">예약이체</div>
</div> -->

</div>
<div style=" width: 500px; height: 200px; float: left;">
<jsp:include page="skitter-master/examples/index.html"  flush="false" />
</div> 

<!-- <div style="width: 100%; height: 100%;">
<div class="main-banner" style="top: 200px; float: left; width: 100%; height: 100%" >
<div  align ="top" style="  height: auto; min-height : 100px; 
width :50%; background-color : #2EFEF7; float: left;"  align = "left"><h1>금융상품</h1></div> -->

<!-- <div style="float: right; background-color: green; margin-left:10px; padding-right:10px; width: 15%;" id="qna">qna</div>
<div style="float: right; background-color: yellow; margin-left:10px; padding-right:10px; width: 15%;" id="faq">faq</div>
<div style="float: right; background-color: red; margin-left: 10%; padding-right:10px; width: 15%;" id="notice" >공지사항</div> -->

</div>
</div>