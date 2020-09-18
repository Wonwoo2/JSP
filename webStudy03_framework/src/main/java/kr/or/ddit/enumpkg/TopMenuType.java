package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.MenuVO;

public enum TopMenuType {
	MemberMgmt(MenuVO.getBuilder().menuId("MEMBERMGMT").menuText("회원 관리").menuURI("/registMember.do").build());
	
	private MenuVO menuVo;

	private TopMenuType(MenuVO menuVo) {
		this.menuVo = menuVo;
	}
	
	public MenuVO getMenuVo() {
		return menuVo;
	}
}