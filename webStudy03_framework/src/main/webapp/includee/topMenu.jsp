<%@page import="kr.or.ddit.enumpkg.TopMenuType"%>
<%@page import="kr.or.ddit.vo.MenuVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="menuForm" action="">
	<input type="hidden" name="service" />
</form>
<ul>
	<%
		TopMenuType[] types = TopMenuType.values();
		for (TopMenuType tmp : types) {
			MenuVO menuVo = tmp.getMenuVo();
	%>
	<li>
		<a class="topMenu" data-service="<%= menuVo.getMenuId() %>" data-action="<%= menuVo.getMenuURI() %>">
			<%= menuVo.getMenuText() %>
		</a>
	</li>
	<%
			}
	%>
</ul>
<script type="text/javascript">
	var menuForm = $("#menuForm");
	$(".topMenu").on("click", function(event) {
		event.preventDefault();
		
		let service = $(this).data("service");
		let action = $(this).data("action");

		menuForm.find("[name='service']").val(service);
		menuForm.attr("action", "<%= request.getContextPath() %>/" + action);
		menuForm.submit();
		return false;
	});
</script>