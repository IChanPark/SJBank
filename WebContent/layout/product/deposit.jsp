<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
/* var main = '';
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
$("#mid").append(main); */

var radioVal = "예금";
var selVal = "";
var path = "product/deposit/";
var gogo =  "layout/"+path+"all.jsp";
var isRun = false; 		//아작스 중복실행 확인 
$('.sel_ds').on("click", function(){
selVal = this.value;
});
	
$(".data_v").on("click", function(){
	radioVal = this.value;

	$(".sel_ds").empty();
	main ="<option value=''>선택해주세요</option>";
	
	if (radioVal == "예금") {
		path = "product/deposit/";	
		$.each(${type_dp},function(i,e){
			main += "<option value='"+e.name+"'>"+e.name+"</option>"; });
	} else {
		path = "product/saving/";
		$.each(${type_sv},function(i,e){
			main += "<option value='"+e.name+"'>"+e.name+"</option>"; });
	}
	gogo =  "layout/"+path+"all.jsp";
	
	$(".sel_ds").append(main);

	$('.sertext').val('');
	ajax_go();
});
	
$(".ser").on("click", function() {
	gogo = "layout/"+path+"select.jsp";
	ajax_go();
});

function ajax_go() {
	if(isRun == true)
		return;
	isRun = true;
	
	$.ajax({	
	url:gogo,
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
				row +="<div class='rl' onclick='detail(this)'><div>상세보기</div></div>";
				row +="<div class='rr' onclick='goMenu(this)' data-menu-name='"+path+"Join'><div>가입하기</div></div>";
				row +="</div>";
			});
			$("#ttt").append(row);
			isRun = false;
		},
		error:function(qqq){
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			row.append($("<div class='m'>검색결과가 존재하지 않습니다.</div>"));
			isRun = false;
		}	
	});	
};

function detail(me) {
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "layout/"+path+"detail.jsp";
	$('.subTitle').text('상품 안내');
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	product : $(me).parent('[data-product-name]').data("product-name")},
		dataType:'json',
		success:function(qqq){
			$(".serBox").empty();
			$("#ttt").empty();
			var row = '';
			
			row += "<div class='box'>"+qqq.product+","+qqq.Min_interest+"max_interest"+qqq.Max_interest;
			row += "month"+qqq.Month+"type"+qqq.Type+"interest_type"+qqq.getInterest_type+"tax"+qqq.Tax;
			row += "preferential"+qqq.getPreferential+"prf_content"+qqq.getPrf_content+"prf_interest"+qqq.getPrf_interest;
			row += "partialization"+qqq.getPartialization+"retention"+qqq.getRetention+"min_sum"+qqq.getMin_sum;
			row += "max_sum"+qqq.getMax_sum+"register_date"+qqq.getRegister_dateStr;
			row += "</div>";
	
			$("#ttt").append(row);
			isRun = false;
		},
		error:function(qqq){
			$(".serBox").empty();
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			row.append($("<div class='m'>검색결과가 존재하지 않습니다.</div>"));
			isRun = false;
		}	
	});
};

function goMenu(qq) {	//메뉴 이동용
	var data1 = $(qq).data("menu-name"); 
    var data2 = $(qq).parent('[data-product-name]').data("product-name");
	
	var go ='<form name="pag" action="index.jsp" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+data1+"'><input>";
	go +="<input type='hidden' name='dt' value='"+data2+"'><input>";
	go +="</form>";
	$("#mid").append(go);
	
	document.pag.submit(); 
};
</script>
<div class='subTitle'>예금·적금 신규</div>
<style>
.topLeft {float: left; height: 100%; width: 65%; background:skyblue;
align-items: flex-start; }
.topLtt {text-align:left; height: 20%; font-size: 16pt; font-weight: bold; padding-top: 10px;}
.topLinfo {text-align:left; height: 65%; font-size: 11pt;}
.topRigth {float: right; height: 100%; width: 35%; background:blue;
align-items: center; font-size: 14px; text-align: center; justify-content: center;}
.topRigth img {height: 150px; width: 150px; }
.infoBox {margin-top: 30px; width: 100%; height: 500px;}
.infoTop {height: 200px; text-align: center; border: 1px solid white; padding-left: 20px; padding-top: 20px;}
infoMain {float: left; height: 200px; width: 60%; background:skyblue;
align-items: flex-start; display: flex;}
.subinfo {margin-top:30px; margin-bottom:20px; border-bottom:1px solid white; font-size: 15pt;}
.cenb {width: 100%; text-align: center;}
.joinb{width: font-size: 14pt; font-weight: bold; width: 100px; height: 50ox;}
border-radius
</style>
<div class= "infoBox">
	<div class="infoTop">
		<div class="topLeft">
			<div class="topLtt">뭐시기 뭐시기 뭐시기</div><br>
			<div class="topLinfo">
				① 뭐시기 우대해서 우대해야<br>
				② 뭐시기 뭐시기 하면 우대해야<br>
				<br>
				상품종류&emsp;|&emsp;대충 이러한 상품<br>
				가입기간&emsp;|&emsp;대충 이러한 기간<br>
				가입금액&emsp;|&emsp;대충 이러한 금액 부터 최대 이러한 금액까지
			</div>
		</div>
		<div class="topRigth">
		 	<img src="img/test1.png" alt="" /><br>
		 	최저 연 ~ 최고 연<br>
		 	1.4 ~ 2.8
		</div>
	</div>
	<div class="cenb"><div class="joinb">가입</div></div>
	<div class="infoMain">
	<div class='subinfo'>상품 안내</div>
		ㅁㄴㅁㄴㄴㅁㅁㄴ 
	</div>
</div>