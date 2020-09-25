<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/deleteForm.jsp</title>
</head>
<jsp:include page="/includee/preScript.jsp" />
<body>
		<div class="jumbotron">
			<div class="container">
				<form class="form-control" action="${pageContext.request.contextPath}/memberDelete.do" method="post">
					<h4 class="text-center">회원 탈퇴</h4>
					<div class="form-group row">
						<label class="col-md-4 p-auto">아이디</label>
						<input class="form-control col-md-6" type="text" name="mem_id" value="${deleteMember.mem_id}" maxlength="15" readonly required />
					</div>
					<div class="form-group row">
						<label class="col-md-4">비밀번호</label>
						<input class="form-control col-md-6" type="password" name="mem_pass" value="" maxlength="15" required />
					</div>
					<div class="form-group row">
						<input class="form-control col-md-4" style="width: 40%; margin: auto;" type="submit" value ="삭제하기" />
						<input class="form-control col-md-4" style="width: 40%; margin: auto;" type="reset" value ="취소" />
					</div>
				</form>
			</div>
		</div>
</body>
</html>