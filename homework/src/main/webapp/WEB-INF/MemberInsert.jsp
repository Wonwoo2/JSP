<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>회원 등록 페이지</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#goMain").on("click", function() {
			location.href = "<%= request.getContextPath() %>/MemberMgmt.do";
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="col-lg-6">
			<div class="jumbotron" style="padding-top: 20px; margin-top: 20px;">
				<form method="post" action="<%= request.getContextPath() %>/MemberService.do">
					<h3 style="text-align: center;">회원 등록</h3>
					
					<div class= form-row>
						<div class="form-group col-lg-9">
							<input type="text" class="form-control" name="userid" placeholder="아이디" />
						</div>
						<div class="form-group" style="margin-left: 20px;">
							<input type="button" class="btn btn-info fom-control" value="중복확인">
						</div>
					</div>
					
					<div class="form-group">
						<input type="text" class="form-control" name="usernm" placeholder="이름" /> 
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="pass" placeholder="비밀번호" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="passCheck" placeholder="비밀번호 확인" />
					</div>
					<div class="form-group">
				    	<input type="text" class="form-control" name="alias" placeholder="닉네임">
					</div>
					<div class="form-group" >
						<input type="text" class="form-control" name="zipcode" placeholder="우편번호">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="addr1" placeholder="주소">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="addr2" placeholder="상세주소">
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-info form-control" value="등록"/>
					</div>
					<div class="form-group">
						<input type="reset" class="btn btn-info form-control" value="취소"/>
					</div>
					<div class="form-group">
						<input id="goMain" type="button" class="btn btn-info form-control" value="돌아가기"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>