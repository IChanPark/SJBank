<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
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
</div>
<a href="#" data-menu-name="service/Join">회원가입</a>
<a href="#" data-menu-name="service/SearchID">아이디 찾기</a>
<a href="#" data-menu-name="service/SearchPW">비밀번호 재설정</a>