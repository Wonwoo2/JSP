<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var inputs = $(".btn");
		var mainForm = $("#mainForm");
		var subForm = $("#subForm");
		
		var tds = $("td");
		var userid = null;
		var write = $("#write");
		
		// 입력값 변수
		var useridInput = $("[name='userid']");
		var usernmInput = $("[name='usernm']");
		var passdInput = $("[name='pass']");
		var reg_dtInput = $("[name='reg_dt']");
		var aliasInput = $("[name='alias']");
		var addr1Input = $("[name='addr1']");
		var addr2Input = $("[name='addr2']");
		var zipcodeInput = $("[name='zipcode']");
		var filenameInput = $("[name='filename']");
		var realfilenameInput = $("[name='realfilename']");
		
		/* inputs.on("click", function() {
			let commandValue = $(this).attr("name");
			
			if(commandValue == "delete") {
				
			}
			
			inputs.attr("disabled", true);
			
			write.show();
			
			let command = writeDiv.find("[name='command']");
		}); */
		
		tds.on("click", function() {
			tds.css("background-color", "white");
			
			userid = $(this).attr("class");
			
			$("." + userid).css("background-color", "yellow");
			
			let method = mainForm.attr("method");
			let action = mainForm.attr("action");
			
			$.ajax({
				url : action ? action : "",
				data : "userid=" + userid,
				method : method,
				dataType : "json",
				success : function(resp) {
					let userID = resp.userid ? resp.userid : " ";
					let userName = resp.usernm ? resp.usernm : " ";
					let userPass = resp.pass ? resp.pass : " ";
					let usertReg_dt = resp.reg_dt ? resp.reg_dt : " "; 
					let userAlias = resp.alias ? resp.alias : " "; 
					let userAddr1 = resp.addr1 ? resp.addr1 : " ";
					let userAddr2 = resp.addr2 ? resp.addr2 : " ";
					let userZipcode = resp.zipcode ? resp.zipcode : " "; 
					let userFileName = resp.filename ? resp.filename : " "; 
					let userRealFileName = resp.realfilename ? resp.realfilename : " ";
					
					useridInput.val(userID);
					usernmInput.val(userName);
					passdInput.val(userPass);
					reg_dtInput.val(usertReg_dt);
					aliasInput.val(userAlias);
					addr1Input.val(userAddr1);
					addr2Input.val(userAddr2);
					zipcodeInput.val(userZipcode);
					filenameInput.val(userFileName);
					realfilenameInput.val(userRealFileName);
					
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			});
		});
		
		var idCheckBtn = $("#idcheck");
		idCheckBtn.on("click", function() {
			let userID = useridInput.val();
			console.log(userid);
			if(userID == null || userID == "") {
				alert("아이디를 입력하세요.");
				return false;
			}
			let action = inputForm.attr("action");
			
			$.ajax({
				url : action ? action : "",
				data : "userid=" + userID,
				method : "get",
				dataType : "json",
				success : function(resp) {
					if(resp == null) {
						alert("사용가능한 아이디입니다.");
						idCheckBtn.attr("disabled", true);
					} else {
						alert("중복된 아이디입니다.");
						idCheckBtn.attr("disabled", false);
					}
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			})
		});
		
		subForm.on("submit", function(event) {
			console.log(this);
			event.preventDefault();
			
		});
	});
</script>
<style type="text/css">

table {
	border-collapse: collapse;
	border-color: lightgray;
}

th {
	background-color: lightblue;
	height: 50px;
}

td {
	width: 150px;
	height: 80px;
}

#mainField {
	width: 18%;
	padding : 20px;
	text-align: center;
	float: left;
}

#subFiled {
	width: 20%;
	padding : 20px;
}

input {
	margin: 10px;
}
</style>
</head>
<%
	List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
%>
<body>
	<fieldset id="mainField">
		<legend>회원 관리</legend>
		<table border="1">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>별명</th>
					<th>등록 날짜</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (MemberVO memVo : memberList) {
				%>
				<tr>
					<td class="<%=memVo.getUserid()%>"><%=Objects.toString(memVo.getUserid(), "")%></td>
					<td class="<%=memVo.getUserid()%>"><%=Objects.toString(memVo.getUsernm(), "")%></td>
					<td class="<%=memVo.getUserid()%>"><%=Objects.toString(memVo.getAlias(), "")%></td>
					<td class="<%=memVo.getUserid()%>"><%=Objects.toString(memVo.getReg_dt(), "")%></td>
					<td class="<%=memVo.getUserid()%>"><input type="button" value="삭제" /></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</fieldset>
	<fieldset id="subFiled">
		<legend>입력</legend>
		<form id="subForm" action="<%= request.getContextPath() %>/MemberMgmt.do" method="post">
			<input type="text" name="userid" placeholder="아이디" />
			<input id="idcheck" type="button" value="중복체크"/>
			<input type="text" name="usernm" placeholder="이름" />
			<input type="password" name="pass" placeholder="비밀번호" />
			<input type="text" name="alias" placeholder="별명" />
			<input type="text" name="addr1" placeholder="주소" />
			<input type="text" name="addr2" placeholder="상세주소" />
			<input type="text" name="zipcode" placeholder="우편번호" />
			<input type="text" name="filename" placeholder="파일명" />
			<input type="text" name="realfilename" placeholder="실제파일명" /> <br>
			<input type="hidden" name="type" />
			
			<input class="btn" type="submit" name="insert" value="등록" />
			<input class="btn" type="submit" name="update" value="수정" />
			<input class="btn" type="submit" name="delete" value="삭제" />
			<input class="btn" type="submit" name="select" value="조회" /> <br>
			<input type="reset" value="취소" />
		</form>
	</fieldset>
</body>
</html>