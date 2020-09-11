<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<style type="text/css">
.error {
	color : red;
}

</style>
</head>
<body>
<%
	String msg = (String) session.getAttribute("msg");
	
	String saveId = (new CookieUtils(request)).getCookieValue("idCookie");
	
	
	if(StringUtils.isNotBlank(msg)) {
%>
	<div class="error"><%= msg %></div>
<%
		session.removeAttribute("msg");		// flash attribute
	}
%>
<form id="loginForm" action="<%= request.getContextPath() %>/login/loginProcess.do" method="post">
	<ul>
		<li>
			아이디 : <input type="text" name="mem_id" value="<%= Objects.toString(saveId, "") %>"/>
			<label><input type="checkbox" name="saveId" value="save" <%= saveId != null ? "checked" : "" %>/> 아이디 기억하기 </label>
			<input type="hidden" name="yn" value="n" />
		</li>
		<li>
			비밀번호 : <input type="password" name="mem_pass" />
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>
</body>
</html>