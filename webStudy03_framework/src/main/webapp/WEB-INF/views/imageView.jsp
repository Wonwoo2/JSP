<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="kr.or.ddit.Contants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String streamingName = (String) request.getAttribute(Contants.IMAGESTREAMINGCOOKIENAME);
%>
<script type="text/javascript">
	$(function() {
		var imageRendering = function(option, value) {
			let clzName = $(option).attr("class");
			let innerTag = null;
			if (clzName.startsWith("image")) {
				innerTag = imgPtrn.replace("%S", value);
			} else if (clzName.startsWith("video")) {
				innerTag = videoPtrn.replace("%S", value);
			}
			if (innerTag) {
				resultArea.append(innerTag);
			}
		}
		var imgPtrn = "<img src='image.do?image=%S'/>";
		var videoPtrn = "<video src='image.do?image=%S' />";
		var resultArea = $("#resultArea");
		var select = $("select").on("change", function() {
			let options = $(this).find("option:selected");
			
			resultArea.empty();
			$(options).each(function(index, option) {
					let value = $(this).text();
				setTimeout(function(){
					imageRendering(option, value);
				}, 300);
			});
		});
		
		<%
			if(StringUtils.isNotBlank(streamingName)) {
		%>
				let json = '<%= streamingName %>';
				let objs = JSON.parse(json);
				select.val(objs);
				select.trigger("change");
		<%
			}
		%>
	});
</script>
<select multiple size="10">
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${not empty listFiles}"> --%>
<%-- 			<c:forEach items="${listFiles}" var="file"> --%>
<%-- 				${header.accept} --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:when> --%>
<%-- 		<c:otherwise> --%>
			
<%-- 		</c:otherwise> --%>
<%-- 	</c:choose> --%>
	<%
		String[] listFiles = (String[]) request.getAttribute("listFiles");
		for (String file : listFiles) {
			String mime = application.getMimeType(file);
			String clz = StringUtils.startsWith(mime, "image/") ? "image"
					: StringUtils.startsWith(mime, "video/") ? "video" : "none";
	%>
			<option class="<%=clz%>" ><%= file %></option>
	<%	
		}
	%>
</select>
<div id="resultArea">
</div>