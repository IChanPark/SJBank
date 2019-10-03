<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.flexbox-centering {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 1000px;
}
</style>

<div class="flexbox-centering">
<div class="child">
	<table>
	<tr>
		<td>아이디 </td>
		<td> : <input type="text" name="id" /></td>
	</tr>
	<tr>
		<td>비밀번호 </td>
		<td> : <input type="text" name="pw" /></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
		<a href="#" data-menu-name="service/Login">로그인</a>
		</td>
	</tr>
	</table>
	<br>
	<a href="#" data-menu-name="service/Join">회원가입</a>
	<a href="#" data-menu-name="service/SearchID">아이디 찾기</a>
	<a href="#" data-menu-name="service/SearchPW">비밀번호 재설정</a>
</div>
</div>
		