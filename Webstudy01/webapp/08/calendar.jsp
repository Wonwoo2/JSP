<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int action = 0;
	int currentYear = 0;
	int currentMonth = 0;
	
	Calendar currentCal = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();
	
	String actionParam = request.getParameter("action");
	if(StringUtils.isNotBlank(actionParam)) {
		action = Integer.parseInt(actionParam);
		
		String monthParam = request.getParameter("month");
		String yearParam = request.getParameter("year");
		
		if(StringUtils.isNotBlank(monthParam) && StringUtils.isNotBlank(yearParam)) {
			currentMonth = Integer.parseInt(monthParam);
			currentYear = Integer.parseInt(yearParam);
			
			if(action == 1) {
				cal.set(currentYear, currentMonth, 1);
				cal.add(Calendar.MONTH, 1);
				currentMonth = cal.get(Calendar.MONTH);
				currentYear = cal.get(Calendar.YEAR);
			} else {
				cal.set(currentYear, currentMonth, 1);
				cal.add(Calendar.MONTH, -1);
				currentMonth = cal.get(Calendar.MONTH);
				currentYear = cal.get(Calendar.YEAR);
			}
		}
	} else {
		currentYear = cal.get(Calendar.YEAR);
		currentMonth = cal.get(Calendar.MONTH);
		cal.set(currentYear, currentMonth, 1);
	}
%>

<%!
	public boolean isDate(int year, int month, int day) {
		month -=1 ;
		
		Calendar c = Calendar.getInstance();
		c.setLenient(false);
		
		try {
			c.set(year, month, day);
			Date date = c.getTime();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getTitle(Calendar cal){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
    	return sdf.format(cal.getTime());    
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/Calendar(달력)</title>
<style>
table {
	border-collapse: collapse;
	border-color: lightgray;
}

th {
	width: 150px;
	height: 100px;
	font-size: 1.5em;
}

td {
	width: 150px;
	height: 100px;
	font-size: 1.5em;
}

#calView {
	text-align: center;
	display: inline-block;
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

#toDayColor {
    background-color: #6C7EAA;
}

</style>
</head>
<body>
	<div id="calView">
		<div id="calHeader">
			<table>
				<tr>
					<td width="150" align="right" valign="middle">
						<a href="Calendar.jsp?month=<%= currentMonth %>&year=<%= currentYear %>&action=0">
							<img id="leftImg" src="<%= request.getContextPath() %>/images/imagesLeft.jpg" width="50" height="50">
						</a>
					</td>
					<td width='300' align='center' valign='middle'>
						<b><%= getTitle(cal) %></b>
					</td>
					<td width="150" align="left" valign="middle">
						<a href="Calendar.jsp?month=<%= currentMonth %>&year=<%= currentYear %>&action=1">
							<img id="rightImg" src="<%= request.getContextPath() %>/images/images.png" width="50" height="50">
						</a>
					</td>
				</tr>
			</table>
		</div>
		<table>
			<tr>
				<td>year:</td>
				<td><input type="text" id="year" /></td>
				<td>month:</td>
				<td><select id="month">
				</select></td>
				<td><select id="locale">
						<%
						String pattern = "<option value='%s'>%s</option>\n";
						Locale[] locales = Locale.getAvailableLocales();
						for(Locale locale:locales) {
							String display = locale.getDisplayLanguage(locale);
							if(StringUtils.isBlank(display)) continue;
								out.println(String.format(pattern, locale.toLanguageTag(), display)
							);
						}
					%>
				</select></td>
				<td><select id="timezone">
				</select></td>
			</tr>
		</table>

		<hr>

		<table border="1">
			<tr>
				<th>Sun</th>
				<th>Mon</th>
				<th>Tue</th>
				<th>Wed</th>
				<th>Thu</th>
				<th>Fri</th>
				<th>Sat</th>
			</tr>
			<%
				int currentDay;
				String todayColor;
				int count = 1;
				int dispDay = 1;
				
				for (int i = 1; i < 7; i ++) {
			%>
				<tr>
			<%
					for (int j = 1; j < 8; j ++) {
						if(!(count >= cal.get(Calendar.DAY_OF_WEEK))) {
			%>
					<td class="emty">&nbsp;</td>
			<%
							count += 1;
						} else {
							if (isDate(currentYear, currentMonth + 1, dispDay)) {
								
								if(dispDay == currentCal.get(Calendar.DAY_OF_MONTH)
										&& currentCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
										&& currentCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) {
									todayColor = "class='todayColor'";
								} else {
									todayColor = "";
								}
			%>
					<td <%= todayColor %>><%= dispDay %></td>
			<%
								count += 1;
								dispDay += 1;
							} else {
			%>
					<td class="empty">&nbsp;</td>
			<%
							}
						}
					}
			%>
				</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>