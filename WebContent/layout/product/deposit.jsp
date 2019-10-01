<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(document).ready(function() {
	$(".ser").on("click", function() {
		
		$.ajax({
			url:"layout/product/deposit_list.jsp",
			type:'get',
			data:{ddd : $('.sertext').val()},
			dataType:'json',
			success:function(qqq){
				$("#ttt").empty();
				$.each(qqq,function(i,e){
					var row = $("<tr></tr>");
					row.append($("<td>"+e.product+"</td>"));
					row.append($("<td>"+e.min_interest+"</td>"));
					row.append($("<td>"+e.max_interest+"</td>"));
					row.append($("<td>"+e.month+"</td>"));
					row.append($("<td>"+e.type+"</td>"));
					row.append($("<td>"+e.tax+"</td>"));
					$("#ttt").append(row);
				});
			},
			error:function(qqq){
				console.log("오류오류");
				console.log(qqq);
			}
		});
	});
});
</script>

<div class="subTitle">예금·적금 신규</div>

<div>
	<div class="child"><input type = "checkbox" name = "hobby" value="보통"/>보통예금
	<input type = "checkbox" name = "hobby" value="정기"/>정기예금
	<input class="sertext" type = "text" style="width: 450px"/>
	<a href="#" class="ser" >검색하기</a>
</div>

<div class="scrollB"> <!-- 스크롤바 -->

<table class="AccInfo" id="ttt">
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