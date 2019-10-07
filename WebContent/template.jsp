<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "icon" href = "SJBank.ico"> 	<!-- 웹 아이콘 -->
<meta charset="UTF-8">
<title>SJ인터넷은행</title>
<link rel="stylesheet" href="//fonts.googleapis.com/earlyaccess/nanumgothic.css">   
<link rel="stylesheet" type="text/css" href="css/Basic.css" />      <!-- 기본 스타일 설정 -->
<link rel="stylesheet" type="text/css" href="css/Top.css" />      <!-- top 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Account.css" />   <!-- 계좌 테이블 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Scroll_bar.css" />   <!-- 스크롤바 미니 -->
<link rel="stylesheet" type="text/css" href="css/Loader.css" />   <!-- 로딩 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Product_List.css" />   <!-- 상품 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Product_Detail.css" />   <!-- 상품상세 스타일 -->
<link rel="stylesheet" type="text/css" href="css/Product_Join.css" />   <!-- 상품가입 스타일 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> <!-- 데이트피커 -->
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>   
<script type="text/javascript" src="js/Menu_Move.js"></script>   <!-- menu 이동 -->
<script type="text/javascript" src="js/Top_Fix.js"></script>   <!-- top 고정 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script><!-- 우편번호api -->
<script type="text/javascript" src="js/zipCode.js"></script>   <!-- 우편번호 -->
<script type="text/javascript" src="js/Loader.js"></script>  
<script type="text/javascript" src="js/jquery-ui.min.js"></script>	  <!-- 데이트 피커 -->
<script type="text/javascript" src="js/Date_Picker.js"></script>	  <!-- 데이트 피커 -->

</head>
	<form name="paging" action="index.jsp" onsubmit="return false;" method="post"/>
	<input type="hidden" name="hid_t" />
<body>
	<jsp:include page="layout/inc/top.jsp" />
	<div id="mid"><div><jsp:include page="${mainUrl }.jsp" /></div></div>
	<div id="bot"><div><jsp:include page="layout/inc/bottom.jsp" /></div></div>
</body>
</html>