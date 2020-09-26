<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="menuForm" action="">
	<input type="hidden" name="service" />
</form>
<c:url value="/member/memberList.do" var="memberListURL" />
<c:url value="/prod/prodList.do" var="prodListURL" />
<c:url value="/buyer/buyerList.do" var="buyerListURL" />
<ul>
	<li><a href="${memberListURL}">회원관리</a></li>
	<li><a href="${prodListURL}">상품관리</a></li>
	<li><a href="${buyerListURL}">거래처관리</a></li>
	<li><a href="#">게시판</a></li>
	<li><a href="#">방명록</a></li>
</ul>
<script type="text/javascript">
	var menuForm = $("#menuForm");
	$(".topMenu").on("click", function(event) {
		event.preventDefault();
		
		let service = $(this).data("service");
		let action = $(this).data("action");

		menuForm.find("[name='service']").val(service);
		menuForm.attr("action", "${pageContext.request.contextPath}" + action);
		menuForm.submit();
		return false;
	});
</script>