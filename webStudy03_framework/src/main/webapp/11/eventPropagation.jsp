<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	div {
		border : 1px solid black;
	}
</style>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var middle = $("#middle").on("click", "#inner", function() {
			event.stopPropagation();
			alert(this.id);
		});
		
		$("#addBtn, #removeBtn").on("click", function() {
			let cmd = this.id;
			if (cmd == "addBtn") {
				let newDiv = $("<div>").prop("id", "inner").text("안쪽 영역");
				$("#middle").append(newDiv);
			} else if (cmd == "removeBtn") {
				$("#inner").remove();
			}
		});
	});
</script>
</head>
<body>
<h4>이벤트 전파 구조</h4>
<pre>
	event.preventDefault(); : 이벤트 자체를 중단 시킴.
	event.stopPropagation(); : 이벤트 전파 중단(bubbling, capturing)
</pre>
	<button id="removeBtn">div 없애기</button>
	<button id="addBtn">div 만들기</button>
	<div id="outer">
		바깥 영역
		<div id="middle">
			중간 영역
			<div id="inner">
				안쪽 영역
			</div>
		</div>
	</div>
</body>
</html>