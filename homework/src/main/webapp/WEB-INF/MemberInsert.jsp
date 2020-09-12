<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록 페이지</title>
</head>
<body>
	<fieldset id="subFiled">
		<legend>회원 추가</legend>
		<form>
		  <div class="form-row">
		  	<div class="form-group col-md-6">
		      <label for="userID">아이디</label>
		      <input type="text" class="form-control" name="userid" id="userID">
		    </div>
		    <button type="button" class="btn btn-primary">중복 확인</button>
		  </div>
		  <div class="form-group col-md-6">
		      <label for="userName">이름</label>
		      <input type="text" class="form-control" name="usernm" id="userName">
		  </div>
		  <div class="form-group col-md-6">
		     <label for="userPass">비밀번호</label>
		     <input type="password" class="form-control" name="pass" id="userPass">
		  </div>
		  <div class="form-group col-md-6">
		     <label for="passCheck">비밀번호 확인</label>
		     <input type="password" class="form-control" name="passchk" id="passCheck">
		  </div>
		  <div class="form-group col-md-6">
		      <label for="userAlias">별명</label>
		      <input type="text" class="form-control" name="userAlias">
		  </div> 
		  <div class="form-group">
		    <label for="userAddress">주소</label>
		    <input type="text" class="form-control" name="addr1" id="userAddress">
		  </div>
		  <div class="form-group">
		    <label for="userDetailAddress">상세주소</label>
		    <input type="text" class="form-control" name="addr2" id="userDetailAddress">
		  </div>
		  <div class="form-group col-md-2">
		      <label for="userZipcode">우편번호</label>
		      <input type="text" class="form-control" name="zipcode" id="userZipcode">
		  </div>
		  <button type="submit" class="btn btn-primary">등록</button>
		  <button type="reset" class="btn btn-primary">취소</button>
		</form>
	</fieldset>
</body>
</html>