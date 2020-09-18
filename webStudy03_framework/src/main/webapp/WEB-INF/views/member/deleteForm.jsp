<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/MemberDelete.jsp</title>
</head>
<jsp:include page="/includee/preScript.jsp" />
<body>
	<form class="form-control" action="<%= request.getContextPath() %>/MemberDelete.do" method="post">
		<div class="container">
			<div class="jumbotron">
				<h4 class="dispaly4 text-center">회원 탈퇴</h4>
				
					<div class="form-group row">
						<label class="col-md-4 p-auto">아이디</label>
						<input class="form-control col-md-6" type="text" name="mem_id" value="${ member.mem_id }" maxlength="15" readonly required />
					</div>
					<div class="form-group row">
						<label class="col-md-4">비밀번호</label>
						<input class="form-control col-md-6" type="password" name="mem_pass" value="" maxlength="15" required />
					</div>
					<div class="form-group row">
						<input class="form-control col-md-4" style="width: 40%; margin: auto;" type="submit" value ="삭제하기" />
						<input class="form-control col-md-4" style="width: 40%; margin: auto;" type="reset" value ="취소" />
					</div>
				
			</div>
		</div>
	</form>
</body>
</html>