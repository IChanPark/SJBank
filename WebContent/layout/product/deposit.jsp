<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
var main = '';
main +="<div class='subTitle'>예금·적금 신규</div>";
main +="<div class='serBox' >";
main +="<input class='data_v' type = 'radio' name = 'group'  value='예금' checked='checked'/>예금</input>";
main +="<input class='data_v' type = 'radio' name = 'group' value='적금'>적금</input>";
main +="<input class='sertext' type = 'text'/>";
main +="<div class='ser' >검색</div>";
main +="</div>";
main +="<div class='scrollB'>"; //스크롤바
main +="<div id = 'ttt'></div>";
main +="</div>";
$("#mid").append(main);

$(document).ready(function() {
var gogo =  "layout/product/deposit_list.jsp";		
var gogo2 = "layout/product/deposit_detail.jsp";
var radioVal = "예금";
	
$(".data_v").on("click", function() {
radioVal = this.value;
if (radioVal == "예금") {
	gogo =  "layout/product/deposit_list.jsp";
	gogo2 = "layout/product/deposit_detail.jsp";
} else {
	gogo =  "layout/product/saving_list.jsp";
	gogo2 = "layout/product/saving_detail.jsp";
}
	
$('.sertext').val('');

$.ajax({	//라디오 버튼 
	url:gogo,
	type:'post',
	data:{ddd : $(this).val()},
	dataType:'json',
		success:function(qqq){
		$("#ttt").empty();
		var row = '';
		$.each(qqq,function(i,e){
			row +="<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>";
			row +="<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>";
			row +="<div class='m'>["+e.type+"] "+e.product+"</div>";
			row +="<div class='rl'><div>가입</div></div>";
			row +="<div class='rr'><div>상세</div></div>";
			row +="</div>";
		});
		$("#ttt").append(row);
		},
		error:function(qqq){
			$("#ttt").empty();
		}
	});
});
$(".ser").on("click", function() {
	
$.ajax({	
	url:gogo2,
	type:'post',
	data:{eee : $('.sertext').val()},
	dataType:'json',
		success:function(qqq){
			$("#ttt").empty();
			var row = '';
			$.each(qqq,function(i,e){
				row +="<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>";
				row +="<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>";
				row +="<div class='m'>["+e.type+"] "+e.product+"</div>";
				row +="<div class='rl'><div>가입</div></div>";
				row +="<div class='rr'><div>상세</div></div>";
				row +="</div>";
			});
			$("#ttt").append(row);
		},
		error:function(qqq){
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			row.append($("<div class='m'>검색결과가 존재하지 않습니다.</div>"));
		}
	});	
});
});
</script>

<!-- <div class="subTitle">예금·적금 신규</div>
<div class="serBox" >
	<input class="data_v" type = "radio" name = "group"  value="예금" checked="checked"/>예금
	<input class="data_v" type = "radio" name = "group" value="적금"/>적금
	<input class="sertext" type = "text"/>
	<div class="ser" >검색</div>
</div>

<div class="scrollB"> 스크롤바
<div id = "ttt"> 
<div class="box"><div class="m">선택해주세요.</div></div>
</div>
</div> -->