<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
 var screen="show";
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

</script>

<style media="screen">
.ltt {
    width: 100%;r
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
<div class="scrollB"> <!-- 스크롤바 -->
<div id = "ttt">
<c:forEach var="dto" items="${data }" varStatus="no">
<tr>

	<div class="d1"  data-faq-seq="${dto.seq}">
    <ul>
      <li class="ltt"><div class="m">No.${dto.seq } ${dto.title }  등록일: ${dto.register_date } </div>
        <ul class="ull">
          <li class="lii">${dto.content }</li>
          <li><a href="#" data-menu-name="admin/service/qna_replay" id=${dto.seq }>답변</a></li> 
        </ul>
      </li>  
    </ul> 
  </div>
 
  
</c:forEach>
</div>
</div>