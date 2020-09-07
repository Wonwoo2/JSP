<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("ul li").on("click", function() {
			var listVal = this.id;
			var list = $(this);
			$.ajax({
				url : "<%= request.getContextPath() %>/subExplorer.do",
				data : "url=" + listVal,
				dataType : "html",
				success : function(resp) {
					let ul = list.find("ul").get(0);
					if(ul) {
						
					} else {
						list.append(resp);
					}
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			})
		});
	})
</script>
</head>
<body>
<h4>Model2 구조로 webStudy01 컨텍스트의 익스플로러 구현</h4>
<ul>
<%
	File[] listFiles =(File[]) request.getAttribute("listFiles");
	String contextRealPath = application.getRealPath("/");
	
	for(File file : listFiles){
		String clz = file.isDirectory() ? "dir":"file";
		String fileAbPath = file.getAbsolutePath();
		String tmp = StringUtils.remove(fileAbPath, contextRealPath);
		String fileURI = tmp.replace(File.separatorChar, '/');
		fileURI = fileURI.startsWith("/") ? fileURI:"/" + fileURI;
%>
		<li class="<%= clz %>" id="<%= fileURI %>"><%=file.getName() %></li>
<%
	}	
%>
</ul>
</body>
</html>