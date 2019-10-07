<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var selected_Product = "예금";
var selected_Type = "";
var path = "product/deposit/";
var gogo = "layout/"+path+"all.jsp";
var isRun = false; 		//아작스 중복실행 확인
var main = '';

main +="<div class='search_Box' >";
main +="<input class='product_Radio' type = 'radio' name = 'group'  value='예금' checked='checked'/>예금</input>";
main +="<input class='product_Radio' type = 'radio' name = 'group' value='적금'>적금</input>";
main +="<select class='type_Select' size='1'><option value=''>선택해주세요</option></select>"
main +="<input class='search_Word' type = 'text' value=''/>";
main +="<div class='search_Button' >검색</div>";
main +="</div><div id ='mm'>";
main +="<div class='scrollB'>"; //스크롤바
main +="<div id = 'ttt'></div>";
main +="</div></div>";
$("#mid").append(main);

$(".product_Radio").on("click", function(){
	selected_Product = this.value;
	
	if (selected_Product == "예금")
		path = "product/deposit/";	
	else 
		path = "product/saving/";
	
	$.ajax({	
		url:"layout/product/deposit_type.jsp",
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

	$('.search_Word').val('');
	ajax_go();
});

$('.type_Select').on("click", function(){	
	selected_Type = this.value;
});
	
$(".search_Button").on("click", function() {
	ajax_go();
});

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
			row +="<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>";
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
			box +=	"<div class='infoTop_Info'>"+qqq.deposits_info+"</div>"; // ① 뭐시기뭐시기 (상품내용)
			box +=	"<br><div class='infoTop_Info'><div class='infoTop_Type'>상품종류</div>"+qqq.type+"</div>";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>가입기간</div>";
			var month = '';
			$.each(qqq.month.split('#'),function(i,e){
				month +=	e+"개월, "; 
			});
			month = month.substring(0, month.lastIndexOf(","));
			box += 	month+"</div>";
			var min = number_Pattern(qqq.min_sum)+"원";
			var max = number_Pattern(qqq.max_sum)+"원";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>가입금액</div>"+min+" 부터 최대 "+max+" 까지</div>";
			box +=	"</div>";
			box +=	"<div class='infoTop_Rigth'>";
			box +=	"<img src='img/test1.png' alt='' /><br>최저 연 &ensp;최고 연<br>";
			box +=	"<div class='infoTop_Percent'>&ensp; "+qqq.min_interest+"~"+qqq.max_interest+"%</div><br>[납입방식 "+qqq.type+"기준]";
			box +=	"</div>";
			box +=	"</div>";
			box +=	"<div class='infoMid'><div class='infoMid_Join' onclick='goMenu(this)' data-menu-name='"+path+"Join'>가입</div></div>";
			box +=	"<div class='infoMain'>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>납입방법</div><div class='infoMain_Value'>"+qqq.type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div><div class='infoMain_Value'>"+qqq.interest_type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>["+qqq.tax+"]</div></div>";	
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>자동재예치</div><div class='infoMain_Value'>["+qqq.retention+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>일부해지</div><div class='infoMain_Value'>["+qqq.partialization+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>우대이자율</div><div class='infoMain_Value'>";
			var pi = qqq.prf_interest.split('#');
			$.each(qqq.prf_content.split('#'),function(i,e){
				box +=	i+1+". "+e+" 연"+pi[i]+"% 우대<br>";	 
			});
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
			$(".scrollB").remove();
			$(".search_Box").remove();
			var box = '';
			box +=	"<div class= 'infoBox'>";
			box +=	"<div class='joinMain'>";
			box +=	"<div class='join_Guide'>출금정보</div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>출금계좌번호</div><div class='infoMain_Value'>";
			box +=	"<select id=''>";
			var ali = qqq.alias.split('#');
			$.each(qqq.account_number.split('#'),function(i,e){
				box +=	"<option value='"+e+"'>"+e+"["+ali[i]+"]</option>";	//계좌번호
			});
			box +=	"</select><button>잔액조회</button></div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>계좌비밀번호</div><div class='infoMain_Value'>";
			box +=	"<input type='text' placeholder='숫자4자리'><button>계좌 비밀번호 오류 횟수조회</button></div></div>";
			box +=	"<div class='join_Guide'>신규계좌 정보</div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>적립방식</div>";
			box +=	"<div class='infoMain_Value'>"+qqq.type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div>";
			box +=	"<div class='infoMain_Value'>"+qqq.interest_type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품기간</div>";
			box +=	"<div class='infoMain_Value'><select id=''>"
			$.each(qqq.month.split('#'),function(i,e){
				box +=	"<option value='"+e+"'>"+e+" 개월</option>";
			});
			box	+=	"</select></div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>신규금액</div>";
			box +=	"<div class='infoMain_Value'><input type='text' placeholder='숫자4자리'></div><br>";
			box	+=	"<div class='infoMain_Value'><button value='5000000'>500만</button><button value='1000000'>100만</button>";
			box +=	"<button value='500000'>50만</button><button value='100000'>10만</button><button value='50000'>5만</button>";
			box	+=	"<button value='10000'>1만</button><button value='1000'>1천</button><button>정정</button></div></div>";	
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>신규계좌 비밀번호</div>";
			box	+=	"<div class='infoMain_Value'><input type='text' placeholder='숫자4자리'></div></div>"
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호 확인</div><div class='infoMain_Value'>";
			box	+=	"<input type='text' placeholder='숫자4자리'></div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>계좌별명 (선택사항)</div>";
			box	+=	"<div class='infoMain_Value'><input type='text' placeholder='10자 이내'></div></div>";
			box	+=	"<div class='join_Guide'>자동이체 정보</div><div class='infoMain_Info'>";
			box	+=	"<div class='infoMain_Type'>자동이체 신청</div><div class='infoMain_Value'>";
			box	+=	"<input class='product_Radio' type = 'radio' name = 'r_auto'  value='신청' checked='checked'/>신청</input>";
			box	+=	"<input class='product_Radio' type = 'radio' name = 'r_auto' value='미신청'>미신청</input></div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>자동이체 기간</div><div class='infoMain_Value'>";
			box	+=	"<input class='product_Radio' type = 'text' id='startDate'></input>~";
			box	+=	"<input class='product_Radio' type = 'text' id='endDate'></input></div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>자동이체 금액</div>";
			box	+=	"<div class='infoMain_Value'>매월 <input type='text' placeholder='0'>원</div></div>";
			box	+=	"</div></div>"
			box	+=	"<div class='AdminBot'><div class='AdminButton'>취소</div><div class='AdminButton'>작성</div></div>"
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

function number_Pattern(num) {	//3자리마다 ,찍기 
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function goMenu(qq) {	//메뉴 이동용
	var menu = $(qq).data("menu-name"); 
    var name = $(qq).parent('[data-product-name]').data("product-name");
	
	var go ='<form name="pag" action="index.jsp" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+menu+"'><input>";
	go +="<input type='hidden' name='dt' value='"+name+"'><input>";
	go +="</form>";
	$("#mid").append(go);
	
	document.pag.submit(); 
};
</script>
<div class='subTitle'>예금·적금 신규</div>