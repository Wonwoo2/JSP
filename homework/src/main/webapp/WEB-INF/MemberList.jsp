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
<title>회원 조회 페이지</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var sendForm = $("#sendForm");
		$(".service").on("click", function(event) {
			let service = $(this).attr("name");
			let userid = $(this).attr("id");
			console.log(service);
			console.log(userid);
			let data = {};
			
			data.service = service;
			data.userid = userid;
			let dataType = null;
			
			if(service == "select") {
				dataType = "json";
			} else if (service == "delete") {
				dataType = "html";
			}
			
			$.ajax({
				url : "<%= request.getContextPath() %>/MemberService.do",
				data : data,
				method : "get",
				dataType : dataType,
				success : function(resp) {
					let trTags = [];
					if (service == "select") {
							let id = $("<tr>");
							let name = $("<tr>");
							let pass = $("<tr>");
							let alias = $("<tr>");
							let reg_dt = $("<tr>");
							let addr = $("<tr>");
							let zipcode = $("<tr>");
							let filename = $("<tr>");
							let realfilename = $("<tr>");
							
							id.append(
									$("<td>").text("아이디"),
									$("<td>").text(resp.userid)
							);
							name.append(
									$("<td>").text("이름"),
									$("<td>").text(resp.usernm)
							);
							pass.append(
									$("<td>").text("비밀번호"),
									$("<td>").text(resp.pass)
							);
							alias.append(
									$("<td>").text("닉네임"),
									$("<td>").text(resp.alias)
							);
							reg_dt.append(
									$("<td>").text("등록 일자"),
									$("<td>").text(resp.reg_dt)
							);
							addr.append(
									$("<td>").text("주소"),
									$("<td>").text(resp.addr1 + " " + resp.addr2)
							);
							zipcode.append(
									$("<td>").text("우편번호"),
									$("<td>").text(resp.zipcode)
							);
							filename.append(
									$("<td>").text("파일명"),
									$("<td>").text(resp.filename)
							);
							realfilename.append(
									$("<td>").text("실제파일명"),
									$("<td>").text(resp.realfilename)
							);
							
							trTags.push(id);
							trTags.push(name);
							trTags.push(pass);
							trTags.push(alias);
							trTags.push(reg_dt);
							trTags.push(addr);
							trTags.push(zipcode);
							trTags.push(filename);
							trTags.push(realfilename);
							$("#memberInfoArea").html(trTags);
						} else if (service == "delete") {
							$("#" + resp).remove();
						}
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			});
		});
	});
	
</script>
</head>
<%
	List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
%>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h3 style="text-align: center; margin-top: 30px;">회원 조회</h3>
			</div>
		</div>
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; margin-top: 30px;">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center; border: 1px solid #dddddd;">아이디</th>
						<th style="background-color: #eeeeee; text-align: center; border: 1px solid #dddddd;">이름</th>
						<th style="background-color: #eeeeee; text-align: center; border: 1px solid #dddddd;">별명</th>
						<th style="background-color: #eeeeee; text-align: center; border: 1px solid #dddddd;">등록날짜</th>
						<th style="background-color: #eeeeee; text-align: center; border: 1px solid #dddddd;" colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<%
						if(memberList == null || memberList.size() == 0) {
					%>
					<tr>
						<td colspan="6">회원이 존재하지 않습니다.</td>
					</tr>
					<%
						} else {
							for (MemberVO memVo : memberList) {
					%>
					<tr id="<%= memVo.getUserid() %>">
						<td><%= memVo.getUserid() %> </td>
						<td><%= Objects.toString(memVo.getUsernm(), "") %> </td>
						<td><%= Objects.toString(memVo.getAlias(), "") %> </td>
						<td><%= Objects.toString(memVo.getReg_dt(), "") %> </td>
						<td><input id="<%= memVo.getUserid() %>" type="button" class="btn btn-light service" name="select" value="상세보기" /></td>
						<td><input id="<%= memVo.getUserid() %>" type="button" class="btn btn-light service" name="delete" value="삭제" /></td>
					</tr>
					<%			
							}
					%>
							
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<div class="row">
			<table style="text-align: center; border: 1px solid #dddddd; margin-top: 30px;">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center; border: 1px solid #dddddd;" colspan="2">회원 정보</th>
					</tr>
				</thead>
				<tbody id="memberInfoArea">
					<%-- <%
						if (memInfo == null) {
					%>
					<tr>
						<td colspan="2" rowspan="10"></td>
					</tr>
					<%
						} else {
					%>
					<tr>
						<td>아이디</td>
						<td><%= memInfo.getUserid() %></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><%= memInfo.getUsernm() %></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><%= memInfo.getPass() %></td>
					</tr>
					<tr>
						<td>별명</td>
						<td><%= memInfo.getAlias() %></td>
					</tr>
					<tr>
						<td>등록 일자</td>
						<td><%= memInfo.getReg_dt() %></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><%= memInfo.getAddr1() %> <%= memInfo.getAddr2() %></td>
					</tr>
					<tr>
						<td>우편 번호</td>
						<td><%= memInfo.getZipcode() %></td>
					</tr>
					<tr>
						<td>파일명</td>
						<td><%= memInfo.getFilename() %></td>
					</tr>
					<tr>
						<td>실제파일명</td>
						<td><%= memInfo.getRealfilename() %></td>
					</tr>
					<%		
						}
					%> --%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>