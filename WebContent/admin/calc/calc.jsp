<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
var path;	//경로용
var layout;	//html 추가용
var isRun = false;	//아작스 중복실행 방지용
var month_data = {
	dateFormat: 'yy-mm', closeText: '선택', prevText: '이전달', nextText: '다음달', currentText: '오늘', weekHeader: 'Wk',
	firstDay: 0, isRTL: false, showMonthAfterYear: true, yearSuffix: '', yearRange: 'c-99:c+99',
	onClose: function(dateText, inst) {
	    var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	    var month = Number($("#ui-datepicker-div .ui-datepicker-month :selected").val()) + 1 + "";
	    $(this).val(year + "-" + (month.length == 1 ? "0" + month : month));
	},
	beforeShow: function() {}
};
layout =	"<div class= 'infoBox'>";
layout +=	"<div class='infoMain_Info'><div class='infoMain_Type'>검색날짜 선택</div><div class='infoMain_Value'>";
layout +=	"일 별<input type='radio' name= 'date_radio' value='d' checked='checked'/>월 별<input type='radio' name= 'date_radio' value='m'/>";
layout +=	"년도 별<input type='radio' name= 'date_radio' value='y'/> </div></div>";
layout +=	"<div class='infoMain_Info'><div class='infoMain_Type'>상품 선택</div><div class='infoMain_Value'>";
layout +=	"모두<input type='radio' name= 'prod_radio' value='모두' checked='checked'/>예금<input type='radio' name= 'prod_radio' value='예금'/>";
layout +=	"적금<input type='radio' name= 'prod_radio' value='적금'/>펀드<input type='radio' name= 'prod_radio' value='펀드'/></div></div>";
layout +=	"<div class='infoMain_Info'><div class='infoMain_Type'>검색</div><div class='infoMain_Value'>";
layout +=	"<button id='search_Button'>검색</button><button id='clean_Button'>초기화</button></div></div>";
layout +=	"<div class='infoMain_Info'><div class='infoMain_Type'>수익</div><div class='infoMain_Value'>";
layout +=	"<input type='text' id='sumAll' readonly='readonly' /></div></div>";
layout += 	"</div><div class='infoAdmin'><table id='info_table'><tbody id='info_tbody'>";
layout += 	"<tr><th>날짜</th><th>건수</th><th>상품종류</th><th>상세분류</th><th>수익</th></tr></tbody></table></div>";

$('#mid').append(layout);
//<input type='text' placeholder='검색어를 입력해주세요 .' id='search_Word'>
$(document).ready(function() {
	setDay();
});

$("#clean_Button").on("click", function(){//초기화
	$("input:radio[name='date_radio']:radio[value='d']").prop("checked", true);
	$("input:radio[name='prod_radio']:radio[value='모두']").prop("checked", true);
	$('#days').remove();
	$("#info_tbody").empty();
	setDay();
});
$("input:radio[name='date_radio']:radio[value='d']").on("click",function() {
	$('#days').remove();//검색날짜선택 년도별
	setDay();
});
$("input:radio[name='date_radio']:radio[value='m']").on("click",function() {
	$('#days').remove();//검색날짜선택 월별
	setMonth();
});
$("input:radio[name='date_radio']:radio[value='y']").on("click",function() {
	$('#days').remove();//검색날짜선택 년도별
	setYear();
});
$("input:radio[name='prod_radio']").on("click",function() {
	$("#types").remove();
	option();  
});
$("#search_Button").on("click", function(){
	$("#info_tbody").empty();
	ajax_go();
});

function setYear(){
    var dt = new Date();
    var ss = "", ee = "", get_year = dt.getFullYear();

    layout = "<div id='days'>시작년도<select id='start_year'>";
    for(var y = (get_year); y >= (get_year-10); y--){
    	ss+="<option value='"+y+"'>"+ y + " 년" +"</option>";
    	ee+="<option value='"+y+"'>"+ y + " 년" +"</option>";
    }
    layout += ss;
    layout += "</select> 끝나는년도 <select id='end_year'>"+ee;
    layout += "</select></div>";
    $("input:radio[name='date_radio']").parent().append(layout);
};
function setMonth(){
	layout = "<div id='days'>시작년도 월 <input type='text' id='start_month'/>";
	layout +=" 종료년도 월 <input type='text' id='end_month'/></div>";
	$("input:radio[name='date_radio']").parent().append(layout);
	$("#start_month").datepicker(month_data);
	$("#end_month").datepicker(month_data);
	$('#start_month').attr("readonly",true);
	$('#end_month').attr("readonly",true);
	$('#start_month').datepicker('setDate', new Date());
	$('#end_month').datepicker('setDate', new Date());
};
function setDay(){
	layout = "<div id='days'>시작일 <input type='text' id='start_day'/>";
	layout +=" 종료일 <input type='text' id='end_day'/></div>";
	$("input:radio[name='date_radio']").parent().append(layout);
	$('#start_day').datepicker();
	$('#end_day').datepicker();
	$('#start_day').attr("readonly",true);
	$('#end_day').attr("readonly",true);
	$('#start_day').datepicker('setDate', new Date());
	$('#end_day').datepicker('setDate', new Date());
};
function option(){
	if(isRun == true)
		return;
	isRun = true;
	
	$.ajax({	
		url:"calc/Product_type",
		type:'post',
		data:{	product	: $("input:radio[name='prod_radio']:checked").val() },
		dataType:'json',
		success:function(qqq){

			console.log('dd');
			if($("input:radio[name='prod_radio']:checked").val() != "all"){
				layout = "<div id='types'><select id='type_Select'><option value='모두'>모두</option>";
				$.each( qqq ,function(i,e){
					layout += "<option value='"+e.name+"'>"+e.name+"</option>";
				});
				layout += "</select><div>";
				$("[name='prod_radio']").parent().append(layout);
			}
			isRun = false;
		},
		error:function(qqq){
			isRun = false;
		}	
	});	
}
//console.log($("#start_day").val();

function ajax_go() {
	if(isRun == true)
		return;
	isRun = true;
	
	$.ajax({	
	url:"calc/select",
	type:'post',
	data:{	
			product		: $("input:radio[name='prod_radio']:checked").val(),
			start_year	: $("#start_year").val(),
			end_year	: $("#end_year").val(),
			start_month	: $("#start_month").val(),
			end_month 	: $('#end_month').val(),
			start_day	: $("#start_day").val(),
			end_day 	: $('#end_day').val()
	},
	dataType:'json',
	success:function(qqq){
		var row ='<tr><th>날짜</th><th>건수</th><th>상품종류</th><th>상세분류</th><th>수익</th></tr>';
		var total = 0;
		var s = 0;
		$.each(qqq,function(i,e){
			row +="<tr>";
			row +="<td class='"+e.date+"'>"+e.date+"</td>";
			row +="<td class='"+e.count+"'>"+e.count+"</td>";
			row +="<td class='"+e.product+"'>"+e.product+"</td>";
			row +="<td class='"+e.type+"'>"+e.type+"</td>";
			row +="<td class='"+e.sum+"'>"+e.sum+"</td>";
			row +="</tr>";
			total += Number(e.sum);
		});
		row += "<tr><td colspan='5'> 총금액 : "+number_Pattern(total)+"원</td></tr>";
		$("#info_tbody").append(row);
		$("#sumAll").val(number_Pattern(total)+'원');
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

</script>
<div class='subTitle'>정산</div><br>