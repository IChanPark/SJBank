<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<script type="text/javascript">
var isRun = false;       //아작스 중복실행 확인
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
		url:"product/saving/Type",
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
   url:"product/saving/Admin_Select",
   type:'post',
   data:{   
	   	type	: $(".type_Radio:checked").val(),
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
   
   gogo =  "product/saving/Detail";
   $('.subTitle').text('적금상품 정보');
   $.ajax({   
      url:gogo,
      type:'post',
      data:{   product : $(me).parent('[data-product-name]').data("product-name")},
      dataType:'json',
      success:function(qqq){
         $(".scrollB").remove();
         $(".search_Box").remove();
         var box = '';
         box +=   "<div class='infoBox'>";
         box +=   "<div class='infoTop'>";
         box +=   "<div class='infoTop_Left'>";
         box +=   "<div class='infoTop_Title'>"+qqq.product+"</div><br>"; // 상품명
         box +=   "<div class='infoTop_Info'>"+qqq.deposits_info+"</div>"; // ① 뭐시기뭐시기 (상품내용)
         box +=   "<br><div class='infoTop_Info'><div class='infoTop_Type'>상품종류</div>"+qqq.type+"</div>";
         box +=   "<div class='infoTop_Info'><div class='infoTop_Type'>가입기간</div>";
         var month = '';
         $.each(qqq.month.split('#'),function(i,e){
            month +=   e+"개월, "; 
         });
         month = month.substring(0, month.lastIndexOf(","));
         box +=    month+"</div>";
         var min = number_Pattern(qqq.min_sum)+"원";
         var max = number_Pattern(qqq.max_sum)+"원";
         box +=   "<div class='infoTop_Info'><div class='infoTop_Type'>가입금액</div>"+min+" 부터 최대 "+max+" 까지</div>";
         box +=   "</div>";
         box +=   "<div class='infoTop_Rigth'>";
         box +=   "<img src='img/test1.png' alt='' /><br>최저 연 &ensp;최고 연<br>";
         box +=   "<div class='infoTop_Percent'>&ensp; "+qqq.min_interest+"~"+qqq.max_interest+"%</div><br>[납입방식 "+qqq.type+"기준]";
         box +=   "</div>";
         box +=   "</div>";
         box +=   "<div class='infoMid'><div>　</div></div>";
         box +=   "<div class='infoMain'>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>납입방법</div><div class='infoMain_Value'>"+qqq.type+"</div></div>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div><div class='infoMain_Value'>"+qqq.interest_type+"</div></div>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>["+qqq.tax+"]</div></div>";   
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>자동재예치</div><div class='infoMain_Value'>["+qqq.retention+"]</div></div>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>일부해지</div><div class='infoMain_Value'>["+qqq.partialization+"]</div></div>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>우대이자율</div><div class='infoMain_Value'>";
         var pf = qqq.preferential.split('#');
         var pi = qqq.prf_interest.split('#');
         $.each(qqq.prf_content.split('#'),function(i,e){
            box +=   i+1+". "+pf+" "+e+" 연"+pi[i]+"% 우대<br>";    
         });
         box +=   "</div></div>";
         box +=   "</div>";
         box +=   "<div class='infoBot'><div class='infoBot_Back' onclick='goMenu(this)' data-menu-name='admin/Product/Saving'>목록으로</div></div>";
         box +=   "</div>";
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
   box +=   "<div class='infoBox'>";
   box +=   "<div class='infoMid_Guide'>상품 추가</div>";
   box +=   "<div class='infoAdmin'>";
   box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>상품종류</div><div class='infoMain_Value'>";
   box +=   "<select id='sel_type'><option value='정기'>정기</option><option value='자유'>자유</option><option value='정기/자유'>정기/자유</option></select></div></div>";
   box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>상품명</div><div class='infoMain_Value'>";
   box +=  "<input type='text' placeholder='상품명을 입력해주세요.' id='product'></div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>상품설명</div><div class='infoMain_Value'>";
   box +=  "<textarea placeholder='상품내용을 입력해주세요. &#13;&#10;①... &#13;&#10;②...' id='product_info'></textarea></div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>상품기간</div><div class='infoMain_Value'>";
   box +=  "<input type='text' placeholder='상품기간을 입력해주세요.' id='month'> 숫자만 입력부탁드리며 상품기간 다중 등록 시 ',' 로 구분하여 입력해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div><div class='infoMain_Value'>";
   box +=  "<select id='interest_type'><option value='만기일시지급-단리식'>만기일시지급-단리식</option></select></div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>";
   box +=  "<select id='tax'><option value='과세'>과세</option><option value='비과세'>비과세</option></select> 과세여부를 선택해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>최소납입금액</div><div class='infoMain_Value'>";
   box +=  "<input type='text' placeholder='금액을 입력해주세요.' id='min_sum'> 최소 납입금액을 입력해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>최대납입금액</div><div class='infoMain_Value'>";
   box +=  "<input type='text' placeholder='금액을 입력해주세요.' id='max_sum'> 최대 납입금액을 입력해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>자동재예치</div><div class='infoMain_Value'>";
   box +=  "<select id='retention'><option value='가능'>가능</option><option value='불가'>불가</option></select> 자동재예치 가능 여부를 선택해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>일부해지</div><div class='infoMain_Value'>";
   box +=  "<select id='partialization'><option value='가능'>가능</option><option value='불가'>불가</option></select> 일부해지 가능 여부를 선택해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>기준금리</div><div class='infoMain_Value'>";
   box +=  "<input type='text' placeholder='0.0' id='min_interest'> 기준 금리를 입력해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>최대금리</div><div class='infoMain_Value'>";
   box +=  "<input type='text' placeholder='0.0' id='max_interest'> 최대 금리를 입력해주세요.</div></div>";
   box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>우대항목</div><div class='infoMain_Value'>";
   box +=  "<select class='preferential'>우대구분<option value='예금'>예금</option><option value='적금'>적금</option><option value='펀드'>펀드</option></select> ";
   box +=  "<select class='prf_content'>우대조건<option value='보유시'>보유시</option></select> ";
   box +=  " 우대 추가금리를 입력해주세요. <input type='text' placeholder='0.0' class='prf_interest'> </div></div>";
   box +=  "</div><div class='AdminBot'><div class='AdminButton' onclick='goMenu(this)' data-menu-name='admin/Product/Saving'>상품등록취소</div>";
   box +=  "<div class='AdminButton' onclick='addReg()'>상품등록</div></div></div>";
   $("#mm").append(box);
};

function addReg(){
   if(isRun == true)
      return;
   isRun = true;
   
   gogo =  "product/saving/AddReg";
   $.ajax({   
      url:gogo,
      type:'post',
      data:{   
    	  	sel_type		:   $('#sel_type').val(), 
            product			:   $('#product').val(), 
            product_info	:   $('#product_info').val(),
            month      		:   $('#month').val(),
            interest_type  	:   $('#interest_type').val(),
            tax          	:   $('#tax').val(),
            min_sum       	:   $('#min_sum').val(),
            max_sum       	:   $('#max_sum').val(),
            retention      	:   $('#retention').val(),
            partialization 	:   $('#partialization').val(),
            min_interest   	:   $('#min_interest').val(),
            max_interest   	:   $('#max_interest').val(),
            preferential   	:   $('.preferential').val(),
            prf_content  	:   $('.prf_content').val(),
            prf_interest    :   $('.prf_interest').val()
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
   
   gogo =  "product/saving/Modify";
   $('.subTitle').text('적금상품 정보수정');
   $.ajax({   
      url:gogo,
      type:'post',
      data:{   product : $(me).parent('[data-product-name]').data("product-name")},
      dataType:'json',
      success:function(qqq){
         $(".scrollB").remove();
         $(".search_Box").remove();
         var box = '';
         box +=   "<div class='infoBox'>";
         box +=   "<div class='infoMid_Guide'>상품 수정</div>";
         box +=   "<div class='infoAdmin'>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>상품상태</div><div class='infoMain_Value'>";
         box +=   "<select id='status'><option value='활성'>활성</option><option value='비활성'>비활성</option></select> 현재 상태 : ["+qqq.status+"] </div></div>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>상품종류</div><div class='infoMain_Value'>";
         box +=   "<select id='sel_type' readonly='readonly'><option value='"+qqq.sel_type+"'>"+qqq.sel_type+"</option></select></div></div>";
         box +=   "<div class='infoMain_Info'><div class='infoMain_Type'>상품명</div><div class='infoMain_Value'>";
         box +=  "<input type='text' placeholder='상품명을 입력해주세요.' id='product' value='"+qqq.product+"' readonly='readonly'></div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>상품설명</div><div class='infoMain_Value'>";
         box +=  "<textarea placeholder='상품내용을 입력해주세요. &#13;&#10;①... &#13;&#10;②...' id='product_info'>"+qqq.product_info+"</textarea></div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>상품기간</div><div class='infoMain_Value'>";
         box +=  "<input type='text' readonly='readonly' value='"+qqq.month+"개월' id='month'> 숫자만 입력부탁드리며 상품기간 다중 등록 시 ',' 로 구분하여 입력해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>이자지급방식</div><div class='infoMain_Value'>";
         box +=  "<input type='text' readonly='readonly' id='interest_type' value='"+qqq.interest_type+"'>상품 종류 선택시 자동으로 입력됩니다.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>세금</div><div class='infoMain_Value'>";
         box +=  "<select id='tax' readonly='readonly'><option value='"+qqq.tax+"'>"+qqq.tax+"</option></select> 과세여부를 선택해주세요. </div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>최소납입금액</div><div class='infoMain_Value'>";
         box +=  "<input type='text' readonly='readonly' value='"+number_Pattern(qqq.min_sum)+"원' id='min_sum'> 최소 납입금액을 입력해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>최대납입금액</div><div class='infoMain_Value'>";
         box +=  "<input type='text' readonly='readonly' value='"+number_Pattern(qqq.max_sum)+"원' id='max_sum'> 최대 납입금액을 입력해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>자동재예치</div><div class='infoMain_Value'>";
         box +=  "<select id='retention' readonly='readonly'><option value='"+qqq.retention+"'>"+qqq.retention+"</option></select> 자동재예치 가능 여부를 선택해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>일부해지</div><div class='infoMain_Value'>";
         box +=  "<select id='partialization' readonly='readonly'><option value='"+qqq.partialization+"'>"+qqq.partialization+"</option></select> 일부해지 가능 여부를 선택해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>기준금리</div><div class='infoMain_Value'>";
         box +=  "<input type='text' id='min_interest' readonly='readonly' value='"+qqq.min_interest+"'> 기준 금리를 입력해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>최대금리</div><div class='infoMain_Value'>";
         box +=  "<input type='text' id='max_interest' readonly='readonly' value='"+qqq.max_interest+"'> 최대 금리를 입력해주세요.</div></div>";
         box +=  "<div class='infoMain_Info'><div class='infoMain_Type'>우대항목</div><div class='infoMain_Value'>";
         box +=  "<select class='preferential' readonly='readonly'>우대구분<option value='"+qqq.preferential+"'>"+qqq.preferential+"</option></select> ";
         box +=  "<select class='prf_content' readonly='readonly'>우대조건<option value='"+qqq.prf_content+"'>"+qqq.prf_content+"</option></select> ";
         box +=  " 우대 추가금리를 입력해주세요. <input type='text' readonly='readonly' value='"+qqq.prf_interest+"' class='prf_interest'> </div></div>";
         box +=  "</div><div class='AdminBot'><div class='AdminButton' onclick='goMenu(this)' data-menu-name='admin/Product/Saving'>수정취소</div>";
         box +=  "<div class='AdminButton' onclick='modReg()' data-menu-name='admin/Saving/Saving'>수정등록</div></div></div>";
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
   
   gogo =  "product/saving/ModifyReg";
   $.ajax({   
      url:gogo,
      type:'post',
      data:{   
    	  	status         :   $('#status').val(),
            product        :   $('#product').val(),
            product_info   :   $('#product_info').val()
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

function number_Pattern(num) {   //3자리마다 ,찍기 
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function goProduct() {   //메뉴 이동용
   var go ='<form name="pag" action="SJAdmin" method="post"></form>';
   $("#mid").append(go);
   document.pag.submit(); 
};

function goMenu(qq) {   //메뉴 이동용
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
<div class='subTitle'>적금상품 관리</div>