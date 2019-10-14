<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var isRun = false; 		//중복실행 방지
var main = '';
var sum = 0;
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
	sum = Number($(aa).val()) + Number(sum);
	$('#sum').val(sum);
	$('#sum').val(number_Pattern(sum)+"원");
	
};
function zero(aa){
	sum = 0;
	$('#sum').val(sum);
	$('#view_money').val(number_Pattern(sum)+"원");
}

function option(){
	$.ajax({	
		url:"product/deposit/Type",
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
	url:"product/deposit/Select",
	type:'post',
	data:{	type	: $(".type_Radio:checked").val(),
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

	$('.subTitle').text('예금 상품 안내');
	$.ajax({	
		url:"product/deposit/Detail",
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
			$.each(qqq.month.split(','),function(i,e){
				month +=	e+"개월, "; 
			});
			month = month.substring(0, month.lastIndexOf(","));
			box += 	month+"</div>";
			var min = number_Pattern(qqq.min_sum)+"원";
			var max = number_Pattern(qqq.max_sum)+"원";
			box +=	"<div class='infoTop_Info'><div class='infoTop_Type'>가입금액</div>"+min+" 부터 최대 "+max+" 까지</div>";
			box +=	"</div>";
			box +=	"<div class='infoTop_Rigth'>";
			box +=	"<img src='img/test1.png' alt=''></img><br>최저 연 &ensp;최고 연<br>";
			box +=	"<div class='infoTop_Percent'>&ensp; "+qqq.min_interest+"~"+qqq.max_interest+"%</div><br>[납입방식 "+qqq.type+"기준]";
			box +=	"</div>";
			box +=	"</div>";
			box +=	"<div class='infoMid' data-product-name='"+qqq.product+"' ><div class='infoMid_Join' onclick='join(this)'>가입</div></div>";
			box +=	"<div class='infoMain'>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>납입방법</div><div class='infoMain_Value'>"+qqq.type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div><div class='infoMain_Value'>"+qqq.interest_type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>["+qqq.tax+"]</div></div>";	
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>자동재예치</div><div class='infoMain_Value'>["+qqq.retention+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>일부해지</div><div class='infoMain_Value'>["+qqq.partialization+"]</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>우대이자율</div><div class='infoMain_Value'>";
			var pf = qqq.preferential.split('#');
			var pi = qqq.prf_interest.split('#');
			$.each(qqq.prf_content.split('#'),function(i,e){
				box +=	i+1+". "+pf+" "+e+" 연"+pi[i]+"% 우대<br>";	 
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
	
	$.ajax({	
		url:"product/deposit/Join",
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
			box +=	"<input onfocusout='ev()' type='text' placeholder='숫자4자리' id='accPW'></input></div></div>"; // <button>계좌 비밀번호 오류 횟수조회</button>
			box +=	"<div class='join_Guide'>신규계좌 정보</div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>적립방식</div>";
			box +=	"<div class='infoMain_Value' data-join-type='"+qqq.type+"'>"+qqq.type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div>";
			box +=	"<div class='infoMain_Value data-join-interest_type='"+qqq.interest_type+"''>"+qqq.interest_type+"</div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품기간</div>";
			box +=	"<div class='infoMain_Value'><select id='month'>"
			$.each(qqq.month.split(','),function(i,e){
				box +=	"<option value='"+e+"'>"+e+" 개월</option>";
			});
			box	+=	"</select></div></div>";
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>신규금액</div>";
			box +=	"<div class='infoMain_Value'><input type='text' id=view_money readonly='readonly'></input><input onfocusout='ev2()' type='text' placeholder='0' id=sum></input></div><br>";
			box	+=	"<div class='infoMain_Value'><button onclick='add(this)' value='5000000'>500만</button><button onclick='add(this)' value='1000000'>100만</button>";
			box +=	"<button onclick='add(this)' value='500000'>50만</button><button onclick='add(this)' value='100000'>10만</button><button onclick='add(this)' value='50000'>5만</button>";
			box	+=	"<button onclick='add(this)' value='10000'>1만</button><button onclick='add(this)' value='1000'>1천</button><button onclick='zero(this)'>정정</button></div></div>";	
			box +=	"<div class='infoMain_Info'><div class='infoMain_Type'>신규계좌 비밀번호</div>";
			box	+=	"<div class='infoMain_Value'><input type='text' placeholder='숫자4자리' id='newPW'></input></div></div>"
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>비밀번호 확인</div><div class='infoMain_Value'>";
			box	+=	"<input onfocusout='ev3()' type='text' placeholder='숫자4자리' id='newPWchk'></input></div></div>";
			box	+=	"<div class='infoMain_Info'><div class='infoMain_Type'>계좌별명 (선택사항)</div>";
			box	+=	"<div class='infoMain_Value'><input onfocusout='ev4()' type='text' placeholder='10자 이내' id='alias'></input></div></div>";
			box	+=	"</div>";
			box	+=	"<div class='AdminBot' data-product-name='"+qqq.product+"'><div class='join_Button' onclick='goMenu(this)' data-menu-name='product/Deposit'>이전</div>";
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

function ev(){if(!check(/^[\d]{4}$/,$('#accPW'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev2(){if(!check(/^[\d,]{0,13}$/,$('#sum'),'13자리 이하 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev3(){if(!check2(/^[\d]{4}$/,$('#newPW'),$('#newPWchk'),'4자리 숫자만 입력가능합니다.')){return false;}else{return true;}};
function ev4(){if(!check(/^[a-zA-Z0-9가-힣\s]{0,10}[a-zA-Z0-9가-힣]$/,$('#alias'),'·선택사항·별명은 10자이내로 작성가능합니다.')){return false;}else{return true;}};

function joinReg(me){
	if(ev()&&ev2()&&ev3()&&ev4()&&true){
		goReg(me);
	}
};

function goReg(me) {
	if(isRun == true)
		return;
	isRun = true;
	
	$.ajax({	
		url:"product/deposit/JoinReg",
		type:'post',
		data:{	product 		:	$(me).parent('[data-product-name]').data("product-name"),
				account_number	:	$('#account_number').val(),
				pw				:	$('#accPW').val(),
				type			:	$('[data-join-type]').data("join-type"),
				interest_type	:	$('[data-join-interest_type]').data("join-interest_type"),
				month			:	$('#month').val(),
				sum				:	$('#sum').val(),//여까지 작업 
				newPW			:	$('#newPW').val(),
				newPWchk		:	$('#newPWchk').val(),
				alias			:	$('#alias').val()
		},
		dataType:'json',
		success:function(qqq){
			goProduct();
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

function goProduct() {	//메뉴 이동용
	var go ='<form name="pag" action="SJBank" method="post"></form>';
	$("#mid").append(go);
	document.pag.submit(); 
};
</script>
<div class='subTitle'>예금 신규</div>