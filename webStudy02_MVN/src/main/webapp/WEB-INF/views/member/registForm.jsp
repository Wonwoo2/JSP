<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
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
		var registForm = $("#registForm");
		var inputID = $("[name='mem_id']");
		
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
				url : "<%=request.getContextPath()%>/idCheck.do",
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
	<%String msg = (String) request.getAttribute("msg");
			if (StringUtils.isNotBlank(msg)) {%>
		alert("<%=msg%>
	");
<%}%>
	});
</script>
</head>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />
<body>
	<form id="registForm" method="post">
		<table class="table table-bordered">
			<tr>
				<th>아이디</th>
				<td><input type="text" required name="mem_id"
					value="${ member.mem_id }" />
					<button class="btn btn-primary" type="button" id="checkBtn">중복
						확인</button> <span class="error">${ errors.get("mem_id") } </span></td>

			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" required name="mem_pass"	value="${ member.getMem_pass() }" /> 
					<span class='error'><%= errors.get("mem_pass") %></span>
				</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" required name="mem_name"	value="${ member.getMem_name() }" /> 
					<span class='error'><%= errors.get("mem_name") %></span>
				</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" required name="mem_regno1" value="${ member.getMem_regno1() }" />
					<span class='error'><%= errors.get("mem_regno1") %></span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" required name="mem_regno2" value="${ member.getMem_regno2() }" />
				<span class='error'><%= errors.get("mem_regno2") %></span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td>
					<input type="date" name="mem_bir" value="${ member.getMem_bir() }" placeholder="1999-01-01" pattern="yy-MM-dd" />
					<span class='error'><%= errors.get("mem_bir") %></span>
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" required name="mem_zip" value="${ member.getMem_zip() }" />
				<span class='error'><%= errors.get("mem_zip") %></span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td>
					<input type="text" required name="mem_add1" value="${ member.getMem_add1() }" />
					<span class='error'><%= errors.get("mem_add1") %></span>
				</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" required name="mem_add2" value="${ member.getMem_add2() }" />
					<span class='error'><%= errors.get("mem_add2") %></span>
					</td>
			</tr>
			<tr>
				<th>집전화번호</th>
				<td>
					<input type="text" required name="mem_hometel" value="${ member.getMem_hometel() }" />
					<span class='error'><%= errors.get("mem_hometel") %></span>
				</td>
			</tr>
			<tr>
				<th>회사전화번호</th>
				<td>
					<input type="text" required name="mem_comtel" value="${ member.getMem_comtel() }" />
					<span class='error'><%= errors.get("mem_comtel") %></span>
				</td>
			</tr>
			<tr>
				<th>휴대폰번호</th>
				<td>
					<input type="text" name="mem_hp" value="${ member.getMem_hp() }" />
					<span class='error'><%=errors.get("mem_hp")%></span>
				</td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="mem_job"
					value="${ member.getMem_job() }" /><span class='error'><%=errors.get("mem_job")%></span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<input type="text" name="mem_like" value="${ member.getMem_like() }" />
					<span class='error'><%=errors.get("mem_like")%></span>
				</td>
			</tr>
			<tr>
				<th>기념일</th>
				<td>
					<input type="text" name="mem_memorial" value="${ member.getMem_memorial() }" />
					<span class='error'><%=errors.get("mem_memorial")%></span>
				</td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td>
					<input type="date" name="mem_memorialday" value="${ member.getMem_memorialday() }" />
					<span class='error'><%= errors.get("mem_memorialday") %></span>
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>
					<input type="number" name="mem_mileage" value="${ member.getMem_mileage() }" />
					<span class='error'><%= errors.get("mem_mileage") %></span>
				</td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td>
					<input type="text" name="mem_delete" value="${ member.getMem_delete() }" />
					<span class='error'><%= errors.get("mem_delete") %></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-primary" value="전송" />
					<input type="reset" class="btn btn-primary"	value="취소" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>