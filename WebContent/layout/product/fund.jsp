<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var selected_Product = "펀드";
var selected_Type = "";
var path = "product/fund/";
var gogo = "layout/"+path+"all.jsp";
var isRun = false; 		//아작스 중복실행 확인
var main = '';
var su = 0;
main +="<div class='search_Box' >";
main +="<select class='type_Select' size='1'><option value=''>선택해주세요</option></select>"
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



$('.type_Select').on("click", function(){	
	selected_Type = this.value;
});
	
$(".search_Button").on("click", function() {
	ajax_go();
});

/* $("#money").on("propertychange change keyup paste input"), function(){	//모든 상태변경 감지
	var currentVal = $(this).val();
    if(currentVal == oldVal) {
        return;
    }
	sum = sum + Number(sum);
	$('#view_money').val(number_Pattern(sum)+"원");
	oldVal == currentVal;
}; */

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
		url:"layout/product/fund_type.jsp",
		type:'post',
		data:{	product	: selected_Product},
		dataType:'json',
		success:function(qqq){
			$(".type_Select").empty();
			main ="<option value=''>선택해주세요</option>";
			$.each( qqq ,function(i,e){
				main += "<option value='"+e.name+"'>"+e.name+"</option>";
			});
			$(".type_Select").append(main);
			isRun = false;
		},
		error:function(qqq){
			isRun = false;
		}	
	});	
}

function ajax_go() {
	gogo = "layout/"+path+"select.jsp";
	
	$.ajax({	
	url:gogo,
	type:'post',
	data:{	type	: selected_Type,
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
	
	gogo =  "layout/"+path+"detail.jsp";
	$('.subTitle').text(selected_Product+'상품 안내');
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
			//box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>가입기간</div>";
		/* 	var month = '';
			$.each(qqq.month.split('#'),function(i,e){
				month +=	e+"개월, "; 
			});
			month = month.substring(0, month.lastIndexOf(","));
			box += 	month+"</div>";
			var min = number_Pattern(qqq.min_sum)+"원";
			var max = number_Pattern(qqq.max_sum)+"원"; */
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>초기기준가</div>"+qqq.price+"</div>";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>수정기준가</div>"+qqq.price_modify+"</div>";
			box +=	"</div>";
			box +=	"<div class='infoTop_Rigth'>";
			box +=	"<img src='img/test1.png' alt=''></img><br><br>";
			box +=	"</div>";
			box +=	"</div>";
			box +=	"<div class='infoMid' data-product-name='"+qqq.product+"' ><div class='infoMid_Join' onclick='join(this)'>가입</div></div>";
			box +=	"<div class='infoMain'>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'></div>유형<div class='infoMain_Value'>"+qqq.type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>운용사</div><div class='infoMain_Value'>"+qqq.management+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>["+qqq.tax+"]</div></div>";	
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>지역</div><div class='infoMain_Value'>["+qqq.area+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품속성</div><div class='infoMain_Value'>["+qqq.property+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>선취수수료</div><div class='infoMain_Value'>["+qqq.first_fee+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>년보수</div><div class='infoMain_Value'>["+qqq.fee+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>위험등급</div><div class='infoMain_Value'>["+qqq.sector+"]</div></div>";
			//box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>등록일</div><div class='infoMain_Value'>["+qqq.register_date+"]</div></div>";
			/* var pi = qqq.prf_interest.split('#');
			$.each(qqq.prf_content.split('#'),function(i,e){
				box +=	i+1+". "+e+" 연"+pi[i]+"% 우대<br>";	 
			}); */
			box +=	"</div></div>";
			box +=	"</div>";
			box +=	"<div class='infoBot'><div class='infoBot_Back' onclick='goMenu(this)' data-menu-name='product/Deposit'>목록으로</div></div>";
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
	
	gogo =  "layout/"+path+"join.jsp";
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
			box +=	"<input type='text' placeholder='숫자4자리' id='pw'></input><button>계좌 비밀번호 오류 횟수조회</button></div></div>";
			box +=	"<div class='join_Guide'>신규가입정보</div>";
			/* box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>적립방식</div>";
			box +=	"<div class='infoMain_Value' data-join-type='"+qqq.type+"'>"+qqq.type+"</div></div>"; */
			//box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div>";
			//box +=	"<div class='infoMain_Value data-join-interest_type='"+qqq.interest_type+"''>"+qqq.interest_type+"</div></div>";
			/* box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품기간</div>";
			box +=	"<div class='infoMain_Value'><select id='month'>"
			$.each(qqq.month.split('#'),function(i,e){
				box +=	"<option value='"+e+"'>"+e+" 개월</option>";
			});
			box	+=	"</select></div></div>"; */
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
			/* box	+=	"<div class='join_Guide'>자동이체 정보</div><div class='infoMain_Info'>";
			box	+=	"<div class='infoMain_Type'>자동이체 신청</div><div class='infoMain_Value'>";
			box	+=	"<input class='auto_Radio' type = 'radio' name = 'r_auto'  value='신청' checked='checked'>신청</input>";
			box	+=	"<input class='auto_Radio' type = 'radio' name = 'r_auto' value='미신청'>미신청</input></div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>자동이체 기간</div><div class='infoMain_Value'>";
			box	+=	"<input type = 'text' id='startDate'></input>~";
			box	+=	"<input type = 'text' id='endDate'></input></div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>자동이체 금액</div>";
			box	+=	"<div class='infoMain_Value'>매월 <input type='text' placeholder='0' id='autoTrans'>원</div></div>"; */
			box	+=	"</div>";
			box	+=	"<div class='AdminBot' data-product-name='"+qqq.product+"'><div class='join_Button' onclick='goMenu(this)' data-menu-name='product/Deposit'>이전</div>";
			box +=	"<div class='join_Button' onclick='joinReg(this)'>다음</div></div>";
			$("#mm").append(box);
			$( "#startDate" ).datepicker();
			$( "#endDate" ).datepicker();
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
	
	gogo =  "layout/"+path+"joinReg.jsp";
	$.ajax({	
		url:gogo,
		type:'post',
		data:{	accType			:	selected_Product,
				product 		:	$(me).parent('[data-product-name]').data("product-name"),
				account_number	:	$('#account_number').val(),
				pw				:	$('#pw').val(),
				//type			:	$('[data-join-type]').data("join-type"),
				//interest_type	:	$('[data-join-interest_type]').data("join-interest_type"),
				//month			:	$('#month').val(),
				sum				:	$('#sum').val(),//여까지 작업 
				newPW			:	$('#newPW').val(),
				newPWchk		:	$('#newPWchk').val(),
				//auto			:	$('.auto_Radio:checked').val(),
				alias			:	$('#alias').val(),
				//startDate		:	$('#startDate').val(),	
				//finish_date		:	$('#endDate').val(),
				price_modify 	:	$('#price_modify[data-product-name]').data("product-name")
				//autoTrans		:	$('#autoTrans').val()
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
	
	var go ='<form name="pag" action="index.jsp" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+menu+"'></input>";
	go +="<input type='hidden' name='dt' value='"+name+"'></input>";
	go +="</form>";
	$("#mid").append(go);
	document.pag.submit(); 
};

function gomyfund() {	//메뉴 이동용
	var go ='<form name="pag" action="index.jsp" method="post"></form>';
	$("#mid").append(go);
	document.pag.submit(); 
};

$('#startDate').datepicker().on("Close",function( selectedDate ) {
	$('#endDate').datepicker( "option", "minDate", selectedDate );
});
$('#endDate').datepicker().on("Close",function( selectedDate ) {
	$('#startDate').datepicker( "option", "maxDate", selectedDate );
});
$('#endDate').datepicker({ maxDate: "+1M" });

$('input[name=startDate]').datepicker('disable').removeAttr('disabled');
$('input[name=endDate]').datepicker('disable').removeAttr('disabled');
</script>
<div class='subTitle'>펀드 신규</div>