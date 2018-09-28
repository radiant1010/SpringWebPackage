<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
<title>Home</title>
<%@ include file="layout/header.jsp" %>
</head>
<body>
	<h1>Hello world!</h1>
	<!-- 로그인이 된다면 숨겨지게 바꾸기. -->
	<input type="button" class="btn btn-primary" value="회원등록" onclick="location.href='${path}/member/write.do'">
    <c:if test="${msg == 'success'}">
    <h2>${sessionScope.userName}(${sessionScope.userId})님 환영합니다.</h2>
    </c:if>
    <!-- 로그인 해야 보이게 -->
    <a href="${path}/member/view.do?userId=${sessionScope.userId}"><input type="button" class="btn btn-primary" value="회원상세정보"></a>
    
    <a href="${path}/board/boardList.do"><input type="button" class="btn btn-primary" value="게시판"></a>
    
</body>
</html>
