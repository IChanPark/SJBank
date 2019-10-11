<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
$(document).ready(function(){
	$('#search').click(function(){
		
		if($('input[name="start"]').val() > $("input[name=end]").val())
		{
			alert("검색일이 잘못 되었습니다.");
			return;
		}
		$("#list").html("");
		var accountNum = "<c:out value="${data.account_number }"/>"
		
			 $.ajax({	//라디오 버튼 
				url:"layout/banking/detailList.jsp",
				type:'post',
				data:{
					acc : accountNum,
				 	start : $('input[name="start"]').val(),
					end : $("input[name=end]").val()
				},
				dataType:'json',
				success:function(qqq){
					var title = $("<tr><td>No</td><td>거래처</td><td>거래대상계좌번호</td><td>받는이</td><td>거래액</td><td>수수료</td><td>메모</td><td>보낸메모</td><td>거래일</td><td>거래종류</td><td>거래상태</td></tr>");
					$("#list").append(title);
					$.each(qqq,function(i,e){
						var row =$("<tr><td>"+(i+1)+"</td>");
						row.append(	$("<td>"+e.target+"</td>"));
						row.append(	$("<td>"+e.to_account_number+"</td>"));
						row.append($("<td>"+e.received+"</td>"));
						row.append(	$("<td>"+e.sum+"</td>"));
						row.append(	$("<td>"+e.fee+"</td>"));
						row.append($("<td>"+e.memo+"</td>"));
						row.append($("<td>"+e.to_memo+"</td>"));
						row.append($("<td>"+e.register_date+"</td>"));
						row.append($("<td>"+e.feetype+"</td>"));
						row.append($("<td>"+e.status+"</td></tr>"));
						$("#list").append(row);
					});
				},
				error:function(qqq){
					console.log("오류오류");
				}
			});
		
	});
});

</script>




<div class="subTitle">상세</div>
<table class="AccInfo">
	<tr>
		<td>계좌명</td>
		<td colspan="3">${data.alias }</td>
	</tr>
	<tr>
		<td>고객아이디</td>
		<td>${data.id }</td>
		<td>계좌번호</td>
		<td name="acc" value="${data.account_number }">${data.account_number }</td>
	</tr>
	<tr>
		<td>신규일</td>
		<td colspan="3" align="left" float="left">${data.register_date }</td>

	</tr>
	<tr>
		<td>잔액</td>
		<td><fmt:formatNumber value="${data.sum }" pattern="#,###원" /></td>
		<td>출금가능금액</td>
		<td><fmt:formatNumber value="${data.sum }" pattern="#,###원" /></td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td><input type="date" name = "start"/>~ <input type="date" name="end"/> </td>
	</tr>
	<tr>
		<td id="search">조회하기</td>
	</tr>
</table>
<div class="subTitle">내역</div>
<table class="AccInfo" id="list">
	<tr>
		<td>No</td>
		<td>거래처</td>
		<td>거래대상계좌번호</td>
		<td>받는이</td>
		<td>거래액</td>
		<td>수수료</td>
		<td>메모</td>
		<td>보낸메모</td>
		<td>거래일</td>
		<td>거래종류</td>
		<td>거래상태</td>
	</tr>
	
	<c:forEach var="lo" items="${IO }" varStatus="no" begin="1" step="1">
		<tr >
			<td>${no.count }</td>
			<td>${lo.target }</td>
			<td>${lo.to_account_number }</td>
			<td>${lo.received }</td>
			<td><fmt:formatNumber value="${lo.sum }" pattern="#,###원" /></td>
			<td>${lo.fee }</td>
			<td>${lo.memo }</td>
			<td>${lo.to_memo }</td>
			<td>${lo.register_date }</td>
			<td>${lo.feetype }</td>
			<td>${lo.status }</td>
			<c:set var="count" value="${count=count+1 }" />
		</tr>
	                                                                                                                                                                                                                  </c:forEach>
	<c:if test="${empty count }">
		<tr>
			<td colspan="11" align="center">거래내역이 없습니다.</td>
		</tr>
	</c:if>
</table>