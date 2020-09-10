package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.MenuVO;

@WebServlet("/index.do")
public class Model2PageModuleServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static enum ServiceType {
		CALCULATE(MenuVO.getBuilder().menuId("CALCULATE")
				.menuText("사칙연산자")
				.menuURI("/")
				.jspPath("/01/calForm.html").build()), 
		SESSIONTIMER(MenuVO.getBuilder().menuId("SESSIONTIMER")
					.menuText("세션타이머")
					.menuURI("/")
					.jspPath("/07/sessionTimer.jsp").build()),
		CALENDAR(MenuVO.getBuilder().menuId("CALENDAR")
				.menuText("달력")
				.menuURI("/")
				.jspPath("/07/calendar.jsp").build()),
		EXPLORER(MenuVO.getBuilder().menuId("EXPLORER")
				.menuText("서버탐색기")
				.menuURI("/serverExplorer.do").build()),
		STREAMING(MenuVO.getBuilder().menuId("STREAMING")
				.menuText("이미지뷰어")
				.menuURI("/image/imageList.do").build());
		
		private MenuVO menuVo;

		private ServiceType(MenuVO menuVo) {
			this.menuVo = menuVo;
		}
		
		public MenuVO getMenuVo() {
			return menuVo;
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String serviceParam = req.getParameter("service");
		
		int statusCode = HttpServletResponse.SC_OK;
		String includePage = "/WEB-INF/views/index.jsp";
		if(StringUtils.isNotBlank(serviceParam)) {
			try {
				ServiceType serviceType = ServiceType.valueOf(serviceParam);
				MenuVO menuVo = serviceType.getMenuVo();
				includePage = menuVo.getJspPath();
			} catch (IllegalArgumentException e) {
				statusCode = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		req.setAttribute("includePage", includePage);
		
		if(statusCode == HttpServletResponse.SC_OK) {
			req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
		} else {
			resp.sendError(statusCode, "해당 페이지는 존재하지 않습니다.");
		}
	}
}