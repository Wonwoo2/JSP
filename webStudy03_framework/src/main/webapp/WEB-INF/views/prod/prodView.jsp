<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prod/prodView.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<!-- 한건의 상품과 해당 상품을 구매한 구매자 목록을 함께 UI 구성. -->
	<%
		ProdVO prod = (ProdVO) request.getAttribute("prod");
	%>
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td><%=prod.getProd_id()%></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><%=prod.getProd_name()%></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td><%= prod.getLprod_nm() %></td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>거래처명</th>
							<th>거래처소재지</th>
							<th>거래처담당자명</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="<%= request.getContextPath() %>/buyer/buyerView.do?what=${prod.buyer.buyer_id}">${prod.buyer.buyer_name}</a></td>
							<td>${prod.buyer.buyer_add1}</td>
							<td>${prod.buyer.buyer_charger}</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><%=prod.getProd_cost()%></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><%=prod.getProd_price()%></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><%=prod.getProd_sale()%></td>
		</tr>
		<tr>
			<th>상품개요</th>
			<td><%=prod.getProd_outline()%></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><%=prod.getProd_detail()%></td>
		</tr>
		<tr>
			<th>상품이미지경로</th>
			<td><%=prod.getProd_img()%></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><%=prod.getProd_totalstock()%></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><%=prod.getProd_insdate()%></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><%=prod.getProd_properstock()%></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><%=prod.getProd_size()%></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><%=prod.getProd_color()%></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><%=prod.getProd_delivery()%></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><%=prod.getProd_unit()%></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><%=prod.getProd_qtyin()%></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><%=prod.getProd_qtysale()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=prod.getProd_mileage()%></td>
		</tr>
		<tr>
			<th>구매자목록</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>회원아이디</th>
							<th>회원명</th>
							<th>휴대폰</th>
							<th>주소</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<MemberVO> memberList = prod.getMemberList();
							if (memberList != null && memberList.size() > 0) {
								for (MemberVO member : memberList) {
						%>
						<tr>
							<td><%=member.getMem_id()%></td>
							<td><a
								href="<%=request.getContextPath()%>/member/memberView.do?who=<%=member.getMem_id()%>"><%=member.getMem_name()%></a></td>
							<td><%=member.getMem_hp()%></td>
							<td><%=member.getMem_add1()%></td>
						</tr>
						<%
							}
							} else {
						%>
						<tr>
							<td colspan="5">구매자가 없음.</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<!-- /prod/prodList.do 요청 발생(동기, 비동기 모두 처리 -->
				<input type="button" value="목록으로" onclick="location.href='<%= request.getContextPath() %>/prod/prodList.do';" />
			</td>
		</tr>
	</table>
</body>
</html>