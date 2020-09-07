package kr.or.ddit.calendarUtil;

import java.util.Calendar;

public class CalendarUtil {
	Calendar cal = Calendar.getInstance();
	
	private String[] calHeader = {"일","월","화","수","목","금","토"};
	
    private String[][] calDate = new String[6][7];
	
    private int width=calHeader.length; // 배열 가로 넓이
    private int startDay;   // 월 시작 요일
    private int lastDay;    // 월 마지막 날짜
    private int inputDate = 1 ;  // 입력 날짜
	
    public CalendarUtil(int year, int month) throws Exception {
    	if(month < 1 || month > 12) {
            throw new Exception();
        } else {
			// Calendar에 년,월,일 셋팅
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);
			cal.set(Calendar.DATE, 1);

			startDay = cal.get(Calendar.DAY_OF_WEEK); // 월 시작 요일
			lastDay = cal.getActualMaximum(Calendar.DATE); // 월 마지막 날짜

			// 2차 배열에 날짜 입력
			int row = 0;
			for (int i = 1; inputDate <= lastDay; i++) {

				// 시작 요일이 오기전에는 공백 대입
				if (i < startDay)
					calDate[row][i - 1] = "";
				else {
					// 날짜 배열에 입력
					calDate[row][(i - 1) % width] = Integer.toString(inputDate);
					inputDate++;

					// 가로 마지막 열에 오면 행 바꿈
					if (i % width == 0)
						row++;
				}
			}
		}
	}
}