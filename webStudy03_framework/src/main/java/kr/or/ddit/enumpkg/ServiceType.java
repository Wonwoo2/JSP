package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.MenuVO;

public enum ServiceType {
	CALCULATE(MenuVO.getBuilder().menuId("CALCULATE").menuText("사칙연산자").menuURI("/").jspPath("/01/calForm.html").build()),
	SESSIONTIMER(MenuVO.getBuilder().menuId("SESSIONTIMER").menuText("세션타이머").menuURI("/")	.jspPath("/07/sessionTimer.jsp").build()),
	CALENDAR(MenuVO.getBuilder().menuId("CALENDAR").menuText("달력").menuURI("/").jspPath("/07/calendar.jsp").build()),
	EXPLORER(MenuVO.getBuilder().menuId("EXPLORER").menuText("서버탐색기").menuURI("/serverExplorer.do").build()),
	STREAMING(MenuVO.getBuilder().menuId("STREAMING").menuText("이미지뷰어").menuURI("/image/imageList.do").build());

	private MenuVO menuVo;

	private ServiceType(MenuVO menuVo) {
		this.menuVo = menuVo;
	}

	public MenuVO getMenuVo() {
		return menuVo;
	}
}
