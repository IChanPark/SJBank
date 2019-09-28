<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>회원가입</h2>
    <form action="joinReg">	
	<table border="">
		<tr>
			<td>ID</td>							
			<td><input type="text" name="id" maxlength="50"/><input type="button" value="중복확인" />
			</td>			
		</tr>
		<tr>	
			<td>비밀번호</td>
			<td><input type="text" name="pw" /></td><input type="button" value="중복확인" />			
		</tr>		
		<tr>	
			<td>간편 비밀 번호</td>
			<td><input type="text" name="epw" /></td>
		</tr>
		<tr>
		<td>이름(한글만가능)</td>							
			<td><input type="text" name="id" maxlength="50"/>		
		</tr>
		<tr>	
			<td>전화번호</td>
			<td><input type="text" name="tel" /></td>
		</tr>
		<tr>
		<td>성별</td>
		<td>
			<input type="radio" name="gender" value="남" checked>남
            <input type="radio" name="gender" value="여" checked>여
		</td>
		</tr>
		<tr>	
			<td>이메일</td>
			<td>
				<input type="text" name="email1" maxlength="50"/>@
				<select name="email2">
					<option>naver.com</option>
					<option>daum.net</option>
					<option>gmail.com</option>
					<option>nate.com</option>
					<option>yahoo.com</option>
			</select>
			</td>	
		</tr>		
		<tr>	
			<td>직업</td>
			<td><input type="text" name="jumin" />
					<select name="position" >
					<option>학생</option>
					<option>군인</option>
					<option>회사원</option>
					<option>주부</option>
					<option>교수</option>
					<option>백수</option>
					</select>
			</td>
		</tr>		
		<tr>	
			<td>주소</td>
			<td><input type="text" name="address" /></td>
		</tr>		
		<tr>	
			<td>우편번호</td>
			<td><input type="text" name="zipcode" /></td>
		</tr>	
		<tr>
		
			<td colspan="2" align="right">			
				<input type="submit"  value="가입" />
				<input type="button" value="취소"/>
				
				
	
</form>