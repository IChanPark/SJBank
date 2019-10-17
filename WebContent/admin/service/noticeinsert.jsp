<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div class='subTitle'>공지사항 작성하기</div>
<br>
<div class='infoAdmin'>
<div class='infoMain_Info'><div class='infoMain_Type'>제목</div><div class='infoMain_Value'>
<input name="title" type='text' placeholder='제목을 입력해주세요.'></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'>내용</div><div class='infoMain_Value'>
<textarea placeholder='공지사항 내용을 입력해주세요. &#13;&#10;①... &#13;&#10;②...' id='product_info' name="content"></textarea></div></div>
<div class='infoMain_Info'><div class='infoMain_Type'></div><div class='infoMain_Value'>
<button data-menu-name="admin/Service/NoticeReg">작성하기</button><button data-menu-name="admin/Service/Notice">목록으로</button></div></div>
</div>