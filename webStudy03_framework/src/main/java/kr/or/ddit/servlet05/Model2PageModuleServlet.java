package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceType;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MenuVO;

@CommandHandler
public class Model2PageModuleServlet {
	
	@URIMapping(value = "/", method = HttpMethod.GET)
	public String getService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String serviceParam = req.getParameter("service");
		String goPage = requestProcessor(serviceParam, req, resp);
		return goPage;
	}
	
	@URIMapping(value = "/", method = HttpMethod.POST)
	public String postService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String serviceParam = req.getParameter("service");
		String goPage = requestProcessor(serviceParam, req, resp);
		return goPage;
	}
	
	public String requestProcessor(String serviceParam, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int statusCode = HttpServletResponse.SC_OK;
		String includePage = "/WEB-INF/views/index.jsp";
		
		if (StringUtils.isNotBlank(serviceParam)) {
			try {
				ServiceType serviceType = ServiceType.valueOf(serviceParam);
				MenuVO menuVo = serviceType.getMenuVo();
				includePage = menuVo.getJspPath();
			} catch (IllegalArgumentException e) {
				statusCode = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		
		req.setAttribute("includePage", includePage);
		String goPage = "layout";
		if(statusCode != HttpServletResponse.SC_OK) {
			resp.sendError(statusCode, "해당 페이지는 존재하지 않습니다.");
			return null;
		}
		return goPage;
	}
}