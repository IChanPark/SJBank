<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<div class='subTitle'>FAQ 작성하기</div>
<br>
<div class='infoAdmin'>
<div class='infoMain_Info'><div class='infoMain_Type'>분류</div><div class='infoMain_Value'>
<select name="type" ><option value='상품'>상품</option><option value='회원'>회원</option><option value='기타'>기타</option></select></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>제목</div><div class='infoMain_Value'>
<input name="title" type='text' placeholder='제목을 입력해주세요.'></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>내용</div><div class='infoMain_Value'>
<textarea placeholder='FAQ내용을 입력해주세요. &#13;&#10;①... &#13;&#10;②...' id='product_info' name="content"></textarea></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button data-menu-name="admin/Service/FaqReg">작성하기</button><button data-menu-name="admin/Service/FAQ">목록으로</button></div></div>

</div>