<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>회원 관리</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		/* var inputs = $(".btn");
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
		
		/* tds.on("click", function() {
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
		}); */
		
		var command = $("[name='command']");
		$("[name='insert']").on("click", function() {
			command.val("insert");
		});
		
		$("[name='update']").on("click", function() {
			command.val("update");
		});
		
		$("[name='select']").on("click", function() {
			command.val("select");
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="col-lg-6">
			<div class="jumbotron" style="padding-top: 20px; margin-top: 20px;">
				<form id="" action="<%= request.getContextPath() %>/MemberMgmt.do" method="get">
					<h3 style="text-align: center;">회원 관리</h3>
					<input type="hidden" name="command" />
					<input type="submit" class="btn btn-outline-danger command" style="margin: 15px;" name="insert" value="회원 등록">
					<input type="submit" class="btn btn-outline-danger command" style="margin: 15px;" name="update" value="회원 수정">
					<input type="submit" class="btn btn-outline-danger command" style="margin: 15px;" name="select" value="회원 조회">
				</form>
			</div>
		</div>
	</div>
</body>
</html>