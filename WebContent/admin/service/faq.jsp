<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
 var screen="show";
$(document).ready(function() {
	$(".ltt").on("click",function() {
	     
		$(this).find(".ull").stop().fadeToggle(500);
	    if(screen=="show"){ 
			$(".d1").height('60');
			screen="hide"
	    }
	    else
	    {
	    	$(".d1").height('30');
	    	screen="show";
	    }
	});
	    	
});

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


<div class="subTitle">FAQ</div>
<div align="right"><input class="sertext" type = "text" style="width: 450px"/>
<a href="#" class="ser" >검색하기</a></div>
<div class="scrollB"> <!-- 스크롤바 -->
	
<div id = "ttt">
<c:forEach var="dto" items="${data }" varStatus="no">
<tr>

	<div class="d1"  data-faq-seq="${dto.seq}">
    <ul>
      <li class="ltt"><div class="m">No.${dto.seq } [${dto.type }] ${dto.title } 작성자: ${dto.id }</div>
        <ul class="ull">
          <li class="lii">${dto.content }</li>
        </ul>
      </li>
  
    </ul> 
  </div>
 
  
</c:forEach>
</div>
</div>