package kr.or.ddit.calendarUtil;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public enum Week {
	SUNDAY(1, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);
			String weeDay = format.format(today);
			return weeDay;
		}
	}), 
	MONDAY(2, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);

			String weeDay = format.format(today);
			return weeDay;
		}
		
	}), 
	TUESDAY(3, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);

			String weeDay = format.format(today);
			return weeDay;
		}
	}), 
	WEDNESDAY(4, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);

			String weeDay = format.format(today);
			return weeDay;
		}
	}), 
	THURSDAY(5, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);

			String weeDay = format.format(today);
			return weeDay;
		}
		
	}), 
	FRIDAY(6, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);

			String weeDay = format.format(today);
			return weeDay;
		}
	}), 
	SATURDAY(7, new WeekConverter() {
		@Override
		public String weekConvert(Locale locale) {
			Date today = new Date();
			DateFormatSymbols dfs = new DateFormatSymbols(locale);
			SimpleDateFormat format = new SimpleDateFormat("E", dfs);

			String weeDay = format.format(today);
			return weeDay;
		}
	});
	
	private int weekDay;
	private WeekConverter weekConverter;
	
	private Week(int weekDay, WeekConverter weekConverter) {
		this.weekDay = weekDay;
		this.weekConverter = weekConverter;
	}
	
	public int getWeekDay() {
		return weekDay;
	}
	
	public String getConvertWeek(Locale locale) {
		return weekConverter.weekConvert(locale);
	}
}