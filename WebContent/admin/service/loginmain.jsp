<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.flexbox-centering {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 600px;
  width: 1000px;
}
</style>
<div class="flexbox-centering">
<div class="child">
	<table class="Info">
		<tr>
			<td>아이디</td>
			<td>: <input type="text" name="id" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>: <input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><a href="#"
				data-menu-name="admin/Service/Login">로그인</a></td>
		</tr>
	</table>
</div>
</div>