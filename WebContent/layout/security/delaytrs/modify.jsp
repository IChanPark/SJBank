<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="subTitle">지연이체 관리</div>

<table>
	<tr>
		<td>출금 계좌번호</td>
		<td></td>
	</tr>
	<tr>
		<td>계좌 비밀번호</td>
		<td></td>
	</tr>
</table>

<br><br>

<div>
<input type="radio" name="delayTime" value="3" />3
<input type="radio" name="delayTime" value="4" />4
<input type="radio" name="delayTime" value="5" />5 
</div>


<br><br>

<div>연락처</div>
<div><input type="radio" name="call"/>휴대폰<input type="radio" name="call"/>직접입력</div>


<button data-menu-name="security/delaytrs/Delete">해지하기</button>
<button data-menu-name="security/delaytrs/Renew">갱신하기</button>

