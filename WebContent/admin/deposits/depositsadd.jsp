<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.User.UserDAO"%>
<%@page import="jdbc.User.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subTitle">예금 상품 등록</div>
<table class="AccInfo">
	
	<tr>
		<tr><td>상품명</td><td><input type="text" name ="product"/></td></tr>
		<tr><td>최저 연이자</td><td><input type="text" name ="min_interest"/></td></tr>
		<tr><td>만기개월</td><td><input type="text" name ="month"/></td></tr>
		<tr><td>정기/비정기</td><td><input type="text" name ="type"/></td></tr>
		<tr><td>이자지급방식</td><td><input type="text" name ="jnterest_type"/></td></tr>
		<tr><td>과세여부 </td><td><input type="text" name ="tax"/></td></tr>
		<tr><td>우대구분 </td><td><input type="text" name ="preferential"/></td></tr>
		<tr><td>우대조건 내용</td><td><input type="text" name ="prf_content"/></td></tr>
		<tr><td>우대이자율</td><td><input type="text" name ="prf_interest"/></td></tr>
		<tr><td>최소금액</td><td><input type="text" name ="min_sum"/></td></tr>
		<tr><td>월납입 최대금액 </td><td><input type="text" name ="max_sum"/></td></tr>
		<tr><td>일부해지가능여부 </td><td><input type="text" name ="partialization"/></td></tr>
		<tr><td>재예치가능여부 </td><td><input type="text" name ="retention"/></td></tr>
	</tr>
	<tr><td colspan="2"><a href="#" data-menu-name="admin/deposits/DepositReg" name="login">등록하기</a></td></tr>
</table>