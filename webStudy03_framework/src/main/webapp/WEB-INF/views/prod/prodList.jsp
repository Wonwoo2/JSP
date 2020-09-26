<%-- <%@page import="kr.or.ddit.vo.BuyerVO"%> --%>
<%-- <%@page import="java.util.Map"%> --%>
<%-- <%@page import="java.util.List"%> --%>
<%-- <%@page import="kr.or.ddit.vo.PagingVO"%> --%>
<%-- <%@page import="kr.or.ddit.vo.ProdVO"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<%--
		PagingVO<ProdVO> pagingVo = (PagingVO) request.getAttribute("pagingVo");
	--%>
	<form id="searchForm" action="${pageContext.request.contextPath}/prod/prodList.do" class="form-inline">
		<input type="hidden" name="page" /> 
		<select class="form-control" name="prod_lgu">
			<option value>상품분류</option>
			<c:forEach items="${lprodList}" var="lprod">
				<option value="${lprod.lprod_gu}">${lprod["lprod_nm"]}</option>
			</c:forEach>
		</select> 
		<select class="form-control" name="prod_buyer">
			<option value>거래처</option>
			<c:forEach items="${buyerList}" var="buyer">
				<option value="${buyer.buyer_id}">${buyer.buyer_name}</option>
			</c:forEach>
		</select>
		<input type="text" name="prod_name" />
		<input type="hidden" name="buyer_lgu" />
		<input type="submit" class="btn btn-primary" value="검색" />
	</form>
	<script type="text/javascript">
		let buyerSelect = $("[name='prod_buyer']");
		$("[name='prod_lgu']").on("change", function() {
			let buyer_lgu = $(this).val();
			$("[name='buyer_lgu']").val(buyer_lgu);
			
			$.ajax({
				url : "${pageContext.request.contextPath}/prod/buyerList.do",
				data : {
					buyer_lgu : buyer_lgu
				},
				method : "get",
				dataType : "json",
				success : function(resp) {
					buyerSelect.empty();
					buyerSelect.append($("<option>").text("상품분류"));
					
					let buyerList = resp.buyerList;
					$(buyerList).each(function(idx, buyer) {
						let buyerItem = $("<option>");
						buyerItem.text(buyer.buyer_name);
						buyerItem.val(buyer.buyer_id);
						buyerSelect.append(buyerItem);
					});
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			})
		});
	</script>
	<table id="prodTable" class="table table-bordered">
		<thead>
			<tr>
				<th>상품명</th>
				<th>상품분류</th>
				<th>거래처</th>
				<th>구매가</th>
				<th>판매가</th>
				<th>세일가</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="prodList" value="${pagingVo.data}">
			</c:set>
			<c:choose>
				<c:when test="${not empty prodList}">
					<c:forEach items="${prodList}" var="prod">
						<tr>
							<c:url value="/prod/prodView.do" var="prodViewURL">
								<c:param name="what" value="${prod.prod_id}" />
							</c:url>
							<td><a href="${prodViewURL}">${prod.prod_name}</a></td>
							<td>${prod.lprod_nm}</td>
							<td>${prod.buyer.buyer_name}</td>
							<td>${prod.prod_cost}</td>
							<td>${prod.prod_price}</td>
							<td>${prod.prod_sale}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">검색 조건에 맞는 상품 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" id="pagingArea">
					${pagingVo.pagingHTML_BS}
				</td>
			</tr>
		</tfoot>
	</table>

	<div class="modal fade" id="prodViewModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<span id="whatArea"></span>의 상세정보
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		let listTable = $("#prodTable");
		let pagingArea = $("#pagingArea");
		let pagingA = pagingArea.on('click', "a" ,function(){
			let page = $(this).data("page");
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
			searchForm.find("[name='page']").val(1);
			return false;
		});
		let searchForm = $("#searchForm").on("submit", function(event){
			event.preventDefault();
			let url = this.action?this.action:location.href;
			let method = this.method?this.method:"get";
			let data = $(this).serialize(); // query string 
			$.ajax({
				url : url,
				method : method,
				data : data,
				dataType : "json",
				success : function(resp) {
					listTable.find("tbody").empty();
					pagingArea.empty();
					let list = resp.data;
					let trTags = [];
					if(list.length>0){
						$(list).each(function(idx, prod){
							trTags.push(
								$("<tr>").append(
									$("<td>").html(
											$("<a>").text(prod.prod_name)
													.attr("href", "#")
													.data("what", prod.prod_id)
									),
									$("<td>").text(prod.lprod_nm),
									$("<td>").text(prod.buyer.buyer_name),
									$("<td>").text(prod.prod_cost),
									$("<td>").text(prod.prod_price),
									$("<td>").text(prod.prod_sale)
								)
							);
							
						});
					}else{
						trTags.push($("<tr>").html($("<td colspan='6'>").text("조건에 맞는 상품이 없음.")));
					}
					listTable.find("tbody").html(trTags);
					pagingArea.html( resp.pagingHTML_BS );
				},
				error : function(errResp) {
				}
			});
			return false;
		});
// 	$("#prodTable>tbody").on("click","a", function(){
// 		let what = $(this).data("what"); 
<%-- <%-- 		location.href="${pageContext.request.contextPath}/Prod/ProdView.do?what="+what; --%>
// 		$.ajax({
<%-- 			url : "${pageContext.request.contextPath}/prod/prodView.do", --%>
// 			method : "get",
// 			data : {
// 				what:what
// 			},
// 			dataType : "html",
// 			success : function(resp) {
// 				$("#prodViewModal").find("#whatArea").text(what);
// 				$("#prodViewModal").find(".modal-body").html(resp);
// 				$("#prodViewModal").modal("show");
// 			},
// 			error : function(errResp) {
// 				console.log(errResp);
// 			}
// 		});
// 		return false;
// 	});
	</script>
</body>
</html>
