<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	- Http(Stateless) 특성의 단점을 보완하기 위한 방법
	1. session : 상태 정보를 서버 측에 저장하는 방법
		1) 서버 부하가 있을 수 있음
		2) 데이터의 저장 기간이 제한됨
	2. cookie : 상태 정보를 클라이언트 측에 저장하는 방법
		1) 보안 취약성
		2) 네트워크 부하를 줄이기 위해 크기 제한
	
	쿠키 사용 방법
	---------------- 서버에서의 쿠키 ----------------
	1. 서버측에서 쿠키 (객체) 생성 - name, value(필수 속성 : 객체를 생성할 때 명시함)
	2. 쿠키의 속성 설정(필수 속성과 선택 속성이 존재)
	3. 응답 데이터에 쿠키를 포함시켜 전송
	---------------- 클라이언트에서의 쿠키 ----------------
	4. 쿠키를 자기 저장소에 저장
	5. 다음번 요청에 쿠키를 재전송(name, value를 제외한 속성들과 관련이 있음)
	---------------- 서버에서의 쿠키 ----------------
	6. 재전송된 쿠키를 받고, 상태 복원
	
	<%--
		Cookie sampleCookie = new Cookie("sampleCookie", "sampleValue");
		response.addCookie(sampleCookie);
	--%>
	
	<%
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if("sampleCookie".equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
	%>
	
	<%= cookieValue %>
	
</pre>
</body>
</html>