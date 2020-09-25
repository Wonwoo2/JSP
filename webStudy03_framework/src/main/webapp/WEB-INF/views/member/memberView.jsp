<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- table 태그를 이용하여, 현재 로그인된 유저의 모든 정보를 출력. -->
<table class="table table-bordered">
	<tr>
		<th>아이디</th>
		<td>${loginMember.mem_id}</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>${loginMember.mem_pass}</td>
	</tr>
	<tr>
		<th>회원명</th>
		<td>${loginMember.mem_name}</td>
	</tr>
	<tr>
		<th>프로필 사진</th>
		<td><img src="data:image/*;base64,${loginMember.mem_imgBase64}" /></td>
	</tr>
	<tr>
		<th>주민번호1</th>
		<td>${loginMember.mem_regno1}</td>
	</tr>
	<tr>
		<th>주민번호2</th>
		<td>${loginMember.mem_regno2}</td>
	</tr>
	<tr>
		<th>생일</th>
		<td>${loginMember.mem_bir}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${loginMember.mem_zip}</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${loginMember.mem_add1}</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${loginMember.mem_add2}</td>
	</tr>
	<tr>
		<th>집전번</th>
		<td>${loginMember.mem_hometel}</td>
	</tr>
	<tr>
		<th>회사전번</th>
		<td>${loginMember.mem_comtel}</td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td>${loginMember.mem_hp}</td>
	</tr>
	<tr>
		<th>직업</th>
		<td>${loginMember.mem_job}</td>
	</tr>
	<tr>
		<th>취미</th>
		<td>${loginMember.mem_like}</td>
	</tr>
	<tr>
		<th>기념일</th>
		<td>${loginMember.mem_memorial}</td>
	</tr>
	<tr>
		<th>기념일자</th>
		<td>
			<input type="date" value="${loginMember.mem_memorialday}" readonly />
		</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${loginMember.mem_mileage}</td>
	</tr>
	<tr>
		<th>탈퇴여부</th>
		<td>${loginMember.mem_delete}</td>
	</tr>
	<tr>
		<th>구매목록</th>
		<td>
			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>상품코드</th>
						<th>상품명</th>
						<th>구매가</th>
						<th>판매가</th>
						<th>마일리지</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="prodList" value="${loginMember.prodList}">
					</c:set>
					<c:choose>
						<c:when test="${not empty prodList}">
							<c:forEach items="${prodList}" var="prod">
								<c:url var="prodViewURL" value="/prod/prodView.do">
									<c:param name="what" value="${prod.prod_id}" />
								</c:url>
								<tr>
									<td>${prod.prod_id}</td>
									<td><a href="${prodViewURL}">${prod.prod_name}</a></td>
									<td>${prod.prod_cost}</td>
									<td>${prod.prod_price}</td>
									<td>${prod.prod_mileage}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">구매 목록이 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</td>
	</tr>
</table>