<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
    
    
    <table>
    	<tr>
    		<td>신청채널</td>
    		<td>미지정계좌 이체한도</td>
    	</tr>
    	<tr>
    		<td>인터넷</td>
    		<td><input type="text" name="trsLimit"/></td>
    	</tr>
    </table>
    
    <table>
    <tr>
    <td>선택</td>
    <td>No</td>
    <td>이용채널</td>
    <td>은행명</td>
    <td>계좌번호</td>
    <td>입금계좌명</td>
    <td>이체일자</td>
    </tr>
    <c:forEach var="dto" items="${data }" varStatus="no">
    	<tr>
	    	<td></td>
	    	<td></td>
	    	<td></td>
	    	<td></td>
	    	<td>${dto.account_number }</td>
	    	<td></td>
	    	<td>${dto.type }</td>
    	</tr>
     </c:forEach>
    
    
    
    </table>
    
    