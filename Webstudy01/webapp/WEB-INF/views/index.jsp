<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
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
<%
	CookieUtils cookieUtils = new CookieUtils(request);

	String mem_id =  cookieUtils.getCookieValue("mem_id");
	if(StringUtils.isNotBlank(mem_id)) {
%>
	<form name="logoutForm" action="<%= request.getContextPath() %>/login/logout.do" method="post"></form>
	현재 로그인 유저 : <%= mem_id %> <a href="#" onclick="document.logoutForm.submit()">로그아웃</a>
<%
	} else {
%>
	<a href="<%= request.getContextPath() %>/login/loginForm.jsp">로그인 하기</a>
<%
	}
%>

</body>
</html>