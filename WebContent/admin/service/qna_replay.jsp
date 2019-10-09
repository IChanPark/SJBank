<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


<input type="hidden" name="seq" value="${dto.seq }">
<input type="hidden" name="page" value="${page }" />


	<table border="" width="100%">
		<tr>
			<td>분류</td><td><input type="text" name="type" value="${dto.type }"></td>
		</tr><tr>
		</tr><tr>
			<td>제목</td><td><input type="text" name="title" value="${dto.title }"></td>
		</tr><tr>
			<td>작성자</td><td><input type="text" name="id" value="${dto.id }"></td>	
		</tr><tr>
			<td>내용</td><td><textarea  name="content" cols="30" rows="10">${dto.content }</textarea></td>
		</tr><tr>
			<td colspan="2" align="center">
				<a href="#" data-menu-name="admin/Service/QnaReg" >답변</a>
				<input type="reset" value="초기화">
				<a href="#" data-menu-name="admin/Service/QNA">뒤로</a>
			</td>
		</tr>
	</table>

