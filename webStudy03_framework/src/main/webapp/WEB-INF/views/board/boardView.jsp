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
		<table class="table table-boardered">
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
		</table>
		
		<form id="replyResgistForm" class="form-inline" 
					action="${pageContext.request.contextPath}/reply/replyRegist.do" method="post">
			<div class="form-group col-md-12">
				<input class="form-control mb-3 mr-3" type="text" name="rep_writer" placeholder="작성자" />
				<input class="form-control mb-3 mr-3" type="password" name="rep_pass" placeholder="비밀번호" />
				<input class="form-control mb-3" type="text" name="rep_ip" value="${pageContext.request.remoteAddr}" readonly />
			</div>
			<div class="form-group col-md-12">
				<textarea class="form-control mb-3" rows="5" cols="200" name="rep_content"></textarea>
				<input class="form-control mb-2 mr-3 btn btn-info" type="submit" value="전송" />
				<input class="form-control mb-2 btn-info" type="reset" value="취소" />
			</div>
		</form>
		<br/>
		<table id="replyViewTable" class="table table-boardered">
			<thead>
				<tr>
					<td colspan="4">댓글리스트(비동기)</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<form id="replyForm" action="${pageContext.request.contextPath}/reply/replyView.do">
			<input type="hidden" name="what" value="${board.bo_no}"/>
			<input type="hidden" name="page" />
		</form>
		<div id="pagingArea">
			${pagingVo.pagingHTML_BS}
		</div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/re"></script>
	
	<script type="text/javascript">
		$(function() {
			let listTable = $("#replyViewTable");
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
						listTable.find("tbody").empty();
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
										$("<td>").append(
												$("<input>").attr({
													type : "button",
													value : "수정" 
												}).addClass("btn btn-info modifyReply mr-3"), 
												$("<input>").attr({
													type : "button",
													value : "삭제"
												}).addClass("btn btn-danger removeReply")
										)
									).data(reply)
								);
							});
						} else {
							trTags.push($("<tr>").html($("<td colspan='4'>").text("댓글이 존재하지 않습니다.")));
						}
						
						listTable.find("tbody").html(trTags);
						pagingArea.html(resp.pagingHTML_BS);
					},
					error : function(errResp) {
						console.log(errResp);
					}
				});
				return false;
			}).submit();
			
			function modifyReply() {
				
			}
			
			function removeReply() {
				
			}
			
			listTable.on("click", ".modifyReply", modifyReply)
						.on("click", ".removeReply", removeReply);
			
			
		});
	</script>
</body>
</html>