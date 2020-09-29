<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/boardList.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<div class="container">
		<form id="searchForm" action="${pageContext.request.contextPath}/board/boardList.do">
			<input type="hidden" name="page" />
			<table id="boardListTable" class="table table-boadered">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:set value="${pagingVo.data}" var="boardList" />
					<c:choose>
						<c:when test="${not empty boardList}">
							<c:forEach items="${boardList}" var="board">
								<c:url value="/board/boardView.do" var="boardViewURL">
									<c:param name="what" value="${board.bo_no}"/>
								</c:url>
								<tr>
									<td>${board.rn}</td>
									<td><a href="${boardViewURL}">${board.bo_title} 글번호(${board.bo_no})</a></td>
									<td>${board.bo_writer}</td>
									<td>${board.bo_date}</td>
									<td>${board.bo_hit}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">해당 게시판은 게시글이 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div class="form-row">
				<div class="form-group mr-4">
					<select class="form-control" name="searchType">
						<option value="all">전체</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="writer">작성자</option>
					</select>
				</div>
				<div class="form-group">
					<input class="form-control mb-2" type="text" name="searchWord" />
				</div>
				<div class="form-group ml-3">
					<input class="form-control mb-2 btn btn-dark" type="button" value="검색" />
				</div>
			</div>
		</form>
		<form action="${pageContext.request.contextPath}/board/boardForm.do">
			<input class="btn btn-info mb-3" type="submit" value="게시글 작성" />
		</form>
		<div id="pagingArea">
			${pagingVo.pagingHTML_BS}
		</div>
		
	</div>
	
	<script type="text/javascript">
		let listTable = $("#boardListTable");
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
					if(list.length > 0){
						$(list).each(function(idx, board){
							trTags.push(
								$("<tr>").append(
									$("<td>").text(board.rn),
									$("<td>").html(
											$("<a>").text(board.bo_title + " 글번호(" + board.bo_no + ")")
													.attr("href", "${boardViewURL}")
													.data("what", board.bo_no)
									),
									$("<td>").text(board.bo_writer),
									$("<td>").text(board.bo_date)
								)
							);
							
						});
					} else {
						trTags.push($("<tr>").html($("<td colspan='4'>").text("해당 게시판은 게시글이 존재하지 않습니다.")));
					}
					listTable.find("tbody").html(trTags);
					pagingArea.html(resp.pagingHTML_BS);
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