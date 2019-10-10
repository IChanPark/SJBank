<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var selected_Type = '';
var isRun = false; 		//아작스 중복실행 확인
var main = '';
var jbAry = new Array();
main +="<div class='search_Box' >";
main +="<select class='type_Select' size='1'>"
main +="<select class='type_Select' size='1'><option value=''>선택해주세요</option></select>"
main +="<input class='search_Word' type = 'text' value=''/>";
main +="<div class='search_Button' onclick='add(this)' >검색</div>";
main +="<div class='search_Button' onclick='goMenu(this)' data-menu-name='admin/Deposits/Depositsadd'>등록하기</div>";
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

function option(){
	$.ajax({	
		url:"admin/deposits/deposit_type.jsp",
		type:'post',
		dataType:'json',
		success:function(qqq){
			$(".type_Select").empty();
			main ="<option value=''>선택해주세요</option>";
			$.each( qqq ,function(i,e){
				main += "<option value='"+e.name+"'>"+e.name+"</option>";
				jbAry[i]=e;
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
	
	$.ajax({	
	url:"admin/deposits/select.jsp",
	type:'post',
	data:{	type	: selected_Type,
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
			row +="<div class='rr' onclick='goMenu(this)' data-menu-name='admin/Deposits/Depositsadd'><div>수정하기</div></div>";
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
	
	gogo =  "admin/deposits/detail.jsp";
	$('.subTitle').text('예금상품 정보');
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
			box +=	"<div class='infoMid'><div>　</div></div>";
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
			box +=	"<div class='infoBot'><div class='infoBot_Back' onclick='goMenu(this)' data-menu-name='admin/Deposits/Deposits'>목록으로</div></div>";
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

function add(me){
	if(isRun == true)
		return;
	isRun = true;
	
	gogo =  "admin/deposits/add.jsp";
	$('.subTitle').text('예금');
	$.ajax({	
		url:gogo,
		type:'post',
		dataType:'json',
		success:function(qqq){
			$(".scrollB").remove();
			$(".search_Box").remove();
			var box = '';
			box +=	"<div class='infoBox'>";
			box +=	"<div class='infoMid_Guide'>상품 추가</div>";
			box +=	"<div class='infoAdmin'>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품종류</div><div class='infoMain_Value'>"
				$.each(qqq.month.split('#'),function(i,e){
					month +=	e+"개월, "; 
				});
			
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
			box +=	"<div class='infoMid'><div>　</div></div>";
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
			box +=	"<div class='infoBot'><div class='infoBot_Back' onclick='goMenu(this)' data-menu-name='admin/Deposits/Deposits'>목록으로</div></div>";
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

function number_Pattern(num) {	//3자리마다 ,찍기 
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function goMenu(qq) {	//메뉴 이동용
	var menu = $(qq).data("menu-name"); 
    var name = $(qq).parent('[data-product-name]').data("product-name");
	
	var go ='<form name="pag" action="admin.jsp" onsubmit="return false;" method="post">';
	go +="<input type='hidden' name='hid_t' value='"+menu+"'><input>";
	go +="<input type='hidden' name='dt' value='"+name+"'><input>";
	go +="</form>";
	$("#mid").append(go);
	
	document.pag.submit(); 
};
</script>
<div class='subTitle'>예금상품 관리</div>
<div class= "infoBox">
	<div class='infoMid_Guide'>상품 추가</div>
	<div class="infoAdmin">
		<div class="infoMain_Info"><div class="infoMain_Type">상품종류</div><div class="infoMain_Value">
			<select name="" id=""><option value="정기">정기</option></select></div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">상품명</div><div class="infoMain_Value">
			<input type="text" placeholder="상품명을 입력해주세요."></div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">상품내용</div><div class="infoMain_Value">
			<textarea placeholder="상품내용을 입력해주세요. &#13;&#10;① 뭐시기뭐시기 &#13;&#10;② 뭐시기" rows="10"></textarea></div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">상품기간</div><div class="infoMain_Value">
			<input type="text" placeholder="상품기간을 입력해주세요." > 숫자만 입력부탁드리며 상품기간 다중 등록 시 "," 로 구분하여 입력해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">이자지급방식</div><div class="infoMain_Value">
			<select name="" id=""><option value="만기일시">만기일시</option></select> 이자 지급방식을 선택해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">세금</div><div class="infoMain_Value">
			<select name="" id=""><option value="비과세">비과세200</option></select> 과세여부를 선택해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">최소납입금액</div><div class="infoMain_Value">
			<input type="text" placeholder="금액을 입력해주세요."> 최소 납입금액을 입력해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">최대납입금액</div><div class="infoMain_Value">
			<input type="text" placeholder="금액을 입력해주세요."> 최대 납입금액을 입력해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">자동재예치</div><div class="infoMain_Value">
			<select name="" id=""><option value="불가">불가</option></select> 자동재예치 가능 여부를 선택해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">일부해지</div><div class="infoMain_Value">
			<select name="" id=""><option value="불가">불가</option></select> 일부해지 가능 여부를 선택해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">기준금리</div><div class="infoMain_Value">
			<input type="text" placeholder=""> 기준 금리를 입력해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">최대금리</div><div class="infoMain_Value">
			<input type="text" placeholder=""> 최대 금리를 입력해주세요.</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">우대항목</div><div class="infoMain_Value">
			<select name="" id=""><option value="펀드">우대구분(펀드)</option></select>
			<select name="" id=""><option value="보유시">우대조건(보유시)</option></select>
			<select name="" id=""><option value="0.1">우대이자율(0.0%)</option></select>추가버튼
		</div>
		
	</div>
		
	</div>
	<div class="AdminBot"><div class="AdminButton">취소</div>
	<div class="AdminButton">작성</div></div>
</div>