package kr.or.ddit.vo;

import java.io.Serializable;

/**
 *  1. 객체 생성에 사용할 Builder 패턴
 *  	1) 생성자 패턴(점층적인 생성자 패턴)
 *  	2) JavaBean 패턴, 생성자와 setter 활용
 *  		MenuVO menu = new MenuVO();
 *  		menu.setMenuId("id");
 *  	3) Builder 패턴 : 객체의 생성을  Builder 에게 위임
 *  		- 생성자를 캡슐화(이 객체를 직접 생성할 수 없다는 의미)
 *  		- 모든 property를 파라미터로 받는 생성자 정의
 *  		- Builder 정의
 *  		
 */
public class MenuVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String menuId;
	private String menuText;
	private String menuURI;
	private String jspPath; // view 경로

	// 생성자 캡슐화 (private)
	private MenuVO() {
		super();
	}

	// 모든 property를 파라미터로 받는 생성자 정의
	private MenuVO(String menuId, String menuText, String menuURI, String jspPath) {
		super();
		this.menuId = menuId;
		this.menuText = menuText;
		this.menuURI = menuURI;
		this.jspPath = jspPath;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVO other = (MenuVO) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		return true;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuURI() {
		return menuURI;
	}

	public void setMenuURI(String menuURI) {
		this.menuURI = menuURI;
	}

	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}
	
	public static class MenuVOBuilder {
		private String menuId;
		private String menuText;
		private String menuURI;
		private String jspPath;
		
		public MenuVOBuilder menuId(String menuId) {
			this.menuId = menuId;
			return this;
		}
		
		public MenuVOBuilder menuText(String menuText) {
			this.menuText = menuText;
			return this;
		}
		
		public MenuVOBuilder menuURI(String menuURI) {
			this.menuURI = menuURI;
			return this;
		}
		
		public MenuVOBuilder jspPath(String jspPath) {
			this.jspPath = jspPath;
			return this;
		}
		
		public MenuVO build() {
			return new MenuVO(menuId, menuText, menuURI, jspPath);
		}
	}
	
	public static MenuVOBuilder getBuilder() {
		return new MenuVOBuilder();
	}
}