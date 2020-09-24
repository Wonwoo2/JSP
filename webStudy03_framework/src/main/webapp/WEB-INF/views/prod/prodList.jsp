<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
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
	<%
		PagingVO<ProdVO> pagingVo = (PagingVO) request.getAttribute("pagingVo");
	%>
	<form id="searchForm" action="<%=request.getContextPath()%>/prod/prodList.do" class="form-inline">
		<input type="hidden" name="page" /> 
		<select class="form-control" name="prod_lgu">
			<option value>상품분류</option>
			<%
				List<Map<String, Object>> lprodList = (List) request.getAttribute("lprodList");
				for (Map<String, Object> lprod : lprodList) {
			%>
			<option value="<%=lprod.get("lprod_gu")%>"><%=lprod.get("lprod_nm")%></option>
			<%
				}
			%>
		</select> 
		<select class="form-control" name="prod_buyer">
			<option value>거래처</option>
			<%
				List<BuyerVO> buyerList = (List) request.getAttribute("buyerList");
				for (BuyerVO buyer : buyerList) {
			%>
			<option value="<%=buyer.getBuyer_id()%>"><%=buyer.getBuyer_name()%></option>
			<%
				}
			%>
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
				url : "<%= request.getContextPath()%>/prod/buyerList.do",
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
			<%
				List<ProdVO> prodList = pagingVo.getData();
				if (prodList != null && prodList.size() > 0) {
					for (ProdVO prod : prodList) {
			%>
			<tr>
				<td><a href="<%= request.getContextPath() %>/prod/prodView.do?what=<%= prod.getProd_id() %>"><%=prod.getProd_name()%></a></td>
				<td><%=prod.getLprod_nm()%></td>
				<td><%=prod.getBuyer().getBuyer_name()%></td>
				<td><%=prod.getProd_cost()%></td>
				<td><%=prod.getProd_price()%></td>
				<td><%=prod.getProd_sale()%></td>
			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td colspan="6">검색 조건에 맞는 상품 없음.</td>
			</tr>
			<%
				}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" id="pagingArea"><%= pagingVo.getPagingHTML_BS() %>
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
<%-- <%-- 		location.href="<%=request.getContextPath() %>/Prod/ProdView.do?what="+what; --%>
// 		$.ajax({
<%-- 			url : "<%=request.getContextPath()%>/prod/prodView.do", --%>
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
