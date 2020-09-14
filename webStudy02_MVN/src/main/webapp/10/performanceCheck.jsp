<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/performanceCheck.jsp</title>
</head>
<body>
<h4>성능 확인(지연시간 체크)</h4>
<pre>
	response time = Process time + Latency time
	<a href="oneConnOneProcess.jsp">한번의 연결과 한번의 처리 : 25ms, (pooling 적용)1ms</a>
	<a>백번의 연결과 백번의 처리 : 550ms, (pooling 적용)30ms</a>
	<a>한번의 연결과 백번의 처리 : 20ms, (pooling 적용)1ms</a>
</pre>
</body>
</html>