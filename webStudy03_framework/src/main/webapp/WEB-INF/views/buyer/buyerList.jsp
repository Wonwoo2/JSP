<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp" />
<title>buyer/buyerList.jsp</title>
</head>
<body>
	<div class="container">
		<form id="searchForm" action="${pageContext.request.contextPath}/buyer/buyerList.do">
			<input type="hidden" name="page" />
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>거래처 코드</th>
						<th>거래처 명</th>
						<th>상품 분류</th>
						<th>거래처 은행</th>
						<th>계좌번호</th>
						<th>예금주명</th>
						<th>우편번호</th>
						<th>주소</th>
						<th>상세주소</th>
						<th>전화</th>
						<th>팩스</th>
						<th>메일</th>
						<th>담당자</th>
						<th>내선번호</th>
					</tr>
				</thead>
				<tbody>
					<c:set value="${pagingVo.data}" var="buyerList" />
					<c:choose>
						<c:when test="${not empty buyerList}">
							<c:forEach items="${buyerList}" var="buyer">
								<c:url value="/buyer/buyerView.do" var="buyerViewURL">
									<c:param name="what" value="${buyer.buyer_id}" />
								</c:url>
								<tr>
									<td>${buyer.buyer_id}</td>
									<td><a href="${buyerViewURL}">${buyer.buyer_name}</a></td>
									<td>${buyer.lprod_nm}</td>
									<td>${buyer.buyer_bank}</td>
									<td>${buyer.buyer_bankno}</td>
									<td>${buyer.buyer_bankname}</td>
									<td>${buyer.buyer_zip}</td>
									<td>${buyer.buyer_add1}</td>
									<td>${buyer.buyer_add2}</td>
									<td>${buyer.buyer_comtel}</td>
									<td>${buyer.buyer_fax}</td>
									<td>${buyer.buyer_mail}</td>
									<td>${buyer.buyer_charger}</td>
									<td>${buyer.buyer_telext}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="14">거래처가 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div id="pagingArea">
				${pagingVo.pagingHTML_BS}
			</div>
		</form>
	</div>
	<script type="text/javascript">
		let searchForm = $("#searchForm");
		let pagingArea = $("#pagingArea");
		let pagingA = pagingArea.on('click', "a" ,function(){
			let page = $(this).data("page");
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
			searchForm.find("[name='page']").val(1);
			return false;
		});
	</script>
</body>
</html>