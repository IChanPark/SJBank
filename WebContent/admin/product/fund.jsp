<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var isRun = false; 		//아작스 중복실행 확인
var main = '';
var jbAry = new Array();
main +="<div class='search_Box' >";
main +="<input class='search_Word' type = 'text' value=''/>";
main +="<div class='search_Button' >검색</div>";
main +="<div class='add_Button' onclick='add()'>등록하기</div>";
main +="</div><div id ='mm'>";
main +="<div class='scrollB'>"; //스크롤바
main +="<div id = 'ttt'></div>";
main +="</div></div>";
$("#mid").append(main);

$(document).ready(function(){
	ajax_go();
	option();
});
	
$(".search_Button").on("click", function() {
	ajax_go();
});

function option(){
	$.ajax({	
		url:"product/fund/Type",
		type:'post',
		dataType:'json',
		success:function(qqq){
			main ="<input class='type_Radio' type = 'radio' name = 'group' value='' checked='checked' onclick='ajax_go()'>모두</input>";
			$.each( qqq ,function(i,e){
				main += "<input class='type_Radio' type = 'radio' name = 'group' value='"+e.name+"' onclick='ajax_go()'>"+e.name+"</input>";
			});
			$(".search_Box").prepend(main);
			isRun = false;
		},
		error:function(qqq){
			isRun = false;
		}	
	});	
}

function ajax_go() {

	$.ajax({	
	url:"product/fund/Admin_Select",
	type:'post',
	data:{	type	: $(".type_Radio:checked").val(),
			title 	: $('.search_Word').val()},
	dataType:'json',
	success:function(qqq){
		$("#ttt").empty();
		var row = '';
		$.each(qqq,function(i,e){
			row +="<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>";
			row +="<div class='l'>가입자수<br>XX명</div>";
			row +="<div class='m'>["+e.type+"] "+e.product+" 등록일:"+e.register_date+"</div>";
			row +="<div class='rl' onclick='detail(this)'><div>상세보기</div></div>";
			row +="<div class='rr' onclick='modify(this)'><div>수정하기</div></div>";
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
	
	gogo =  "product/fund/Detail";
	$('.subTitle').text('펀드상품 정보');
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	product : $(me).parent('[data-product-name]').data("product-name")},
		dataType:'json',
		success:function(qqq){
			$(".scrollB").remove();
			$(".search_Box").remove();
			var box = '';
			box +=	"<div class='infoBox'>";
			box +=	"<div class='infoTop'>";
			box +=	"<div class='infoTop_Left'>";
			box +=	"<div class='infoTop_Title'>"+qqq.product+"</div><br>"; // 상품명
			box +=	"<div class='infoTop_Info'>"+qqq.product_info+"</div>"; // ① 뭐시기뭐시기 (상품내용)
			box +=	"<br><div class='infoTop_Info'><div class='infoTop_Type'>상품종류</div>"+qqq.type+"</div>";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>운용사&ensp;&ensp;</div>"+qqq.management+"</div>";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>위험분류</div>"+qqq.sector+"</div>";
			box +=	"</div>";
			box +=	"<div class='infoTop_Rigth'>";
			box +=	"기준가 "+qqq.price+"<br>수정기준가 "+qqq.price_modify+"";
			box +=	"</div>";
			box +=	"</div>";
			box +=	"<div class='infoMid'><div></div></div>";
			box +=	"<div class='infoMain'>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>투자지역</div><div class='infoMain_Value'>"+qqq.area+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>"+qqq.tax+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>선취수수료</div><div class='infoMain_Value'>"+qqq.first_fee+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>년보수</div><div class='infoMain_Value'>"+qqq.fee+"</div></div>";
			box +=	"</div>";
			box +=	"<div class='infoBot'><div class='infoBot_Back' onclick='goMenu(this)' data-menu-name='admin/Product/Fund'>목록으로</div></div>";
			box +=	"</div>";
			$("#mm").append(box);
			isRun = false;
		},
		error:function(qqq){
			$(".search_Box").empty();
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			row.append($("<div class='m'>검색결과가 존재하지 않습니다.</div>"));
			isRun = false;
		}	
	});
};

function add(){
	$(".scrollB").remove();
	$(".search_Box").remove();
	var box = '';
	box +=	"<div class='infoBox'>";
	box +=	"<div class='infoMid_Guide'>상품 추가</div>";
	box +=	"<div class='infoAdmin'>";
	box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품종류</div><div class='infoMain_Value'>";
	box	+=	"<select id='sel_type'><option value='체권'>체권</option><option value='혼합자산(재간접형)'>혼합자산(재간접형)</option></select></div></div>";
	box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품명</div><div class='infoMain_Value'>";
	box +=  "<input type='text' placeholder='상품명을 입력해주세요.' id='product'></div></div>";
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>상품설명</div><div class='infoMain_Value'>";
	box +=  "<textarea placeholder='상품내용을 입력해주세요. &#13;&#10;①... &#13;&#10;②...' id='product_info'></textarea></div></div>";
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>운용사</div><div class='infoMain_Value'>";
	box +=  "<input type='text' placeholder='운용사를 입력해주세요.' id='management'>운용사를 입력해주세요.</div></div>";	
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>";
	box +=  "<select id='tax'><option value='과세'>과세</option><option value='비과세'>비과세</option></select> 과세여부를 선택해주세요.</div></div>";
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>지역</div><div class='infoMain_Value'>";
	box +=  "<select id='area'><option value='국내'>국내</option><option value='해외'>해외</option></select>투자지역을 선택해주세요.</div></div>";
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>년 보수</div><div class='infoMain_Value'>";
	box +=  "<input type='text' placeholder='0.0' id='fee'>수수료를 입력해주세요.</div></div>";
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>선취 수수료</div><div class='infoMain_Value'>";
	box +=  "<input type='text' placeholder='0.0' id='first_fee'>수수료를 입력해주세요.</div></div>";	
	box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>위험구분</div><div class='infoMain_Value'>";
	box +=  "<select id='sector'><option value='보통위험'>보통위험</option><option value='고위험'>고위험</option></select>위험구분을 선택해주세요.</div></div>";
	box +=  "</div><div class='AdminBot'><div class='AdminButton' onclick='goMenu(this)' data-menu-name='admin/Product/Fund'>상품등록취소</div>";
	box +=  "<div class='AdminButton' onclick='addReg()'>상품등록</div></div></div>";
	$("#mm").append(box);
};

function addReg(){
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "product/fund/AddReg";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{
				sel_type 		:	$('#sel_type').val(), 
				product 		:	$('#product').val(), 
				product_info	:	$('#product_info').val(),
				management 		:	$('#management').val(),
				area		 	:	$('#area').val(),
				tax 			:	$('#tax').val(),
				sector	 		:	$('#sector').val(),
				first_fee		:	$('#first_fee').val(),
				fee 			:	$('#fee').val()
		},
		dataType:'json',
		success:function(qqq){
			goProduct();
			isRun = false;
		},
		error:function(qqq){
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			isRun = false;
		}	
	});
};

function modify(me) {
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "product/fund/Modify";
	$('.subTitle').text('펀드상품 정보수정');
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	product : $(me).parent('[data-product-name]').data("product-name")},
		dataType:'json',
		success:function(qqq){
			$(".scrollB").remove();
			$(".search_Box").remove();
			var box = '';
			box +=	"<div class='infoBox'>";
			box +=	"<div class='infoMid_Guide'>상품 수정</div>";
			box +=	"<div class='infoAdmin'>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품상태</div><div class='infoMain_Value'>";
			box	+=	"<select id='status'><option value='활성'>활성</option><option value='비활성'>비활성</option></select> 현재 상태 : ["+qqq.status+"] </div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품종류</div><div class='infoMain_Value'>";
			box	+=	"<select id='sel_type' readonly='readonly'><option value='"+qqq.sel_type+"'>"+qqq.sel_type+"</option></select></div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품명</div><div class='infoMain_Value'>";
			box +=  "<input type='text' placeholder='상품명을 입력해주세요.' id='product' value='"+qqq.product+"' readonly='readonly'></div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>상품설명</div><div class='infoMain_Value'>";
			box +=  "<textarea placeholder='상품내용을 입력해주세요. &#13;&#10;①... &#13;&#10;②...' id='product_info'>"+qqq.product_info+"</textarea></div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>운용사</div><div class='infoMain_Value'>";
			box +=  "<input type='text' readonly='readonly' value='"+qqq.management+"' id='management'></div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>";
			box +=  "<select id='tax' readonly='readonly'><option value='"+qqq.tax+"'>"+qqq.tax+"</option></select> 과세여부를 선택해주세요. </div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>지역</div><div class='infoMain_Value'>";
			box +=  "<select id='area' readonly='readonly'><option value='"+qqq.area+"'>"+qqq.area+"</option></select>투자지역을 선택해주세요.</div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>년 보수</div><div class='infoMain_Value'>";
			box +=  "<input type='text' readonly='readonly' value='"+qqq.fee+"' id='fee'></div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>선취 수수료</div><div class='infoMain_Value'>";
			box +=  "<input type='text' readonly='readonly' value='"+qqq.first_fee+"' id='first_fee'></div></div>";
			box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>위험구분</div><div class='infoMain_Value'>";
			box +=  "<select id='sector' readonly='readonly'><option value='"+qqq.sector+"'>"+qqq.sector+"</option></select></div></div>";
			box +=  "</div><div class='AdminBot'><div class='AdminButton' onclick='goMenu(this)' data-menu-name='admin/Product/Fund'>수정취소</div>";
			box +=  "<div class='AdminButton' onclick='modReg()' data-menu-name='product/fund/ModifyReg'>수정등록</div></div></div>";
			$("#mm").append(box);
			isRun = false;
		},
		error:function(qqq){
			$(".search_Box").empty();
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			row.append($("<div class='m'>검색결과가 존재하지 않습니다.</div>"));
			isRun = false;
		}	
	});
	
};

function modReg(){
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "product/fund/ModifyReg";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	status			:	$('#status').val(),
				product			:	$('#product').val(),
				product_info	:	$('#product_info').val()
		},
		dataType:'json',
		success:function(qqq){
			goProduct();
			isRun = false;
		},
		error:function(qqq){
			$("#ttt").empty();
			var row = $("<div class='box'></div>");
			isRun = false;
		}	
	});
};

function number_Pattern(num) {	//3자리마다 ,찍기 
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function goProduct() {	//메뉴 이동용
	var go ='<form name="pag" action="SJAdmin" method="post"></form>';
	$("#mid").append(go);
	document.pag.submit(); 
};

function goMenu(qq) {	//메뉴 이동용
	var menu = $(qq).data("menu-name"); 
    var name = $(qq).parent('[data-product-name]').data("product-name");
	
	var go ='<form name="pag" action="SJAdmin" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+menu+"'/>";
	go +="<input type='hidden' name='dt' value='"+name+"'/>";
	go +="</form>";
	$("#mid").append(go);
	
	document.pag.submit(); 
};
</script>
<div class='subTitle'>펀드상품 관리</div>