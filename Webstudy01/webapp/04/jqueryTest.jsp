<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$.fn.test=function(sample){
		this.css(sample);
		sample.temp();
	};
	$(function(){
		var divs = $("div");
		console.log(divs);
		console.log(divs[0]);
		console.log(divs.get(0));
		console.log(divs.html());
		console.log(divs[0].innerHTML);
		$("#div1").test({
			backgroundColor:"yellow"
			, temp:function(){
				alert("TEMP");
			}
		});
	});
// 	$(document).ready(function(){
		
// 	});
// 	function init(){
// 	}
</script>
</head>
<body >
<div id="div1">DIV1</div>
<div id="div2">
	DIV2
	<div id="div3">DIV3</div>
</div>
<script type="text/javascript">
	var object1 = {
			prop1:"value1"
			, prop2:"valu2"
	}
	var object2 = {};
	object2.prop1="value1";
	object2['prop2']="value2"; // 연관배열, 연상배열구조 
	// javascript 객체를 JSON 으로 변환 : marshalling , JSON.stringify
	// JSON 을 javascript 객체로 변환 :unmarshalling , JSON.parse
</script>
</body>
</html>














