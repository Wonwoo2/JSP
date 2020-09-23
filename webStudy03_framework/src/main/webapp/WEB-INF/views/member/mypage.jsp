<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/mypage.jsp</title>
<style type="text/css">

td {
	width: 200px;
	height: 30px;
}

.title {
	width : 100px;
}
</style>
</head>
<body>
<!-- table 태그를 이용하여, 현재 로그인 된 유저의 모든 정보를 출력 -->
<%
	MemberVO authMember = (MemberVO) request.getAttribute("member");
	
	if (authMember != null) {
		
%>
	<table border="1" style="border-collapse: collapse; border: solid lightgray;">
		<thead>
			<tr style="background-color: lightgray">
				<th colspan="2">내 정보</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="title">아이디</td>
				<td><%= authMember.getMem_id() %></td>
			</tr>
			<tr>
				<td class="title">비밀번호</td>
				<td><%= authMember.getMem_pass() %></td>
			</tr>
			<tr>
				<td class="title">이름</td>
				<td><%= authMember.getMem_name() %></td>
			</tr>
			<tr>
				<td class="title">주민번호</td>
				<td><%= authMember.getMem_regno1() %>-<%= authMember.getMem_regno2() %></td>
			</tr>
			<tr>
				<td class="title">생일</td>
				<td><%= authMember.getMem_bir() %></td>
			</tr>
			<tr>
				<td class="title">집전화</td>
				<td><%= authMember.getMem_hometel() %></td>
			</tr>
			<tr>
				<td class="title">휴대폰</td>
				<td><%= authMember.getMem_hp() %></td>
			</tr>
			<tr>
				<td class="title">직장전화</td>
				<td><%= authMember.getMem_comtel() %></td>
			</tr>
			<tr>
				<td class="title">직업</td>
				<td><%= authMember.getMem_job() %></td>
			</tr>
			<tr>
				<td class="title">우편번호</td>
				<td><%= authMember.getMem_zip() %></td>
			</tr>
			<tr>
				<td class="title">주소</td>
				<td><%= authMember.getMem_add1() %></td>
			</tr>
			<tr>
				<td class="title">상세주소</td>
				<td><%= authMember.getMem_add2() %></td>
			</tr>
			<tr>
				<td>취미</td>
				<td><%= authMember.getMem_like() %></td>
			</tr>
			<tr>
				<td class="title">기념일명</td>
				<td><%= authMember.getMem_memorial() %></td>
			</tr>
			<tr>
				<td class="title">기념일 날짜</td>
				<td><%= authMember.getMem_memorialday() %></td>
			</tr>
			<tr>
				<td class="title">마일리지</td>
				<td><%= authMember.getMem_mileage() %></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정하기" data-command="update" onclick="location.href='<%= request.getContextPath() %>/MemberUpdate.do';"/>
					<input type="button" value="탈퇴하기" data-command="delete" onclick="location.href='<%= request.getContextPath() %>/MemberDelete.do';"/>
				</td>
			</tr>
		</tbody>
	</table>
<%
	} else {
		out.print("존재하지 않는 정보");
	}
%>
</body>
</html>