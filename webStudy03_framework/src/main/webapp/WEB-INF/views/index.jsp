<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/index.jsp</title>
</head>
<body>
	<h4>웰컴 페이지</h4>
	<c:choose>
		<c:when test="${not empty loginMember}">	
			<form name="logoutForm" action="${pageContext.request.contextPath}/login/logout.do" method="post">
			</form>
			현재 로그인 유저 : <a href="${pageContext.request.contextPath}/mypage.do">${sessionScope.loginMember.mem_name}</a> 
			<a href="#" onclick="document.logoutForm.submit()">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/login/loginForm.jsp">로그인</a>
			<a href="${pageContext.request.contextPath}/registMember.do">회원가입</a>
		</c:otherwise>
	</c:choose>
</body>
</html>