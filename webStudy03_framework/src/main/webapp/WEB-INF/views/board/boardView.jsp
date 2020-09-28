<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/boardView.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<div class="container">
		<table id="boardViewTable" class="table table-boardered">
			<thead>
				<tr>
					<td>제목 : ${board.bo_title}(${board.bo_no})</td>
					<td>작성자 : ${board.bo_writer}</td>
					<td>IP : ${board.bo_ip}</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td>작성일 : ${board.bo_date}</td>
					<td>조회수 : ${board.bo_hit}</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="3">
						${board.bo_content}
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="2">댓글리스트</th>
				</tr>
				<c:choose>
					<c:when test="${not empty pagingVo.data}">
						<c:set value="${pagingVo.data}" var="replyList" />
						<c:forEach items="${replyList}" var="reply">
							<tr>
								<td rowspan="2">${reply.rep_content}</td>
								<td>${reply.rep_writer}(${reply.rep_ip})</td>
							</tr>
							<tr>
								<td>${reply.rep_date}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">댓글이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tfoot>
		</table>
		<div id="pagingArea">
			${pagingVo.pagingHTML_BS}
		</div>
	</div>
	<form id="replyForm" action="${pageContext.request.contextPath}/reply/replyView.do">
		<input type="hidden" name="what" value="${board.bo_no}"/>
		<input type="hidden" name="page" />
	</form>
	
	<script type="text/javascript">
		$(function() {
			let listTable = $("#boardViewTable");
			let pagingArea = $("#pagingArea");
			let pagingA = pagingArea.on('click', "a" ,function(){
				let page = $(this).data("page");
				replyForm.find("[name='page']").val(page);
				replyForm.submit();
				replyForm.find("[name='page']").val(1);
				return false;
			});
			let replyForm = $("#replyForm").on("submit", function(event){
				event.preventDefault();
				let url = this.action ? this.action : location.href;
				let method = this.method ? this.method : "get";
				let data = $(this).serialize(); // query string 
				$.ajax({
					url : url,
					method : method,
					data : data,
					dataType : "json",
					success : function(resp) {
						listTable.find("tfoot").empty();
						pagingArea.empty();
						
						let list = resp.data;
						let trTags = [];
						if(list.length > 0){
							$(list).each(function(idx, reply){
								trTags.push(
									$("<tr>").append(
										$("<td>").text(reply.rep_content),
										$("<td>").text(reply.rep_writer + "(" + reply.rep_ip + ")"),
										$("<td>").text(reply.rep_date),
										$("<td>").html(
												$("<input>").attr({
													type : "button",
													value : "수정" 
												}).addClass("btn btn-info modify"), 
												$("<input>").attr({
													type : "button",
													value : "삭제"
												}).addClass("btn btn-danger delete")
										)
									).data(reply)
								);
								
							});
						}else{
							trTags.push($("<tr>").html($("<td colspan='4'>").text("댓글이 존재하지 않습니다.")));
						}
						listTable.find("tfoot").html(trTags);
						pagingArea.html(resp.pagingHTML_BS);
					},
					error : function(errResp) {
						console.log(errResp);
					}
				});
				return false;
			}).submit();
		});
	</script>
</body>
</html>