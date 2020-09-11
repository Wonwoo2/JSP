<%@page import="kr.or.ddit.servlet04.FileWrapper"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ServletContext context = getServletContext().getContext("/dummy");
%>
<style type=text/css>
#tree1, #tree2 {
	width : 40%;
	margin : 50px;
	float: left;
}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/skin-win8/ui.fancytree.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/jquery.fancytree-all-deps.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/jquery.fancytree-all.min.js"></script>

<script type="text/javascript">
	$(function() {
		var tree1 = null;
		var tree2 = null;
		var commandProcess = function(param) {
			let command = param.command;
			let srcFile = param.data.otherNode.key;
			let destFolder = null;
			if(param.node) {
				destFolder = param.node.key;
			}
			
			$.ajax({
				data : {
					command : command,
					srcFile : srcFile,
					destFolder : destFolder
				},
				method : "post",
				dataType : "json",		// Accept, Content-Type
				success : function(resp) {
					if(!resp.success) {
						return;
					}
					if(command == "COPY") {
						param.data.otherNode.copyTo(param.node);
					} else if(command == "MOVE") {
						param.data.otherNode.moveTo(param.node, param.data.hitMode);
					} else {
						param.data.otherNode.remove();
					}
				},
				error : function(errorResp) {
					console.log(errorResp);
				}
			});
		}
		
		$("#tree1").fancytree({
			extensions: ["dnd", "edit"],
			selectMode: 1,
		    init : function(event, data) {
		    	tree1 = data.tree;
		    }, 
			dnd: {
				autoExpandMS: 400,
		        focusOnClick: true,
				preventVoidMoves: true, 
				preventRecursiveMoves: true, 
				dragStart: function(node, data) {
					return true;
				},
				dragEnter: function(node, data) {
					let pass = false;
					pass = node.folder && node != data.otherNode.parent && node.parent != data.otherNode.parent;
					return pass;
				},
				dragDrop: function(node, data) {
					// 서버사이드의 진짜 자원에 대한 커맨드 처리
					let param = {
							node : node,
							data : data,
							command : data.originalEvent.ctrlKey ? "COPY" : "MOVE"
					}
					commandProcess(param);
				}
			},
// 			source: {
<%-- 				url : "<%= request.getContextPath() %>/serverExplorer.do" --%>
// 			},
		    lazyLoad: function(event, data) {
				data.result = {url: "<%= request.getContextPath() %>/serverExplorer.do?url=" + data.node.key};
			}
		});
		
		$("#tree2").fancytree({
			extensions: ["dnd", "edit"],
			selectMode: 1,
		    init : function(event, data) {
		    	tree2 = data.tree;
		    }, 
			dnd: {
				autoExpandMS: 400,
		        focusOnClick: true,
				preventVoidMoves: true, 
				preventRecursiveMoves: true, 
				dragStart: function(node, data) {
					return true;
				},
				dragEnter: function(node, data) {
					let pass = false;
					pass = node.folder && node != data.otherNode.parent && node.parent != data.otherNode.parent;
					return pass;
				},
				dragDrop: function(node, data) {
					// 서버사이드의 진짜 자원에 대한 커맨드 처리
					let param = {
							node : node,
							data : data,
							command : data.originalEvent.ctrlKey ? "COPY" : "MOVE"
					}
					commandProcess(param);
				}
			},
// 			source: {
<%-- 				url : "<%= request.getContextPath() %>/serverExplorer.do" --%>
// 			},
		    lazyLoad: function(event, data) {
				data.result = {
						url: "<%= request.getContextPath() %>/serverExplorer.do?contextName=<%= context.getContextPath() %>&url=" + data.node.key
				};
			}
		});
		
		$(window).on("keyup", function(event) {
			if(event.keyCode != 46) {
				return;
			}
			
			let pass = confirm("지우실?");
			if(pass) {
				let srcFile = tree1.getActiveNode();
				commandProcess({
					command : "DELETE",
					data : {
						otherNode : srcFile
					}
				});
			}
		});
	});
</script>
<h4>Model2 구조로 webStudy01 컨텍스트의 익스플로러 구현</h4>
	<div id="tree1">
		<ul>
			<li id="/" class="folder lazy"><%= request.getContextPath() %></li>
		</ul>
	</div>
	<div id="tree2">
		<ul>
			<li id="/" class="folder lazy"><%= context.getContextPath() %></li>
		</ul>
	</div>
