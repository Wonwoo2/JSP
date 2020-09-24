<%@page import="kr.or.ddit.enumpkg.TopMenuType"%>
<%@page import="kr.or.ddit.vo.MenuVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="menuForm" action="">
	<input type="hidden" name="service" />
</form>
<ul>
	<li><a href="<%= request.getContextPath() %>/member/memberList.do">회원관리</a></li>
	<li><a href="<%= request.getContextPath() %>/prod/prodList.do">상품관리</a></li>
	<li><a href="#">거래처관리</a></li>
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
		menuForm.attr("action", "<%= request.getContextPath() %>" + action);
		menuForm.submit();
		return false;
	});
</script>