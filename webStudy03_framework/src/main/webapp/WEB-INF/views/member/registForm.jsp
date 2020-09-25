<%@page import="java.util.Date"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/registForm.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$("input").addClass("form-control");
		$("th").addClass("text-center");
		var registForm = $("#registForm");
		var inputID = $("[name='mem_id']");
		
		registForm.validate({
			rules : {
				mem_id : {
					required : true,
					minlength : 6,
					maxlength : 15
				}, 
				mem_pass : {
					required : true,
					minlength : 8,
					maxlength : 15
				}, 
				mem_name : {
					required : true,
					maxlength : 20
				}
			},
			
			messages : {
				mem_id : {
					required : "아이디는 필수 입력 항목 입니다.",
					minlength : "아이디는 최소 6글자 입니다.",
					maxlength : "아이디는 최대 15글자 입니다."
				}, 
				mem_pass : {
					required : "비밀번호는 필수 입력 항목 입니다.",
					minlength : "비밀번호는 최소 8글자 입니다.",
					maxlength : "비밀번호는 최대 15글자 입니다."
				}, 
				mem_name : {
					required : "비밀번호는 필수 입력 항목 입니다.",
					minlength : "비밀번호는 최소 8글자 입니다.",
					maxlength : "비밀번호는 최대 15글자 입니다."
				}
			}
		});
		
		registForm.on("submit", function() {
			let valid = $(this).data("validID");
			if (!valid) {
				alert("아이디 중복 체크하세요");
				valid = false;
			}
			return valid;
		});
		
		inputID.on("change", function() {
			registForm.data("validID", false);
			$(this).next('.idCheckMsg').remove();
		});
		
		$("#checkBtn").on("click", function() {
			let mem_id = inputID.val();
			
			$.ajax({
				url : "${pageContext.request.contextPath}/idCheck.do",
				data : {
					mem_id : mem_id
				},
				method : "post",
				dataType : "json",
				success : function(resp) {
					if (resp.validID) {
						let msgTag = inputID.next(".idCheckMsg");
						if (msgTag.length == 0) {
							inputID.after("<span class='idCheckMsg'>사용 가능한 아이디입니다.</span>");
						}
						registForm.data("validID", true);
					} else {
						alert("아이디가 중복입니다.");
						inputID.focus();
					}
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			})
		});
	<%
		String msg = (String) request.getAttribute("msg");
		if (StringUtils.isNotBlank(msg)) {
	%>
			alert("<%=msg%>");
	<%
		}
	%>
	});
</script>
</head>
<body>
	<form class="cmxform form-inline" id="registForm" method="post" enctype="multipart/form-data">
		<div class="container">
			<div class="jumbotron">
				<h4 class="dispaly4 text-center">회원 가입</h4>
				<table class="table table-bordered">
					<tr>
						<th>아이디</th>
						<td>
							<div class="form-group">
								<input class="col-md-4" type="text" name="mem_id" value="${registMember.mem_id}" maxlength="15" required />
								<button class="btn btn-primary ml-2" type="button" id="checkBtn">중복 확인</button> 
								<span class="error"> ${errors.get("mem_id")} </span>
							</div>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<div class="form-group">
								<input class="col-md-4" type="password" name="mem_pass"	value="${registMember.mem_pass}" maxlength="15" required /> 
								<span class='error'> ${ errors.get("mem_pass") } </span>
							</div>
						</td>
					</tr>
					<tr>
						<th>회원명</th>
						<td>
							<div class="form-group">
								<input class="col-md-4" type="text" name="mem_name"	value="${registMember.mem_name}" maxlength="20" required /> 
								<span class='error'> ${ errors.get("mem_name") } </span>
							</div>
						</td>
					</tr>
					<tr>
						<th>주민번호</th>
						<td>
							<input class="mr-2" type="text" name="mem_regno1" value="${registMember.mem_regno1}" maxlength="6" pattern="[0-9]{6}" required />
							<input class="ml-2" type="text" name="mem_regno2" value="${registMember.mem_regno2}" maxlength="7" pattern="[0-9]{7}" required />
							<span class='error'>${errors.get("mem_regno1")}</span>
							<span class='error'>${errors.get("mem_regno2")}</span>
						</td>
					</tr>
					<tr>
						<th>생일</th>
						<td>
							<input type="date" name="mem_bir" value="${registMember.mem_bir}" pattern="\d{4}-\d{2}-\d{2}" />
							<span class='error'>${errors.get("mem_bir")}</span>
						</td>
					</tr>
					<tr>
						<th>프로필 사진</th>
						<td>
							<input type="file" name="mem_image" />
							<span class='error'>${errors.get("mem_img")}</span>
						</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
							<input type="text" name="mem_zip" value="${registMember.mem_zip}" 
								maxlength="7" pattern="[0-9]{2,3}-[0-9]{2,3}" readonly required />
							<button type="button" class="btn btn-dark ml-2" data-toggle="modal" data-target="#zipSearchModal">우편번호 검색</button>
							<span class='error'>${errors.get("mem_zip")}</span>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<div class="form-group">
								<input class="col-md-6" type="text" name="mem_add1" value="${registMember.mem_add1}" 
									maxlength="100" readonly required />
								<span class='error'>${errors.get("mem_add1")}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>상세주소</th>
						<td>
							<div class="form-group">
								<input class="col-md-6" type="text" name="mem_add2" value="${registMember.mem_add2}" 
									maxlength="80"  required />
								<span class='error'>${errors.get("mem_add2")}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>집전화번호</th>
						<td>
							<div class="form-group">
								<input class="col-md-4" type="text" required name="mem_hometel" value="${registMember.mem_hometel}" 
										maxlength="14" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" required />
								<span class='error'> ${errors.get("mem_hometel")}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>회사전화번호</th>
						<td>
							<div class="form-group">
								<input class="col-md-4" type="text" name="mem_comtel" value="${ member.getMem_comtel() }" 
									maxlength="14" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" required />
								<span class='error'> ${errors.get("mem_comtel")}</span>
							</div>
						</td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td>
							<input type="text" name="mem_hp" value="${registMember.mem_hp}" 
								maxlength="15" pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}" required />
							<span class='error'>${errors.get("mem_hp")}</span>
						</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
							<input type="text" name="mem_job" value="${registMember.mem_job}" 
								maxlength="40" />
							<span class='error'>${errors.get("mem_job")}</span>
						</td>
					</tr>
					<tr>
						<th>취미</th>
						<td>
							<input type="text" name="mem_like" value="${registMember.mem_like}" 
								maxlength="40" />
							<span class='error'>${errors.get("mem_like")}</span>
						</td>
					</tr>
					<tr>
						<th>기념일</th>
						<td>
							<input type="text" name="mem_memorial" value="${registMember.mem_memorial}" 
								maxlength="40" />
							<span class='error'>${errors.get("mem_memorial")}</span>
						</td>
					</tr>
					<tr>
						<th>기념일자</th>
						<td>
							<input type="date" name="mem_memorialday" value="${registMember.mem_memorialday}"  
								pattern="\d{4}-\d{2}-\d{2}" />
							<span class='error'>${errors.get("mem_memorialday")}</span>
						</td>
					</tr>
					<tr>
						<th>마일리지</th>
						<td>기본마일리지</td>
					</tr>
					<tr>
						<th>탈퇴여부</th>
						<td>신규가입</td>
					</tr>
				</table>
				<div class="form-group form-inline">
					<input id="submit" class="btn btn-primary col-md-2" type="submit" value="전송" />
					<input class="btn btn-primary ml-2 col-md-2" type="reset" value="취소" />
				</div>
			</div>
		</div>
	</form>
	<div id="modalDiv">
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zip.js"></script>
	<script type="text/javascript">
		let modalDiv = $("#modalDiv");
		let zipcode = $("[name='mem_zip']");
		let addr1 = $("[name='mem_add1']");
		let addr2 = $("[name='mem_add2']");
		
		let tag = {};
		tag.modalDiv = modalDiv;
		tag.zipcode = zipcode;
		tag.addr1 = addr1;
		tag.addr2 = addr2;
		
		$("registForm").zipSearch({
			tag : tag,
			command : "regist",
			url : "${pageContext.request.contextPath}/dataTable.do",
		});
	</script>
</body>
</html>