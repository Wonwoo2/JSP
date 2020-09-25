<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prod/prodView.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<!-- 한건의 상품과 해당 상품을 구매한 구매자 목록을 함께 UI 구성. -->
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td>${prod.prod_id}</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prod_name}</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prod.lprod_nm}</td>
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
							<td>
								<c:url value="/buyer/buyerView.do" var="buyerViewURL">
									<c:param name="what" value="${prod.buyer.buyer_id}" />
								</c:url> 
								<a href="${buyerViewURL}">${prod.buyer.buyer_name}</a>
							</td>
							<td>${prod.buyer.buyer_add1}</td>
							<td>${prod.buyer.buyer_charger}</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prod_cost}</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prod_price}</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prod_sale}</td>
		</tr>
		<tr>
			<th>상품개요</th>
			<td>${prod.prod_outline}</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prod_detail}</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td><img
				src="${pageContext.request.contextPath}/prodImages/${prod.prod_img}"></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>${prod.prod_totalstock}</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prod_insdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prod_prperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prod_size}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prod_color}</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prod_delivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prod_unit}</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prod_qtyin}</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>${prod.prod_qtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prod_mileage}</td>
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
						<c:if test="${not empty prod.memberList}">
							<c:set var="memberList" value="${prod.memberList}" />
							<c:forEach var="member" items="${memberList}">
								<tr>
									<c:url value="/member/mbmerView.do" var="memberViewURL">
										<c:param name="who" value="${member.mem_id}"/>
									</c:url>
									<td>${member.mem_id}</td>
									<td>
										<a href="${memberViewURL}">${member.mem_name}</a>
									</td>
									<td>${member.mem_hp}</td>
									<td>${member.mem_add1}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty prod.memberList}">
							<tr>
								<td colspan="5">구매자가 없음.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<!-- /prod/prodList.do 요청 발생(동기, 비동기 모두 처리 --> 
				<input type="button" value="목록으로" onclick="location.href='${pageContext.request.contextPath}/prod/prodList.do';" />
				<input type="button" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/prod/prodUpdate.do?what=${prod.prod_id}';" />
			</td>
		</tr>
	</table>
</body>
</html>