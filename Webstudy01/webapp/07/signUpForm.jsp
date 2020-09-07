<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<style>
</style>
</head>
<body>
<form action="signUp.jsp" method="post">
		<div class="form-group col-md-3">
			<label for="inputID">아이디</label> 
			<input type="text" class="form-control" name="mem_id">
		</div>
		<div class="form-group col-md-3">
			<label for="inputPass">비밀번호</label> 
			<input type="password" class="form-control" name="mem_pass1">
		</div>
		<div class="form-group col-md-3">
			<label for="inputPass">비밀번호 확인</label> 
			<input type="password" class="form-control" name="mem_pass2">
		</div>
		<div class="form-group col-md-3">
			<label for="inputName">이름</label> 
			<input type="text" class="form-control" name="mem_name">
		</div>
		<div class="form-group col-md-3 col-mb-3">
			<label for="inputReg">주민등록번호</label>
			<input type="text" class="form-control regInput" name="mem_regno1">
			<input type="text" class="form-control regInput" name="mem_regno2">
		</div>
		<div class="form-group col-md-6">
			<label for="inpuntEmail">이메일</label> 
			<input type="email" class="form-control" name="mem_mail">
		</div>
		<div class="form-group col-md-6">
			<label for="inputAddress">주소</label> 
			<input type="text" class="form-control" placeholder="주소" name="mem_add1">
		</div>
		<div class="form-group col-md-6">
			<label for="inputAddress2">상세주소</label> 
			<input type="text" class="form-control" placeholder="상세주소" name="mem_add2">
		</div>
		<div class="form-group col-md-2">
			<label for="inputZip">bir</label> 
			<input type="text" class="form-control" name="mem_bir">
		</div>
		<div class="form-group col-md-2">
			<label for="inputZip">우편번호</label> 
			<input type="text" class="form-control" name="mem_zip">
		</div>
		<div class="form-group col-md-2">
			<label for="inputHometel">집전화</label> 
			<input type="text" class="form-control" name="mem_hometel">
		</div>
		<div class="form-group col-md-2">
			<label for="inputComtel">직장전화</label> 
			<input type="text" class="form-control" name="mem_comtel">
		</div>
		<div class="form-group col-md-2">
			<label for="inputHp">휴대폰 번호</label> 
			<input type="text" class="form-control" name="mem_hp">
		</div>
		<div class="form-group col-md-2">
			<label for="inputJob">직업</label> 
			<input type="text" class="form-control" name="mem_job">
		</div>
		<div class="form-group col-md-2">
			<label for="inputLike">취미</label> 
			<input type="text" class="form-control" name="mem_like">
		</div>
		<div class="form-group col-md-2">
			<label for="inputMemorial">기념일 이름</label> 
			<input type="text" class="form-control" name="mem_memorial">
		</div>
		<div class="form-group col-md-2">
			<label for="inputMemorialDay">기념일</label> 
			<input type="date" class="form-control" name="mem_memorialday">
		</div>
		<button type="submit" class="btn btn-primary">가입하기</button>
		<button type="reset" class="btn btn-primary">취소하기</button>
	</form>
</body>
</html>