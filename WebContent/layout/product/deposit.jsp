<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/domAtion.js"></script>
<script type="text/javascript">
var main = '';
main +="<div class='serBox' >";
main +="<input class='data_v' type = 'radio' name = 'group'  value='예금' checked='checked'/>예금</input>";
main +="<input class='data_v' type = 'radio' name = 'group' value='적금'>적금</input>";
main +="<select class='sel_ds' size='1'><option value=''>선택해주세요</option></select>"
main +="<input class='sertext' type = 'text'/>";
main +="<div class='ser' >검색</div>";
main +="</div>";
main +="<div class='scrollB'>"; //스크롤바
main +="<div id = 'ttt'></div>";
main +="</div>";
$("#mid").append(main);

var gogo =  "layout/product/deposits/list.jsp";		
var gogo2 = "layout/product/deposits/detail.jsp";
var radioVal = "예금";
var selVal = "";
var path = "product/deposits/";
$('.sel_ds').on("click", function(){
selVal = this.value;
});
	
$(".data_v").on("click", function(){
	radioVal = this.value;

	$(".sel_ds").empty();
	main ="<option value=''>선택해주세요</option>";
	
	if (radioVal == "예금") {
		gogo =  "layout/product/deposits/list.jsp";		
		gogo2 = "layout/product/deposits/detail.jsp";
		path = "product/deposits/";
		$.each(${type_dp},function(i,e){ main += "<option value='"+e.name+"'>"+e.name+"</option>"; });
	} else {
		gogo =  "layout/product/saving/list.jsp";		
		gogo2 = "layout/product/saving/detail.jsp";
		path = "product/saving/";
		$.each(${type_sv},function(i,e){ main += "<option value='"+e.name+"'>"+e.name+"</option>"; });
	}
	
	$(".sel_ds").append(main);

	$('.sertext').val('');

	$.ajax({	//라디오 버튼 
		url:gogo,
		type:'post',
		dataType:'json',
			success:function(qqq){
			$("#ttt").empty();
			var row = '';
			
			$.each(qqq,function(i,e){
				row +="<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>";
				row +="<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>";
				row +="<div class='m'>["+e.type+"] "+e.product+"</div>";
				row +="<div class='rl' onclick='goMenu(this)' data-menu-name='"+path+"Detail'><div>상세보기</div></div>";
				row +="<div class='rr' onclick='goMenu(this)' data-menu-name='"+path+"Join'><div>가입하기</div></div>";
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
	data:{	type	: selVal,
			product : $('.sertext').val()},
	dataType:'json',
		success:function(qqq){
			$("#ttt").empty();
			var row = '';
			
			$.each(qqq,function(i,e){
				row +="<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>";
				row +="<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>";
				row +="<div class='m'>["+e.type+"] "+e.product+"</div>";
				row +="<div class='rl' onclick='goMenu(this)' data-menu-name='"+path+"Detail'><div>상세보기</div></div>";
				row +="<div class='rr' onclick='goMenu(this)' data-menu-name='"+path+"Join'><div>가입하기</div></div>";
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

</script>
<div class='subTitle'>예금·적금 신규</div>