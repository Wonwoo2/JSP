<%@page import="kr.or.ddit.calendarUtil.Week"%>
<%@page import="kr.or.ddit.calendarUtil.WeekConverter"%>
<%@page import="kr.or.ddit.calendarUtil.Month"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CalendarView</title>
<style type="text/css">
#calView {
	text-align: center;
	display: inline-block;
	width: 70%;
	margin-left: 5%;
	padding: 10px;
}

#calHeader {
	display: inline-block;
	text-align: center;
}

#leftImg {
	margin-right: 50px;
}

#rightImg {
	margin-left: 50px;
}

#calBodyTable {
	border-color: lightgray;
	border-collapse: collapse;
}

#calBodyTable .week {
	width: 200px;
	height: 50px;
}

</style>
</head>
<body>
	<div id="calView">

		<div id="calHeader">
			<table>
				<tr>
					<td width="150" align="right" valign="middle">
						<a href="">
							<img id="leftImg" src="<%=request.getContextPath()%>/images/imagesLeft.jpg" width="50" height="50">
						</a>
					</td>
					<td width='300' align='center' valign='middle'>title</td>
					<td width="150" align="left" valign="middle">
						<a href=""> 
							<img id="rightImg" src="<%= request.getContextPath() %>/images/images.png" width="50" height="50">
						</a>
					</td>
				</tr>

			</table>
			<table>
				<tr>
					<td>year:</td>
					<td>
						<input type="text" id="year" />
					</td>
					<td>month:</td>
					<td>
						<select id="month">
						<%
							Month[] months = Month.values();
											for (int i = 0;  i < months.length; i ++) {
						%>
							<option value="<%= months[i].getMonth() %>"><%= months[i].getMonthName() %></option>
						<%
							}
						%>
						</select>
					</td>
					<td>
						<select id="locale">
						<%
							Locale[] locales = Locale.getAvailableLocales();
							for (Locale locale : locales) {
								String display = locale.getDisplayLanguage(locale);
								if (StringUtils.isBlank(display))
									continue;
						%>
							<option value="<%= locale.toLanguageTag() %>"><%= display %></option>		
						<%
							}
						%>
						</select>
					</td>
					<td>
						<select id="timezone">
						<%
							String[] timeZones = TimeZone.getAvailableIDs();
							for (int i = 0; i < timeZones.length; i ++) {
						%>
						<option value="<%= timeZones[i] %>"><%= timeZones[i] %></option>	
						<%
							}
						%>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<hr>
<%
	String accept = request.getHeader("Accept");
	String acceptLanguage = request.getHeader("Accept-Language");
	Locale locale = request.getLocale();
	if (acceptLanguage != null) {
		locale = Locale.forLanguageTag(acceptLanguage);
	}
%>
		<div id="calBody">
			<table border="1" id="calBodyTable">
				<tr>
				<%
					Week[] weeks = Week.values();
					for (int i = 0; i < weeks.length; i ++) {
				%>
					<th class="week"><%= weeks[i].getConvertWeek(locale) %></th>		
				<%		
					}
				%>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>