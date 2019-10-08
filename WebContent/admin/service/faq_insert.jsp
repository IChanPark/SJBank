<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

	<table border="" width="100%">
		<tr>
			<td>분류</td><td><input type="text" name="type"></td>
		</tr>
		<tr>
			<td>제목</td><td><input type="text" name="title"></td>
		</tr><tr>
			<td>작성자</td><td><input type="text" name="id"></td>				
		</tr><tr>
			<td>내용</td><td><textarea  name="content" cols="30" rows="10"></textarea></td>
		</tr><tr>
			<td colspan="2" align="center">
				<a href="#" data-menu-name="admin/Service/FaqReg" >작성</a>
				<a href="#" data-menu-name="admin/Service/FAQ" >목록으로</a>
			</td>
		</tr>
	</table>