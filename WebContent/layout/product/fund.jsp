<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var isRun = false; 		//아작스 중복실행 확인
var main = '';
var su = 0;
main +="<div class='search_Box' >";
main +="<input class='search_Word' type = 'text' value=''></input>";
main +="<div class='search_Button' >검색</div>";
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

function add(aa) {
	su = Number($(aa).val()) + Number(su);
	$('#sum').val(su);
	$('#view_money').val(number_Pattern(su)+"원");
	
};
function zero(aa){
	sum = 0;
	$('#money').val(sum);
}

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
	url:"product/fund/Select",
	type:'post',
	data:{	type	: $(".type_Radio:checked").val(),
			title 	: $('.search_Word').val()},
	dataType:'json',
	success:function(qqq){
		$("#ttt").empty();
		var row = '';
		
		$.each(qqq,function(i,e){
			row +="<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>";
			row +="<div class='l'>"+"수정기준가 "+"<br>"+e.price_modify+"</div>";
			row +="<div class='m'>["+e.type+"] "+e.product+"</div>";
			row +="<div class='rl' onclick='detail(this)'><div>상세보기</div></div>";
			row +="<div class='rr' onclick='join(this)'><div>가입하기</div></div>";
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
	$('.subTitle').text('펀드상품 안내');
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	product : $(me).parent('[data-product-name]').data("product-name")},
		dataType:'json',
		success:function(qqq){
			$(".scrollB").remove();
			$(".search_Box").remove();
			var box = '';
			//<fmt:formatNumber value="${dto.sum }" pattern="#,###원"/>
			box +=	"<div class='infoBox'>";
			box +=	"<div class='infoTop'>";
			box +=	"<div class='infoTop_Left'>";
			box +=	"<div class='infoTop_Title'>"+qqq.product+"</div><br>"; // 상품명
			box +=	"<div class='infoTop_Info'>"+qqq.product_info+"</div>"; // ① 뭐시기뭐시기 (상품내용)
			box +=	"<br><div class='infoTop_Info'><div class='infoTop_Type'>상품종류</div>"+qqq.type+"</div>";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>초기기준가</div>"+qqq.price+"</div>";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>수정기준가</div>"+qqq.price_modify+"</div>";
			box +=	"</div>";
			box +=	"<div class='infoTop_Rigth'>";
			box +=	"<img src='img/test1.png' alt=''></img><br><br>";
			box +=	"</div>";
			box +=	"</div>";
			box +=	"<div class='infoMid' data-product-name='"+qqq.product+"' ><div class='infoMid_Join' onclick='join(this)'>가입</div></div>";
			box +=	"<div class='infoMain'>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>유형</div><div class='infoMain_Value'>"+qqq.type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>운용사</div><div class='infoMain_Value'>"+qqq.management+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>["+qqq.tax+"]</div></div>";	
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>지역</div><div class='infoMain_Value'>["+qqq.area+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품속성</div><div class='infoMain_Value'>["+qqq.property+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>선취수수료</div><div class='infoMain_Value'>["+qqq.first_fee+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>년보수</div><div class='infoMain_Value'>["+qqq.fee+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>위험등급</div><div class='infoMain_Value'>["+qqq.sector+"]</div></div>";
			box +=	"</div></div>";
			box +=	"</div>";
			box +=	"<div class='infoBot'><div class='infoBot_Back' onclick='goMenu(this)' data-menu-name='product/Fund'>목록으로</div></div>";
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

function join(me) {
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "product/fund/Join";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	product : $(me).parent('[data-product-name]').data("product-name")},
		dataType:'json',
		success:function(qqq){
			$('.subTitle').text(qqq.product);
			$(".infoBox").remove();
			$(".infoMain").remove();
			$(".infoBot").remove();
			$(".scrollB").remove();
			$(".search_Box").remove();
			var box = '';
			box +=	"<div id='price_modify' data-product-name='"+qqq.price_modify+"'></div>";
			box +=	"<div class='infoBox'>";
			box +=	"<div class='joinMain'>";
			box +=	"<div class='join_Guide'>출금정보</div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>출금계좌번호</div><div class='infoMain_Value'>";
			box +=	"<select id='account_number'>";
			var ali = qqq.alias.split('#');
			$.each(qqq.account_number.split('#'),function(i,e){
				box +=	"<option value='"+e+"'>"+e+"["+ali[i]+"]</option>";	//계좌번호
			});
			box +=	"</select><button>잔액조회</button></div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>계좌비밀번호</div><div class='infoMain_Value'>";
			box +=	"<input type='text' placeholder='숫자4자리' id='pw'></input></div></div>";
			box +=	"<div class='join_Guide'>신규가입정보</div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>신규금액</div>";
			box +=	"<div class='infoMain_Value'><input type='text' placeholder='0' id=sum></input><input type='text' id=view_money readonly='readonly'></input></div><br>";
			box	+=	"<div class='infoMain_Value'><button onclick='add(this)' value='5000000'>500만</button><button onclick='add(this)' value='1000000'>100만</button>";
			box +=	"<button onclick='add(this)' value='500000'>50만</button><button onclick='add(this)' value='100000'>10만</button><button onclick='add(this)' value='50000'>5만</button>";
			box	+=	"<button onclick='add(this)' value='10000'>1만</button><button onclick='add(this)' value='1000'>1천</button><button onclick='zero(this)'>정정</button></div></div>";	
			 box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>신규계좌 비밀번호</div>";
			box	+=	"<div class='infoMain_Value'><input type='text' placeholder='숫자4자리' id='newPW'></input></div></div>"
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호 확인</div><div class='infoMain_Value'>";
			box	+=	"<input type='text' placeholder='숫자4자리' id='newPWchk'></input></div></div>"; 
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>계좌별명 (선택사항)</div>";
			box	+=	"<div class='infoMain_Value'><input type='text' placeholder='10자 이내' id='alias'></input></div></div>";
			box	+=	"</div>";
			box	+=	"<div class='AdminBot' data-product-name='"+qqq.product+"'><div class='join_Button' onclick='goMenu(this)' data-menu-name='product/Fund'>이전</div>";
			box +=	"<div class='join_Button' onclick='joinReg(this)'>다음</div></div>";
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

function joinReg(me) {
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "product/fund/JoinReg";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	accType			:	selected_Product,
				product 		:	$(me).parent('[data-product-name]').data("product-name"),
				account_number	:	$('#account_number').val(),
				pw				:	$('#pw').val(),
				sum				:	$('#sum').val(),//여까지 작업 
				newPW			:	$('#newPW').val(),
				newPWchk		:	$('#newPWchk').val(),
				alias			:	$('#alias').val(),
				price_modify 	:	$('#price_modify[data-product-name]').data("product-name")
		},
		dataType:'json',
		success:function(qqq){
			gomyfund();
			isRun = false;
		},
		error:function(qqq){
			
			isRun = false;
		}	
	});
};

function number_Pattern(num) {	//3자리마다 ,찍기 
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

function goMenu(qq) {	//메뉴 이동용
	var menu = $(qq).data("menu-name"); 
    var name = $(qq).parent('[data-product-name]').data("product-name");
	
	var go ='<form name="pag" action="SJBank" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+menu+"'></input>";
	go +="<input type='hidden' name='dt' value='"+name+"'></input>";
	go +="</form>";
	$("#mid").append(go);
	document.pag.submit(); 
};

function gomyfund() {	//메뉴 이동용
	var go ='<form name="pag" action="SJBank" method="post"></form>';
	$("#mid").append(go);
	document.pag.submit(); 
};
</script>
<div class='subTitle'>펀드 신규</div>