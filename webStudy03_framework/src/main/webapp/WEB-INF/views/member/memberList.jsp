<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberList.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table id="memberTable" class="table table-bordered">
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>주소</th>
				<th>마일리지</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="memberList" value="${paginVO.data}"></c:set>
			<c:choose>
				<c:when test="${not empty memberList}">
					<c:forEach items="${memberList}" var="member">
						<tr>
							<td>${member.mem_id}</td>
							<td><a href="#" data-who="${member.mem_id}">${member.mem_name}</a></td>
							<td>${member.mem_hp}</td>
							<td>${member.mem_add1}</td>
							<td>${member.mem_mileage}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">검색 조건에 맞는 회원이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" id="pagingArea">
					${pagingVO.pagingHTML_BS}
				</td>
			</tr>
		</tfoot>
	</table>
	<c:url value="/member/memberList.do" var="memberListURL" />
	<form id="searchForm" action="${memberListURL}" class="form-inline">
		<input type="hidden" name="page" /> 
		<select name='searchType' class="form-control">
			<option value="all">전체</option>
			<option value="name">이름</option>
			<option value="address">지역</option>
		</select> 
		<input type="text" class="form-control" name="searchWord" /> 
		<input type="submit" class="btn btn-primary" value="검색" />
	</form>
	<div class="modal fade" id="memberViewModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<span id="whoArea"></span>의 상세정보
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
		let listTable = $("#memberTable");
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
						$(list).each(function(idx, member){
							trTags.push(
								$("<tr>").append(
									$("<td>").text(member.mem_id),
									$("<td>").html(
											$("<a>").text(member.mem_name)
													.attr("href", "#")
													.data("who", member.mem_id)
									),
									$("<td>").text(member.mem_hp),
									$("<td>").text(member.mem_add1),
									$("<td>").text(member.mem_mileage)
								)
							);
							
						});
					}else{
						trTags.push($("<tr>").html($("<td colspan='6'>").text("조건에 맞는 회원이 없음.")));
					}
					listTable.find("tbody").html(trTags);
					pagingArea.html( resp.pagingHTML_BS );
				},
				error : function(errResp) {
				}
			});
			return false;
		});
		$("#memberTable>tbody").on("click","a", function(){
			let who = $(this).data("who"); 
			console.log(who);
			$.ajax({
				url : "${pageContext.request.contextPath}/member/memberView.do",
				method : "get",
				data : {
					who:who
				},
				dataType : "html",
				success : function(resp) {
					$("#memberViewModal").find("#whoArea").text(who);
					$("#memberViewModal").find(".modal-body").html(resp);
					$("#memberViewModal").modal("show");
				},
				error : function(errResp) {
					console.log(errResp);
				}
			});
			return false;
		});
	</script>
</body>
</html>