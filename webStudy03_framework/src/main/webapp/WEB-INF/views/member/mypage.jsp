<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp" />
<title>member/mypage.jsp</title>
</head>
<body>
<!-- table 태그를 이용하여, 현재 로그인 된 유저의 모든 정보를 출력 -->
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="2">
						내 정보
				</th>
				</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty loginMember}">
					<tbody>
						<tr>
							<td>아이디</td>
							<td>${loginMember.mem_id}</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td>
								<input type="password" value="${loginMember.mem_pass}" readonly />
							</td>
						</tr>
						<tr>
							<td>프로필 사진</td>
							<td>
								<img width="150px;" height="80px;" src="data:image/*;base64,${loginMember.mem_imgBase64}" />
							</td>
						</tr>
						<tr>
							<td>주민번호</td>
							<td>${loginMember.mem_regno1}-${loginMember.mem_regno2}</td>
						</tr>
						<tr>
							<td>생일</td>
							<td>${loginMember.mem_bir}</td>
						</tr>
						<tr>
							<td>집전화</td>
							<td>${loginMember.mem_hometel}</td>
						</tr>
						<tr>
							<td>휴대폰</td>
							<td>${loginMember.mem_hp}</td>
						</tr>
						<tr>
							<td>회사전화</td>
							<td>${loginMember.mem_comtel}</td>
						</tr>
						<tr>
							<td>직업</td>
							<td>${loginMember.mem_job}</td>
						</tr>
						<tr>
							<td>우편번호</td>
							<td>${loginMember.mem_zip}</td>
						</tr>
						<tr>
							<td>주소</td>
							<td>${loginMember.mem_add1}</td>
						</tr>
						<tr>
							<td>상세주소</td>
							<td>${loginMember.mem_add2}</td>
						</tr>
						<tr>
							<td>취미</td>
							<td>${loginMember.mem_like}</td>
						</tr>
						<tr>
							<td>기념일명</td>
							<td>${loginMember.mem_memorial}</td>
						</tr>
						<tr>
							<td>기념일자</td>
							<td>
								<input type="date" value="${loginMember.mem_memorialday}" readonly />
							</td>
						</tr>
						<tr>
							<td>마일리지</td>
							<td>${loginMember.mem_mileage}</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="button" value="수정하기" data-command="update" class="btn btn-primary"
								onclick="location.href='${pageContext.request.contextPath}/memberUpdate.do';"/>
								<input type="button" value="탈퇴하기" data-command="delete" class="btn btn-danger"
								onclick="location.href='${pageContext.request.contextPath}/memberDelete.do';"/>
							</td>
						</tr>
					</tbody>
			</c:when>
			<c:otherwise>
				<tbody>
					<tr>
						<th colspan="2">존재하지 않는 회원입니다.</th>
					</tr>
				</tbody>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>