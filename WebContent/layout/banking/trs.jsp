<%@page import="jdbc.Account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="subTitle">계좌번호 ${data.to_account_number }로 ${data.sum }원의 이체가 ${data.status }하였습니다.
<br> 이용해 주셔서 감사합니다.</div>