<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Loan.LoanDAO"%>
<%@page import="jdbc.Loan.LoanDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subTitle">대출 상품 등록</div>
<table class="AccInfo">
	
	<tr>
		<tr><td>상품명</td><td><input type="text" name ="product"/></td></tr>
		<tr><td>상품설명</td><td><textarea name="product_info" cols="30" rows="10"></textarea></td>
		<tr><td>최저 금리</td><td><input type="text" name ="min_interest"/></td></tr>
		<tr><td>최고 금리</td><td><input type="text" name ="max_interest"/></td></tr>
		<tr><td>만기개월</td><td><input type="text" name ="month"/></td></tr>
		<tr><td>대출 종류</td><td><input type="text" name ="type"/></td></tr>
		<tr><td>대출한도</td><td><input type="text" name ="loanlimit"/></td></tr>
		<tr><td>우대구분 </td><td><input type="text" name ="preferential"/></td></tr>
		<tr><td>우대조건 내용</td><td><input type="text" name ="prf_content"/></td></tr>
		<tr><td>우대금리</td><td><input type="text" name ="prf_interest"/></td></tr>
		<tr><td>상태</td><td><input type="text" name ="status"/></td></tr>
	
	</tr>
	<tr><td colspan="2"><a href="#" data-menu-name="admin/LoanReg" name="login">등록하기</a></td></tr>
</table>