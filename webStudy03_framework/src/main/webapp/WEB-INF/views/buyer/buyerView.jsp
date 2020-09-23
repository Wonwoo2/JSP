<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
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
			<td>${buyer.buyer_id }</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyer_name }</td>
		</tr>
		<tr>
			<th>거래처분류</th>
			<td>${buyer.buyer_lgu }</td>
		</tr>
		<tr>
			<th>은행</th>
			<td>${buyer.buyer_bank }</td>
		</tr>
		<tr>
			<th>계좌</th>
			<td>${buyer.buyer_bankno }</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyer_bankname }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyer_zip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyer_add1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyer_add2 }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${buyer.buyer_comtel }</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyer_fax }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${buyer.buyer_mail }</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${buyer.buyer_charger }</td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td>${buyer.buyer_telext }</td>
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
						<%
							BuyerVO buyer = (BuyerVO) request.getAttribute("buyer");
							List<ProdVO> prodList = buyer.getProdList();
							if (prodList != null && prodList.size() > 0) {
								for (ProdVO prod : prodList) {
						%>
						<tr>
							<td><%=prod.getProd_name()%></td>
							<td><%=prod.getProd_cost()%></td>
							<td><%=prod.getProd_price()%></td>
							<td><%=prod.getProd_sale()%></td>
							<td><%=prod.getProd_outline()%></td>
						</tr>
						<%
							}
							} else {
						%>
						<tr>
							<td colspan="5">거래 물품이 없음.</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>