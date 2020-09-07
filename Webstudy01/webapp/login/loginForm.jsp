<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<style type="text/css">
.error {
	color : red;
}

</style>
</head>
<body>
<%
	String fail_id = (String) request.getAttribute("mem_id");
	String msg = (String) request.getAttribute("msg");
	
	if(StringUtils.isNotBlank(msg)) {
%>
	<div class="error"><%= msg %></div>
<%
	}
%>
<form action="<%= request.getContextPath() %>/login/loginProcess.do" method="post">
	<ul>
		<li>
			아이디 : <input type="text" name="mem_id" value="<%= Objects.toString(fail_id, "") %>"/>
		</li>
		<li>
			비밀번호 : <input type="password" name="mem_pass" />
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>
</body>
</html>