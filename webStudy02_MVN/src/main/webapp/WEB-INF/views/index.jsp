<%@page import="kr.or.ddit.vo.MemberVO"%>
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

	MemberVO authMember = (MemberVO) session.getAttribute("authMember");

	if (authMember != null) {
%>
	<form name="logoutForm" action="<%= request.getContextPath() %>/login/logout.do" method="post"></form>
	현재 로그인 유저 : <a href="<%= request.getContextPath() %>/mypage.do"><%= authMember.getMem_name() %></a> <a href="#" onclick="document.logoutForm.submit()">로그아웃</a>
<%
	} else {
%>
	<a href="<%= request.getContextPath() %>/login/loginForm.jsp">로그인</a>
	<a href="<%= request.getContextPath() %>/registMember.do">회원가입</a>
<%
	}
%>

</body>
</html>