package kr.or.ddit.calendarUtil;

import java.util.Locale;

@FunctionalInterface
public interface WeekConverter {
	public String weekConvert(Locale locale);
}
