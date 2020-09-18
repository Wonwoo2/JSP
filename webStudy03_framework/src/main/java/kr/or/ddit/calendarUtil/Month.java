package kr.or.ddit.calendarUtil;

public enum Month {
	JANUARY("January", 0), FEBRUARY("February", 1), MARCH("March", 2), APRIL("April", 3), MAY("May", 4), JUNE("June", 5), JULY("July", 6),
	AUGUST("Agust", 7), SEPTEMBER("September", 8), OCTOBER("October", 9), NOVEMBER("November", 10), DECEMBER("December", 11);

	private String monthName;
	private int month;

	private Month(String monthName, int month) {
		this.monthName = monthName;
	}
	
	public String getMonthName() {
		return monthName;
	}
	
	public int getMonth() {
		return month;
	}
}