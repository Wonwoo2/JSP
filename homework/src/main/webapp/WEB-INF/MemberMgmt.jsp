<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var inputs = $(".btn");
		var form = $("#memberForm");
		inputs.on("click", function() {
			inputs.attr("disabled", true);
			form
		});
	});
</script>
<style type="text/css">

table {
	border-collapse: collapse;
	border-color: lightgray;
}

th {
	background-color: lightblue;
	height: 50px;
}

td {
	width: 150px;
	height: 80px;
}
</style>
</head>
<%
	List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
%>
<body>
	<input class="btn" type="button" name="insert" value="등록" />
	<input class="btn" type="button" name="update" value="수정" />
	<input class="btn" type="button" name="delete" value="삭제" />
	<input class="btn" type="button" name="select" value="조회" />
	<form id="memberForm">
		<input type="submit" value="전송" />
	</form>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody>
		<%
			for (MemberVO memVo : memberList) {
		%>
		<tr>
			<td class="<%= memVo.getUserid() %>"><%= memVo.getUserid() %></td>
			<td class="<%= memVo.getUserid() %>"><%= memVo.getUsernm() %></td>
		</tr>
		<%
			}
		%>
		</tbody>

	</table>
</body>
</html>