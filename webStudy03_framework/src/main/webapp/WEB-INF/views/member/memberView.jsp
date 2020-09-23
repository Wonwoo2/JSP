<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- table 태그를 이용하여, 현재 로그인된 유저의 모든 정보를 출력. -->
<%
	MemberVO member = (MemberVO) request.getAttribute("member");
%>
<table class="table table-bordered">
	<tr>
		<th>아이디</th>
		<td><%=member.getMem_id()%></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><%=member.getMem_pass()%></td>
	</tr>
	<tr>
		<th>회원명</th>
		<td><%=member.getMem_name()%></td>
	</tr>
	<tr>
		<th>주민번호1</th>
		<td><%=member.getMem_regno1()%></td>
	</tr>
	<tr>
		<th>주민번호2</th>
		<td><%=member.getMem_regno2()%></td>
	</tr>
	<tr>
		<th>생일</th>
		<td><%=member.getMem_bir()%></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td><%=member.getMem_zip()%></td>
	</tr>
	<tr>
		<th>주소1</th>
		<td><%=member.getMem_add1()%></td>
	</tr>
	<tr>
		<th>주소2</th>
		<td><%=member.getMem_add2()%></td>
	</tr>
	<tr>
		<th>집전번</th>
		<td><%=member.getMem_hometel()%></td>
	</tr>
	<tr>
		<th>회사전번</th>
		<td><%=member.getMem_comtel()%></td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td><%=member.getMem_hp()%></td>
	</tr>
	<tr>
		<th>직업</th>
		<td><%=member.getMem_job()%></td>
	</tr>
	<tr>
		<th>취미</th>
		<td><%=member.getMem_like()%></td>
	</tr>
	<tr>
		<th>기념일</th>
		<td><%=member.getMem_memorial()%></td>
	</tr>
	<tr>
		<th>기념일자</th>
		<td><%=member.getMem_memorialday()%></td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td><%=member.getMem_mileage()%></td>
	</tr>
	<tr>
		<th>탈퇴여부</th>
		<td><%=member.getMem_delete()%></td>
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
					<%
						List<ProdVO> prodList = member.getProdList();
						if (prodList != null && prodList.size() > 0) {
							for (ProdVO prod : prodList) {
					%>
					<tr>
						<td><%=prod.getProd_id()%></td>
						<td><a
							href="<%=request.getContextPath()%>/prod/prodView.do?what=<%=prod.getProd_id()%>"><%=prod.getProd_name()%></a></td>
						<td><%=prod.getProd_cost()%></td>
						<td><%=prod.getProd_price()%></td>
						<td><%=prod.getProd_mileage()%></td>
					</tr>
					<%
						}
						} else {
					%>
					<tr>
						<td colspan="5">구매 목록이 없음.</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</td>
	</tr>
</table>