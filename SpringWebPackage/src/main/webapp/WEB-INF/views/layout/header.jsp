<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 코어 태그 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl 시간 출력 태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- context 경로 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- 부트스트랩  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<div style="text-align: center;">
	<c:choose>
		<c:when test="${sessionScope.userId == null}">
			<a href="${path}/member/login.do">로그인</a>
		</c:when>
		<c:otherwise>
        ${sessionScope.userName}님이 로그인중입니다.
        <a href="${path}/member/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>

<hr>