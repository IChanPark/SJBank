<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(document).ready(function() {
	$(".data_v").on("click", function() {
		var gogo;
		
		if ($(this).val() == "예금") {
			gogo = "layout/product/deposit_list.jsp";
		} else {
			gogo = "layout/product/saving_list.jsp";
		}
		
		$.ajax({	//라디오 버튼 
			url:gogo,
			type:'post',
			data:{ddd : $(this).val(),
				  ccc : $('.sertext').val()},
			dataType:'json',
			success:function(qqq){
				$("#ttt").empty();
				$.each(qqq,function(i,e){
					var row = $("<div class='box' data-product-name='"+e.product+"' data-product-type='"+e.type+"'>");
					row.append($("<div class='l'>최저 "+e.min_interest+"<br>최고 "+e.max_interest+"</div>"));
					row.append($("<div class='m'>["+e.type+"] "+e.product+"</div>"));
					row.append($("<div class='r'>가입<br>상세 버튼</div>"));

					$("#ttt").append(row);
				});
			},
			error:function(qqq){
				console.log("오류오류");
			}
		});
	});
	
	$(".dp").on("click", function() {
		
		$.ajax({	//초기 데이터 갱신용
			url:"layout/product/deposit_detail.jsp",
			type:'post',
			data:{eee : $(this).data("product-name")},
			/* dataType:'json', */
			success:function(qqq){
				/* $("#ttt").empty(); */
				/* $.each(qqq,function(i,e){
					var row = $("<tr></tr>");
					row.append($("<td>"+e.product+"</td>"));
					row.append($("<td>"+e.min_interest+"</td>"));
					row.append($("<td>"+e.max_interest+"</td>"));
					row.append($("<td>"+e.month+"</td>"));
					row.append($("<td>"+e.type+"</td>"));
					row.append($("<td>"+e.tax+"</td>"));
					$("#ttt").append(row);
				}); */
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
	<div class="child" >
	<input class="data_v" type = "radio" name = "group"  value="예금" checked="checked"/>예금
	<input class="data_v" type = "radio" name = "group" value="적금"/>적금
	<input class="sertext" type = "text" style="width: 450px"/>
	<a href="#" class="ser" >검색하기</a>
</div>

<div class="scrollB"> <!-- 스크롤바 -->
<div id = "ttt">
<c:forEach var="dto" items="${data_dp}">
	<div class="box" data-product-name="${dto.product }" data-product-type="${dto.type }">
		<div class="l">
		최저 ${dto.min_interest }%<br>
		최고 ${dto.max_interest }%
		</div>
	
		<div class="m">[${dto.type }] ${dto.product }</div>
		<div class="r">가입<br>
		상세 버튼</div>
	</div>
</c:forEach>
</div>
</div>