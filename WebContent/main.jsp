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
	$('#deposit').click(function(){
		document.paging.hid_t.value ="product/Deposit";
		document.paging.submit();
	});
	$('#fund').click(function(){
		document.paging.hid_t.value ="product/Fund";
		document.paging.submit();
	});
	$('#saving').click(function(){
		document.paging.hid_t.value ="product/Saving";
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

<div align ="right" style="width: 500px; height: 300px; float: right;">
<jsp:include page="skitter-master/examples/index.html"  flush="false" />
</div>

 <div  align ="left" style="font-size: 30px; color: #F6F6F6; height: auto;  min-height : 70px; width : 50%;  background-color:#4375DB; float: center;"  align = "center" >
주요서비스
<div> </div>
<div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="transfer" >즉시이체</div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="check">전체조회</div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="reserve">예약이체</div>
</div>

</div>

 <div  align ="left" style="font-size: 30px; color: #F6F6F6; height: auto;  min-height : 70px; width : 50%;  background-color:#4375DB; float: center;"  align = "center" >
금융상품
<div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="deposit" >예금</div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="saving">적금</div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="fund">펀드</div>
</div> 
</div>


 <div  align ="left" style="font-size: 30px; color: #F6F6F6; height: auto;  min-height : 70px; width : 50%;  background-color:#4375DB; float: center;"  align = "center" >
고객서비스

<div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="qna" >Q&A</div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="faq">FAQ</div>
<div style=" float: left; width: 33.3%; color: #F6F6F6;" id="notice">공지사항</div>
</div> 
</div>
<!--  <div style="width: 100%; height: 100%;">
<div class="main-banner" style="top: 200px; float: right; width: 100%; height: 100%" >
<div  align ="top" style="  height: auto; min-height : 100px; 
width :50%; background-color : #4375DB; color: #F6F6F6; float: left;"  align = "left">금융상품</div> -->

 <!-- <div style="float: right; background-color: #4375DB; color: #F6F6F6;  margin-left:10px; padding-right:10px; width: 15%;" id="qna">qna</div>
<div style="float: right; background-color: #4375DB; color: #F6F6F6; margin-left:10px; padding-right:10px; width: 15%;" id="faq">faq</div>
<div style="float: right; background-color: #4375DB; color: #F6F6F6; margin-left: 10%; padding-right:10px; width: 15%;" id="notice" >공지사항</div>  -->

<!-- </div> -->
</div>