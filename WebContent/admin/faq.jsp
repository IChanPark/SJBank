<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

$(document).ready(function() {
	$('.uid').on("click", function() {
		//var b = $(this).text();
		$.ajax({
			url:"admin/list.jsp",
			type:'get',
			data:{nn : $(this).data("faq-seq")},				
			 dataType:'json', 
			success:function(qqq){
				var row = $("<tr></tr>");
					row.append($("<td>"+qqq.seq+"</td>"));
					row.append($("<td>"+qqq.id+"</td>"));
					row.append($("<td>"+qqq.title+"</td>"));
					row.append($("<td>"+qqq.content+"</td>"));
					row.append($("<td>"+qqq.type+"</td>"));
					row.append($("<td>"+qqq.status+"</td>"));
					row.append($("<td>"+qqq.register_date+"</td>"));
					$("#test").append(row);
					
					/*  $(a).append(b) */
					
					/* var $tr = $(this).parent(); // 클릭한 버튼이 속한 tr 요소
					$(this).closest('tr').prevAll().length;
					consolog($(this).closest('tr').prevAll().length);
					$tr.next().after($tr); */
					
					/* $("#test").eq($(this).index(this)).append(row); */
					//var $tr = $(this).parent().parent(); // 클릭한 버튼이 속한 tr 요소
					//$tr.next().after($tr);
					
					
			},
			error:function(qqq){
				$("#test").html(qqq.responseText);
			}
			
		});
	});
});



</script>

<div class="subTitle">FAQ</div>
<table class="AccInfo" id="test">
<tr >
	<td>번호</td>
	<td>분류</td>
	<td>제목</td>
	<td>작성자</td>	
</tr>
<c:forEach var="dto" items="${data }" varStatus="no">
	<tr class="uid" data-faq-seq="${dto.seq}" >
		<td>${dto.seq }</td>
		<td>${dto.type }</td>
		<td>${dto.title }</td>
		<td>${dto.id }</td>		
	</tr>
</c:forEach>



</table>