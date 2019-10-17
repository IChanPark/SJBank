<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
 var screen="show";
 /*
$(document).ready(function() {
	 $(".ltt").on("click",function() {
	     var index = $(".ltt").index(this);
	     var hh = $(".d1").eq(index).height();
	  
		$(this).find(".ull").stop().fadeToggle(500);
	    if(hh ==30){ 
		//	$(".d1").height('60');
		$(".d1").eq(index).height('60');
			screen="hide"
	    }
	    else
	    {
	    	$(".d1").eq(index).height('30');
	    	screen="show";
	    }
	}); 
	    	
});
*/

$(document).ready(function(){
	ajax_go();
	
	$("#ser").on("click", function() {
		ajax_go();
	});
});

function ajax_go() {
	gogo = "admin/service/faq_select.jsp";
	$.ajax({	
	url:gogo,
	type:'post',
	data:{	 type 	: $('#sertext').val()},
	dataType:'json',
	success:function(qqq){
		$("#ttt").empty();
		var row = '';
		
		$.each(qqq,function(i,e){
		row+="<div class='d1'  data-faq-seq='"+Number(i+1)+"'><ul>"
	    row+="<li class='ltt' onclick = 'lttClk(this)'><div class='m'>No."+Number(i+1)+" ["+e.type+"] "+e.title+" </div>"
	    row+="<ul class='ull'> <li class='lii'>"+e.content+"</li>"
	    row+="</ul>"
	    row+="</li>"
	    row+="</ul></div>";
		});
	$("#ttt").append(row);
	isRun = false
	},error:function(qqq){
		$("#ttt").empty();
		var row = $("<div class='box'></div>");
		row.append($("<div class='m'>검색결과가 존재하지 않습니다.</div>"));
		isRun = false;
	}	
		
	});	
};

function lttClk(me){
	var index = $(".ltt").index(me);
	
	//alert(index);
	$(me).find(".ull").stop().fadeToggle(500);
	
	var hh = $(".d1").eq(index).height();
    if(hh ==30){ 
	//	$(".d1").height('60');
	$(".d1").eq(index).height('60');
		screen="hide"
    }
    else
    {
    	$(".d1").eq(index).height('30');
    	screen="show";
    }
}

</script>

<style media="screen">
.ltt { width: 100%; text-align: center; }
.ull{display: none;}
.lii {display: block;}
.d1 {padding-top: 10px;  height: 30px; margin: auto; }
.m {background-color: #c6c6c6; float: left; height: 30px;  width: 100%;
display: grid;justify-content: center;align-items: center;}
a{ text-decoration:none;}
</style>

<div class="subTitle">FAQ</div>

<div class='search_Box' >
<input class='search_Word' type = 'text' id='sertext' value=''/>
<div class='search_Button' id='ser' >검색</div>
<div class='add_Button'><a href="#" data-menu-name="admin/service/faq_insert" id="notice">작성하기</a></div>
</div>

<div class="scrollB"> <!-- 스크롤바 -->
<div id = "ttt">
<c:forEach var="dto" items="${data }" varStatus="no">
<tr>

	<div class="d1"  data-faq-seq="${dto.seq}">
	<ul>
      <li class="ltt" onclick = "lttClk(this)"><div class="m">No.${dto.seq } [${dto.type }] ${dto.title } </div>
        <ul class="ull">
          <li class="lii">${dto.content }</linoticeinsert.jsp>
        </ul>
      </li>
  
    </ul> 
  </div>
 
  
</c:forEach>
</div>
</div>