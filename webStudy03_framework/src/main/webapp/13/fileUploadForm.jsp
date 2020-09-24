<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form action="<%= request.getContextPath() %>/fileUpload.do" method="post" enctype="multipart/form-data">
	업로더 : <input type="text" name="uploader" />
	업로드파일 : <input type="file" name="uploadFile" />
	업로드파일 : <input type="file" name="uploadFile" />
	<input type="submit" value="전송" />
</form>
<%= session.getAttribute("saveURLs") %>
<img src="${pageContext.request.contextPath}${saveURLs[0]}" />
<%
	session.removeAttribute("saveURLs");
%>
</body>
</html>