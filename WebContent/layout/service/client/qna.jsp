<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
 var screen="show";
$(document).ready(function() {
	ajax_go();
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

function ajax_go() {
	gogo = "layout/service/qna_select.jsp";
	$.ajax({	
	url:gogo,
	type:'post',
	dataType:'json',
	success:function(qqq){
		$("#ttt").empty();
		var row = '';
		
		$.each(qqq,function(i,e){
		row	+=	"<div class='d1'><ul>";
		row	+=	"<li class='ltt' onclick = 'lttClk(this)'><div class='m'>No."+Number(i+1)+" ["+e.type+"] "+e.title+" 작성자"+e.name+"</div>";
	    row	+=	"<ul class='ull'> <li class='lii'>"+e.content+"</li>";
	    row	+=	"</ul>";
	    row	+=	"</li>";
	    row	+=	"</ul></div>";
	    row +=	"</div>";
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
.ltt {
    width: 100%;
    text-align: center;
  }
.ull{
	
    display: none;
}
.lii {
	
  	display: block;
}
.d1 {padding-top: 10px;  height: 30px; margin: auto; }
.m {background-color: #3C0; float: left; height: 30px;  width: 100%;
display: grid;justify-content: center;align-items: center;}

</style>


<div class="subTitle">문의사항</div>
<div align="right">
<a href="#" data-menu-name="service/qna_insert" id="notice">글쓰기</a></div>
<div class="scrollB"> <!-- 스크롤바 -->
<div id = "ttt"></div>
</div>