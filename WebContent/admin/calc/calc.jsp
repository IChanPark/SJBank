<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
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

function goProduct() {	//메뉴 이동용
	var go ='<form name="pag" action="index.jsp" method="post"></form>';
	$("#mid").append(go);
	document.pag.submit(); 
};

/* 
$('#startDate').datepicker().on("Close",function( selectedDate ) {
	$('#endDate').datepicker( "option", "minDate", selectedDate );
});
$('#endDate').datepicker().on("Close",function( selectedDate ) {
	$('#startDate').datepicker( "option", "maxDate", selectedDate );
});
$('#endDate').datepicker({ maxDate: "+1M" });

$('input[name=startDate]').datepicker('disable').removeAttr('disabled');
$('input[name=endDate]').datepicker('disable').removeAttr('disabled');
*/
</script>
<style>
.infoMain_Value input[type=radio]{ margin-right: 10px; }
.infoMain_Value select[id=sel_type]{ margin-right: 10px; }
.table15_1 table { width:100%; margin:15px 0; border:0; margin-top: 20px;}
.table15_1 th { font-weight:bold; background-color:#c6c6c6; color:#202020 }
.table15_1,.table15_1 th,.table15_1 td { font-size:0.95em; text-align:center; padding:4px; border-collapse:collapse;}
.table15_1 th { border: 1px solid #c6c6c6; border-width:1px }
.table15_1 td { border: 1px solid #c6c6c6; border-width:1px }
.table15_1 tr { border: 1px solid #ffffff; }
.table15_1 tr:nth-child(odd){ background-color:#f7f7f7; }
.table15_1 tr:nth-child(even){ background-color:#ffffff; }
.table15_1 
</style>
<div class='subTitle'>정산</div>
<div class= "infoBox">
	<div class="infoAdmin">
		<div class="infoMain_Info"><div class="infoMain_Type">검색날짜 선택</div><div class="infoMain_Value">
			일별<input type='radio' name= 'radio_date' value='d'/>월별<input type='radio' name= 'radio_date' value='m'/>
			년도별<input type='radio' name= 'radio_date' value='y'/></div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">상품 선택</div><div class="infoMain_Value">
			전체<input type='radio' name= 'type' value='a'/>예금<input type='radio' name= 'type' value='d'/>
			적금<input type='radio' name= 'type' value='s'/>펀드<input type='radio' name= 'type' value='f'/>
			</div></div>
		<div class="infoMain_Info"><div class="infoMain_Type">검색</div><div class="infoMain_Value">
			<select name="" id="sel_type"><option value=''>구분</option></select><input type="text" placeholder="검색어를 입력해주세요.">
			<button id="ser">검색</button>
			</div></div>	
		
		<table class=table15_1>
		<tr>
			<th>gd</th><th>gd</th><th>gd</th><th>gd</th>
			<th>gd</th><th>gd</th><th>gd</th><th>gd</th>
		</tr>
		<tr>
			<td>1</td><td>2</td><td>3</td><td>3</td>
			<td>3</td><td>3</td><td>3</td><td>3</td>
		</tr>
		<tr>
			<td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td>
			<td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td>
		</tr>
		<tr>
			<td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td>
			<td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td>
		</tr>
		<tr>
			<td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td>
			<td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td><td>SAMPLE</td>
		</tr>
		</table>
			
			
			
		
		
	</div>
		
	</div>
</div>