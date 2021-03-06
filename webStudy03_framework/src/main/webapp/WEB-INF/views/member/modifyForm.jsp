<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember/modifyForm.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	<form class="form-inline" action="${pageContext.request.contextPath}/memberUpdate.do" method="post">
		<div class="container">
			<div class="jumbotron">
				<h4 class="dispaly4 text-center">회원 수정</h4>
				<div class="form-group row">
					<label class="col-md-4">아이디</label>
					<input class="col-md-4" type="text" name="mem_id" value="${updateMember.mem_id}" 
						maxlength="15" readonly required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">비밀번호</label>
					<input class="col-md-4" type="text" name="mem_pass" value="${updateMember.mem_pass}" 
						maxlength="15" required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">이름</label>
					<input class="col-md-4" type="text" name="mem_name" value="${updateMember.mem_name}" 
						maxlength="20" required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">주민번호</label>
					<input class="mr-2" type="text" name="mem_regno1" value="${updateMember.mem_regno1}" 
						maxlength="6" readonly required />
					<input class="ml-2" type="text" name="mem_regno2" value="${updateMember.mem_regno2}" 
						maxlength="7" readonly required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">생일</label>
					<input type="date" name="mem_bir" value="${updateMember.mem_bir}" 
						placeholder="1999-01-01" pattern="yy-MM-dd" readonly />
				</div>
				<div class="form-group row">
					<label class="col-md-4">우편번호</label>
					<input type="text" name="mem_zip" value="${updateMember.mem_zip}" 
						maxlength="7" readonly required />
					<button type="button" class="btn btn-dark ml-2 mb-2" data-toggle="modal" data-target="#zipSearchModal">우편번호 검색</button>
				</div>
				<div class="form-group row">
					<label class="col-md-4">주소</label>
					<input class="col-md-6" type="text" name="mem_add1" value="${updateMember.mem_add1}" 
						maxlength="100" readonly required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">상세주소</label>
					<input class="col-md-6" type="text" name="mem_add2" value="${updateMember.mem_add2}" 
						maxlength="80" required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">집 전화</label>
					<input class="col-md-6" type="text" name="mem_hometel" value="${updateMember.mem_hometel}" 
						maxlength="14" required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">회사 전화</label>
					<input class="col-md-6" type="text" name="mem_comtel" value="${updateMember.getMem_comtel}" 
						maxlength="14" required />
				</div>
				<div class="form-group row">
					<label class="col-md-4">휴대폰</label>
					<input type="text" name="mem_hp" value="${updateMember.mem_hp}" 
						maxlength="15" />
				</div>
				<div class="form-group row">
					<label class="col-md-4">직업</label>
					<input type="text" name="mem_job" value="${updateMember.mem_job}" 
						maxlength="40" />
				</div>
				<div class="form-group row">
					<label class="col-md-4">취미</label>
					<input type="text" name="mem_like" value="${updateMember.mem_like}" 
						maxlength="40" />
				</div>
				<div class="form-group row">
					<label class="col-md-4">기념일</label>
					<input type="text" name="mem_memorial" value="${updateMember.mem_memorial}" maxlength="40" />
				</div>
				<div class="form-group row">
					<label class="col-md-4">기념일자</label>
					<input type="date" name="mem_memorialday" value="${updateMember.mem_memorialday}" placeholder="1999-01-01" pattern="yyyy-MM-dd" />
				</div>
				<div class="form-group row">
					<label class="col-md-4">마일리지</label>
					<input type="text" name="mem_mileage" value="${updateMember.mem_mileage}" readonly />
				</div>
				<div class="form-group row">
					<label class="col-md-4">탈퇴여부</label>
					<input type="text" name="mem_delete" value="${updateMember.mem_delete}" readonly />
				</div>
				<div class="form-group row">
					<input style="width: 20%; margin-left: auto;" class="mt-2" type="submit" value="확인" />
					<input style="width: 20%; margin-right: auto;" class="mt-2" type="reset" value="취소" onclick="location.href='#'" />
				</div>
			</div>
		</div>
	</form>
					
	<div id="modalDiv">
	</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/zip.js"></script>
<script type="text/javascript">
	$(function() {
		let modalDiv = $("#modalDiv");
		let zipcode = $("[name='mem_zip']");
		let addr1 = $("[name='mem_add1']");
		let addr2 = $("[name='mem_add2']");
		
		let tag = {};
		tag.modalDiv = modalDiv;
		tag.zipcode = zipcode;
		tag.addr1 = addr1;
		tag.addr2 = addr2;
		
		$("input").addClass("form-control").addClass("mb-2");
		$("#modifyForm").zipSearch({
			tag : tag,
			command : "modify",
			url : "${pageContext.request.contextPath}/dataTable.do",
		});
		
	});
</script>
</html>