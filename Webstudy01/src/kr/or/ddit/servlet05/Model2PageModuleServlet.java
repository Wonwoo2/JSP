package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.MenuVO;

@WebServlet("/model2/layoutPage.do")
public class Model2PageModuleServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static enum ServiceType {
		CALCULATE(new MenuVO("CALCULATE", "사칙연산기", "/model2/layoutPage.do", "/01/calForm.html")), 
		SESSIONTIMER(new MenuVO("SESSIONTIMER", "세션타이머", "/model2/layoutPage.do", "/07/sessionTimer.jsp")),
		CALENDAR(new MenuVO("CALENDAR", "달력", "/model2/layoutPage.do", "/07/calendar.jsp")),
		EXPLORER(new MenuVO("EXPLORER", "서버탐색기", "/serverExplorer.do", null)),
		STREAMING(new MenuVO("STREAMING", "이미지뷰어", "/image/imageList.do", null));
		
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
		if(StringUtils.isNotBlank(serviceParam)) {
			try {
				ServiceType serviceType = ServiceType.valueOf(serviceParam);
				MenuVO menuVo = serviceType.getMenuVo();
				req.setAttribute("includePage", menuVo.getJspPath());
			} catch (IllegalArgumentException e) {
				statusCode = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
			req.getRequestDispatcher("/WEB-INF/views/layout.jsp").forward(req, resp);
		} else {
			resp.sendError(statusCode, "해당 페이지는 존재하지 않습니다.");
		}
	}
}