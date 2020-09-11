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
	String fail_id = request.getParameter("mem_id");
	String msg = (String) request.getAttribute("msg");
	
	CookieUtils cookieUtils = new CookieUtils(request);
	String ynValue = cookieUtils.getCookieValue("check");
	
	String check = null;
	if(StringUtils.isNotBlank(ynValue)) {
		if("y".equals(ynValue)) {
			check = "true";
		} else if("n".equals(ynValue)) {
			check = "false";
		}
	}
	
	if(StringUtils.isNotBlank(msg)) {
%>
	<div class="error"><%= msg %></div>
<%
	}
%>

<script type="text/javascript">
	$(function() {
		var check = $("[name='saveId']");
		var yn = $("[name='yn']");
		
		check.on('change', function() {
			ynValue = yn.val();
			if(ynValue == "y") {
				$(this).attr("checked", false);
				yn.val("n");
				
				console.log(yn.val());
			} else if(ynValue == "n") {
				$(this).attr("checked", true);
				yn.val("y");
				
				console.log(yn.val());
			}
		});
	});
	
</script>
<form id="loginForm" action="<%= request.getContextPath() %>/login/loginProcess.do" method="post">
	<ul>
		<li>
			아이디 : <input type="text" name="mem_id" value="<%= Objects.toString(fail_id, "") %>"/>
			<label><input type="checkbox" name="saveId" /> 아이디 기억하기 </label>
			<input type="hidden" name="yn" value="n" />
		</li>
		<li>
			비밀번호 : <input type="password" name="mem_pass" checked="<%= check %>"/>
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>
</body>
</html>