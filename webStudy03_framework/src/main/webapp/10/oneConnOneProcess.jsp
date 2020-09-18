<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	long startTime = System.currentTimeMillis();
	String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID = 'a001'";
	String mem_name = null;
	try (
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
	) {
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			mem_name = rs.getString("MEM_NAME");
		}
	}
	out.println(mem_name);
	long endTime = System.currentTimeMillis();
%>
소요 시간 : <%= endTime - startTime %>ms
</body>
</html>