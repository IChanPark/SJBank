<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>

.custom-scrollbar {
  height: 1000px;
  overflow-y: scroll;
}

.custom-scrollbar::-webkit-scrollbar {
  width: 8px;
}

.custom-scrollbar::-webkit-scrollbar-track {
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.5);
}

input{
	margin-left: 10px;
	margin-right: 3px;
}

</style>

<script type="text/javascript">

$(document).ready(function() {
	$(".uid").on("click", function() {
		
		console.log($(this).text());
		$.ajax({
			url:"admin/list.jsp",
			type:'get',
			data:{nn : $(this).text()},
			 ////json을 안하면 문자열로 , json 처리하면 object로 묶어서 받음
			success:function(qqq){
				console.log(qqq);
			},
			error:function(qqq){
				/* $("#AccInfo").html(qqq.responseText); */
				console.log("gd");
			}
			
		});
	});
});


</script>

<div class="subTitle">예금·적금 신규</div>

<div>
	<div class="child"><input type = "checkbox" name = "hobby" value="보통"/>보통예금
	<input type = "checkbox" name = "hobby" value="정기"/>정기예금
	<input tyep = "text" style="width: 450px"/>
	<button>검색</button></div>
</div>

<div class="custom-scrollbar">
<table class="AccInfo">
	<tr>
		<td>상품명</td>
		<td>최저 연</td>
		<td>최고 연</td>
		<td>가입 기간</td>
		<td>예금종류</td>
		<td>비과세여부</td>
	</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<tr>
		<td>${dto.product }</td>
		<td>${dto.min_interest }</td>
		<td>${dto.max_interest }</td>
		<td>${dto.month }</td>
		<td>${dto.type }</td>
		<td>${dto.tax }
		<td><input type="hidden" value=""/></td>
	</tr>
</c:forEach>
</table>
</div>