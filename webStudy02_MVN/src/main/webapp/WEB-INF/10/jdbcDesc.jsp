<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="kr.or.ddit.Contants"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jdbcDesc.jsp</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("[name='property_name']").val("${property.property_name}");
		$("#searchForm").on("submit", function(event) {
			event.preventDefault();
			let url = this.action ? this.action : "";
			let method = $(this).attr("method") ? this.method : "get";
			let data = $(this).serialize(); // query string 생성

			$.ajax({
				url : url,
				data : data,
				method : method,
				dataType : "json",
				success : function(resp) {
					let trTags = [];
					if (resp.length > 0) {
						$(resp).each(function(index, dbProperty) {
							let tr = $("<tr>");
							tr.append(
									$("<td>").text(dbProperty.property_name),
									$("<td>").text(dbProperty.property_value),
									$("<td>").text(dbProperty.description)
							);
							trTags.push(tr);
						});
					} else {
						let tr = $("<tr>").attr({
							colspan : "3"
						}).append($("<td>").text("검색 결과가 없음"));
						trTags.push(tr);
					}
					$("#listArea").html(trTags);
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			});
			return false;
		});
	})
</script>
<style type="text/css">
table {
	border-collapse: collapse;
}

th {
	background-color: lightblue;
	height: 30px;
}

td {
	height: 50px;
}
</style>
</head>
<body>
	<h4>JDBC (JavaDataBaseConnectivity)</h4>
	<pre>
	- Facade Pattern : Facade 객체를 통해 실 객체에 명령을 전달하는 구조
	
	- JDBD 단계
	1. 드라이버를 빌드패스에 추가(pom.xml)
	2. 드라이버 로딩(드라이버 클래스 로딩)
	3. Connection 생성
	4. 쿼리 객체 생성
		1) Statement
		2) PreparedStatement
		3) CallableStatement
	5. 쿼리 실행
		1) ResultSet(리턴 타입) executeQuery(sql) 
		2) int(리턴 타입) executeUpdate(sql)
	6. 결과 집합 핸들링
	7. 자원 release
	<%
		List<DataBasePropertyVO> propertyList = (List<DataBasePropertyVO>) request.getAttribute("propertyList");
		List<String> propertyNameList = (List<String>) request.getAttribute("propertyNameList");
		DataBasePropertyVO property = (DataBasePropertyVO) request.getAttribute("property");
	%>
</pre>
	<form id="searchForm">
		프로퍼티명 : <select name="property_name">
			<option value>프로퍼티명 선택</option>
			<%
				for (String property_name : propertyNameList) {
			%>
			<option><%=property_name%></option>
			<%
				}
			%>
		</select> 
		프로퍼티값 : <input type="text" name="property_value" value="${property.property_value}" />
		디스크립션 : <input type="text" name="description" value="${property.description}" />
		<input id="search" type="submit" value="검색" />
		
	</form>
	<table border="1">
		<thead>
			<tr>
				<th><%=Contants.DATABASEPROPERTYNAME%></th>
				<th><%=Contants.DATABASEPROPERTYVALUE%></th>
				<th><%=Contants.DATABASEPDESCRPIPTION%></th>
			</tr>
		</thead>
		<tbody id="listArea">
			<%
				for (DataBasePropertyVO propertyVo : propertyList) {
			%>
			<tr>
				<td><%=propertyVo.getProperty_name()%></td>
				<td><%=propertyVo.getProperty_value()%></td>
				<td><%=propertyVo.getDescription()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>