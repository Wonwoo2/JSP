<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/boardForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
</head>
<body>
	<div class="container">
		<div class="jumbotron mt-2">
			<form action="${pageContext.request.contextPath}/reply/replyRegist.do" method="post" enctype="multpart/form-data">
				<table class="table table-boarderd">
					<thead>
						<tr>
							<th colspan="4" style="text-align: center;">게시판 등록</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<div class="form-group row">
									<div class="col-5 text-right">
										<span>제목</span>
									</div>
									<input class="form-control" type="text" name="bo_title" />
								</div>
							</td>
							<td>
								<div class="form-group row">
									<div class="col-5 text-right">
										<span>작성자</span>
									</div>
									<input class="form-control col-md-6 ml-2" type="text" name="bo_writer" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="form-group">
									<input class="col-md-4" type="file" name="" />
									<input class="col-md-4" type="file" name="" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<textarea class="form-control" name="bo_content" rows="20" cols="20"></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="form-row">
									<div class="form-group">
										<input class="form-control ml-2 btn btn-info" type="submit" value="확인" />
									</div>
									<div class="form-group">
										<input class="form-control ml-3 btn btn-warning" type="reset" value="취소" />
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>
	<script>
		CKEDITOR.replace('bo_content');
	</script>
</body>
</html>