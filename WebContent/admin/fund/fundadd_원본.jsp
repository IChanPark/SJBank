<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subTitle">펀드 상품 등록</div>
<table class="AccInfo">
	
	<tr>
		<tr><td>상품명</td><td><input type="text" name ="product"/></td></tr>
		<tr><td>상품설명</td><td><textarea name="product_info" cols="30" rows="10"></textarea></td>
		<tr><td>초기기준가</td><td><input type="text" name ="price"/></td></tr>
		<tr><td>수정기준가</td><td><input type="text" name ="price_modify"/></td></tr>
		<tr><td>유형</td><td><input type="text" name ="type"/></td></tr>
		<tr><td>과세여부</td><td><input type="text" name ="tax"/></td></tr>
		<tr><td>국내/해외 </td><td><input type="text" name ="area"/></td></tr>
		<tr><td>상품속성 </td><td><input type="text" name ="property"/></td></tr>
		<tr><td>선취수수료</td><td><input type="text" name ="first_fee"/></td></tr>
		<tr><td>년보수</td><td><input type="text" name ="fee"/></td></tr>
		<tr><td>운용사</td><td><input type="text" name ="management"/></td></tr>
		<tr><td>섹터 </td><td><input type="text" name ="sector"/></td></tr>
		<tr><td>상태 </td><td><input type="text" name ="status"/></td></tr>

		
	</tr>
	<tr><td colspan="2"><a href="#" data-menu-name="admin/Fund/FundReg" name="login">등록하기</a></td></tr>
</table>