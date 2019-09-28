<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="top">
   <div id="info"><img src="img/logo.png" height="40px" display="block" margin="0px auto"/>
   <c:choose>
      <c:when test="${userID !=null }">
         <a href="#" data-menu-name="admin/LogOut" id="login">로그아웃</a>
      </c:when>
      <c:when test="${userID ==null }">
         <a href="#" data-menu-name="admin/LoginMain" id="login">로그인</a>
      </c:when>
   </c:choose>   
   </div>
</div>
<div class="TitleMenu">
   <ul class="MainUl">
      <li><a href="#" data-menu-name="admin/UserList" >사용자 조회</a>
      </li>
       <li><a href="#" >상품 관리</a>
         <div class="SubMenu">
            <ul class="SubUl">
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Deposit">예금</a></li>
               </div>
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Saving">적금</a></li>
               </div>
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Loan">대출</a></li>
               </div>
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Fund">펀드</a></li>
               </div>
           </ul>
           <ul class="SubUl">
            <div class="LowMenu">
                 <li><a hrefhref="#" data-menu-name"admin/ISA">ISA</a></li>
           </div>
           </ul>
         </div>
      </li>
      
       <li><a href="#" >사용자·멤버십 관리</a>
       <div class="SubMenu">
            <ul class="SubUl">
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Block">거래제한목록</a></li>
               </div>
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Members">멤버십등급</a></li>
               </div>
           </ul>
         </div>
      </li>
      
      <li><a href="#" data-menu-name="admnin/Server" >서버 관리</a>
      </li>
       <li><a href="#" >고객센터</a>
       <div class="SubMenu">
            <ul class="SubUl">
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/Notice">공지사항</a></li>
               </div>
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/FAQ">FAQ</a></li>
               </div>
               <div class="LowMenu">
                  <li><a href="#" data-menu-name="admin/QNA">문의사항</a></li>
               </div>
           </ul>
         </div>
      </li>
   </ul>
</div>