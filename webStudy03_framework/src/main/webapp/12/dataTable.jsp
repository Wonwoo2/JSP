<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/dataTable.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table id="zipTable">
		<thead>
			<tr>
				<th>우편번호</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script type="text/javascript">
		$("#zipTable").DataTable({
			processing : true,
	        serverSide : true,
			ajax : "<%= request.getContextPath() %>/dataTable.do",
			columns : [
	            { "data": "zipcode" },
	            { "data": "address" },
	        ]
		});
	</script>
</body>
</html>