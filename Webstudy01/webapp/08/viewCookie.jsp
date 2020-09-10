<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h4>다른 경로에서 쿠키 확인하기</h4>
<table>
	<thead>
		<tr>
			<th>쿠키 이름</th>
			<th>쿠키 값</th>
		</tr>
	</thead>
	<tbody>
		<%
			Cookie[] cookies =  request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
		%>
			<tr>
				<td><%= cookie.getName() %></td>
				<td><%= URLDecoder.decode(cookie.getValue(), "UTF-8") %></td>
			</tr>
		<%
				}
			} else {
		%>
			<tr>
				<td colspan="2">재전송된 쿠키가 없음</td>
			</tr>
		<%		
			}
		%>
	</tbody>
</table>
</body>
</html>