<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.enumpkg.ServiceType"%>
<%@page import="kr.or.ddit.vo.MenuVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="menuForm" action="">
	<input type="hidden" name="service" />
</form>
<ul>
	<%
			ServiceType[] types = ServiceType.values();
			for (ServiceType tmp : types) {
				MenuVO menuVo = tmp.getMenuVo();
	%>
	<li>
		<a class="leftMenu" data-service="<%= menuVo.getMenuId() %>" data-action="<%= menuVo.getMenuURI() %>">
			<%= menuVo.getMenuText() %>
		</a>
	</li>
	<%
			}
			
			Map<String, MemberVO> userList = (Map<String, MemberVO>) application.getAttribute("userList");
			
			for (Entry<String, MemberVO> entry : userList.entrySet()) {
				MemberVO member = entry.getValue();
	%>
	<li>
		<ul><%= member.getMem_name() %></ul>
	</li>
	<%			
			}
	%>
</ul>
<script type="text/javascript">
	var menuForm = $("#menuForm");
	$(".leftMenu").on("click", function(event) {
		event.preventDefault();
		
		let service = $(this).data("service");
		let action = $(this).data("action");

		menuForm.find("[name='service']").val(service);
		menuForm.attr("action", "<%= request.getContextPath() %>/" + action);
		menuForm.submit();
		return false;
	});
</script>