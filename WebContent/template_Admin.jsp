<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SJBank_Admin</title>
<link rel="stylesheet" href="//fonts.googleapis.com/earlyaccess/nanumgothic.css">   
<link rel="stylesheet" type="text/css" href="css/Basic.css" />      <!-- 기본 스타일 설정 -->
<link rel="stylesheet" type="text/css" href="css/Top.css" />      <!-- top 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Account.css" />   <!-- 계좌 테이블 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Scroll_bar.css" />   <!-- 스크롤바 미니 -->
<link rel="stylesheet" type="text/css" href="css/Box.css" />   <!-- 상품 스타일 -->
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>   
<script type="text/javascript" src="js/Menu_Script.js"></script>   <!-- menu 관련 -->
</head>

<form name="paging" action="admin.jsp"/>
<input type="hidden" name="hid_t" />
<!-- post데이터 전송용 form과 input -->

<body>
   <jsp:include page="admin/inc/top.jsp" />
   <div id="mid"><div><jsp:include page="${mainUrl }.jsp" /></div></div>
   <div id="bot"><div><jsp:include page="admin/inc/bottom.jsp" /></div></div>
</body>
</html>