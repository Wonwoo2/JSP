<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>buyer/buyerView.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>거래처코드</th>
			<td>${buyer.buyer_id}</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyer_name}</td>
		</tr>
		<tr>
			<th>거래처분류</th>
			<td>${buyer.lprod_nm}</td>
		</tr>
		<tr>
			<th>은행</th>
			<td>${buyer.buyer_bank}</td>
		</tr>
		<tr>
			<th>계좌</th>
			<td>${buyer.buyer_bankno}</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyer_bankname}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyer_zip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyer_add1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyer_add2}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${buyer.buyer_comtel}</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyer_fax}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${buyer.buyer_mail}</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${buyer.buyer_charger}</td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td>${buyer.buyer_telext}</td>
		</tr>
		<tr>
			<th>삭제여부</th>
			<td>${buyer.buyer_delete}</td>
		</tr>
		<tr>
			<th>거래물품</th>
			<td>
				<table class="table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>상품명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>세일가</th>
							<th>상품개요</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="prodList" value="${buyer.prodList}">
						</c:set>
						<c:choose>
							<c:when test="${not empty prodList}">
								<c:forEach items="${prodList}" var="prod">
									<tr>
										<td>${prod.prod_name}</td>
										<td>${prod.prod_cost}</td>
										<td>${prod.prod_price}</td>
										<td>${prod.prod_sale}</td>
										<td>${prod.prod_outline}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">거래 물품이 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<c:url value="/buyer/buyerRegist.do" var="registBuyerURL"/>
			<c:url value="/buyer/buyerModify.do" var="modifyBuyerURL">
				<c:param name="what" value="${buyer.buyer_id}" />
			</c:url>
			<c:url value="/buyer/buyerDelete.do" var="deleteBuyerURL">
				<c:param name="what" value="${buyer.buyer_id}" />
			</c:url>
			<td colspan="2">
				<input class="btn btn-info" type="button" value="등록" 
					onclick="location.href='${registBuyerURL}';" />
				<input class="btn btn-info" type="button" value="수정" 
					onclick="location.href='${modifyBuyerURL}';" />
				<input class="btn btn-info" type="button" value="삭제" 
					onclick="location.href='${deleteBuyerURL}';" />
			</td>
		</tr>
	</table>
</body>
</html>