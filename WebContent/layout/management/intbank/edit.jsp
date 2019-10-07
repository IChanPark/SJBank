<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.mymenulist {border:1px solid white; height: 50px; margin: auto; margin-bottom: 15px;}

</style>

<script type="text/javascript">
var mylistv="";
var mylisnum=0;

 function ajaxGo(goUrl, getData){
		$.ajax({
			url:"layout/management/intbank/"+goUrl+".jsp",
			type:'get',
			data:getData,
			dataType:'json',  ////json을 안하면 문자열로 , json 처리하면 object로 묶어서 받음
			success:function(qqq){
		
				if(goUrl=="menulist")
					listGo(qqq);
				
				if(goUrl=="menuinput")
					menuinput(qqq);
				
			},
			error:function(qqq){
				alert("에러인가");
				
			}
			
		});
	}
		
	function listGo(qqq){
		
		 	
		$.each(qqq,function(i,e){
			if(e.type=="banking"&&e.depth=="2"){
				var row = "";
				row += "&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"<span class='addmenu'>"+ e.kor_name+"</span>" ;
			
			$("#banking").append(row);
			}
			 if(e.type=="product"&&e.depth=="1"){
				var row = "";
				row += "&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"<span class='addmenu'>"+ e.kor_name+"상품안내"+"</span>" ;
			
			$("#product").append(row);
			}
			 if(e.type=="security"&&e.depth=="2"){
				var row = "";
				row += "&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"<span class='addmenu'>"+ e.kor_name+"</span>" ;
			
			$("#security").append(row);
			}
			 if(e.type=="management"&&e.depth=="2"){
				var row = "";
				row += "&nbsp;"+"&nbsp;"+"&nbsp;"+"<span class='addmenu'>"+ e.kor_name+"</span>" ;
			
			$("#management").append(row);
			}
			 if(e.type=="service"&&e.depth=="2"){
				var row = "";
				row += "&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"&nbsp;"+"<span class='addmenu'>"+ e.kor_name+"</span>" ;
			
			$("#service").append(row);
			}
		 	if(e.mymenu1!=null){
		 		$(".mymenulist").html("");
				var row ="";
				row += "<span>"+"&nbsp;"+ e.mymenu1+"&nbsp;"+"</span>";
				mylistv += e.mymenu1+",";
				mylisnum++;
				$(".mymenulist").append(row); 
				
			} 
		 	if(e.mymenu2!=null){
				var row ="";
				row += "<span>"+"&nbsp;"+ e.mymenu2+"&nbsp;"+"</span>";
				mylistv += e.mymenu2+",";
				mylisnum++;
				$(".mymenulist").append(row); 
				
			} 
		 	if(e.mymenu3!=null){
				var row ="";
				row += "<span>"+"&nbsp;"+ e.mymenu3+"&nbsp;"+"</span>";
				mylistv += e.mymenu3+",";
				mylisnum++;
				$(".mymenulist").append(row); 
				
			} 
		 	if(e.mymenu4!=null){
				var row ="";
				row += "<span>"+"&nbsp;"+ e.mymenu4+"&nbsp;"+"</span>";
				mylistv += e.mymenu4+",";
				mylisnum++;
				$(".mymenulist").append(row); 
				
			} 
		 	if(e.mymenu5!=null){
				var row ="";
				row += "<span>"+"&nbsp;"+ e.mymenu5+"&nbsp;"+"</span>";
				mylistv += e.mymenu5+",";
				mylisnum++;
				$(".mymenulist").append(row); 
				
			} 
		 	
			
		
		});
		
		$(".addmenu").on("click", function(){	
			if(mylisnum<5){
			var row ='';
				row += $(this).text();
				mylistv +=$(this).text()+",";
				mylisnum++;
			$(".mymenulist").append(row);}else{
				alert("메뉴리스트 5개초과");
			}
		});
		
		$(".initialization").on("click", function(){	
			$(".mymenulist").html("");
			mylistv="";
			mylisnum=0;
		});
		
		$(".submit").on("click", function(){	
			ajaxGo("menuinput",{list:mylistv});
		});
	}

	function menuinput(qqq){
		$("#banking").empty();
		$("#product").empty();
		$("#security").empty();
		$("#management").empty();
		$("#service").empty();
		ajaxGo("menulist",{});
		
	}
	
$(document).ready(function(){
	
	ajaxGo("menulist",{});
	
	
});
  

</script>


<br><br><br>
<div>조회 ·이체</div>
<div>───────────────────────────────────────────────────────────</div>
<div id="banking" class="add"></div>
<br><br><br>
<div>금융상품</div>
<div>───────────────────────────────────────────────────────────</div>
<div id="product" class="add"></div>
<br><br><br>
<div>보안관리</div>
<div>───────────────────────────────────────────────────────────</div>
<div id="security" class="add"></div>
<br><br><br>
<div>사용자관리</div>
<div>───────────────────────────────────────────────────────────</div>
<div id="management" class="add"></div>
<br><br><br>
<div>고객센터</div>
<div>───────────────────────────────────────────────────────────</div>
<div id="service" class="add"></div>

<br><br><br><br>
<div>My메뉴 리스트</div>
<div class="mymenulist">
</div>
<span class="initialization">선택 초기화 &nbsp;&nbsp;&nbsp;</span><span class=submit >&nbsp;&nbsp;등록  &nbsp;&nbsp;     </span>

