<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(document).ready(function() {
var radioVal; //라디오 버튼 값저장용
	
$("input[name='group']:radio").change(function () {
	//라디오 버튼 값을 가져옴
	radioVal = this.value;
});
	
$(".data_v").on("click", function() {
	var gogo;

	if ($(this).val() == "예금") {
		gogo = "layout/product/deposit_list.jsp";
	} else {
		gogo = "layout/product/saving_list.jsp";
	}
	
$.ajax({	//라디오 버튼 
	url:gogo,
	type:'post',
	data:{ddd : $(this).val()},
	dataType:'json',
		success:function(qqq){
		$("#ttt").empty();
		$.each(qqq,function(i,e){
			var row = $("<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'></div>");
			row.append($("<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>"));
			row.append($("<div class='m'>["+e.type+"] "+e.product+"</div>"));
			row.append($("<div class='rl'><div>가입</div></div>"));
			row.append($("<div class='rr'><div>상세</div></div>"));
		
			$("#ttt").append(row);
		});
		},
		error:function(qqq){
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			row.append($("<div class='m'>오류가 발생하였습니다.</div>"));
		}
	});
});
$(".ser").on("click", function() {
	var gogo;
		
	if (radioVal == "예금") {
		gogo = "layout/product/deposit_detail.jsp";
	} else {
		gogo = "layout/product/saving_detail.jsp";
	}
	
$.ajax({	
	url:gogo,
	type:'post',
	data:{eee : $('.sertext').val()},
	dataType:'json',
		success:function(qqq){
			$("#ttt").empty();
			$.each(qqq,function(i,e){
				var row = $("<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'></div>");
				row.append($("<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>"));
				row.append($("<div class='m'>["+e.type+"] "+e.product+"</div>"));
				row.append($("<div class='rl'><div>가입</div></div>"));
				row.append($("<div class='rr'><div>상세</div></div>"));
		
				$("#ttt").append(row);
			});
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

<div class="subTitle">예금·적금 신규</div>
<div class="serBox" >
	<input class="data_v" type = "radio" name = "group"  value="예금" checked="checked"/>예금
	<input class="data_v" type = "radio" name = "group" value="적금"/>적금
	<input class="sertext" type = "text"/>
	<div class="ser" >검색</div>
</div>

<div class="scrollB"> <!-- 스크롤바 -->
<div id = "ttt"> 
<div class="box"><div class="m">선택해주세요.</div></div>
</div>
</div>