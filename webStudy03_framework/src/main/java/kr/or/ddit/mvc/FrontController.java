package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

public class FrontController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basePackage = config.getInitParameter("basePackage");
		handlerMapper = new HandlerMapper(basePackage);
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcessor();
		
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		viewProcessor.setPrefix(prefix);
		viewProcessor.setSuffix(suffix);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		URIMappingInfo mappingInfo = handlerMapper.findCommandHandler(req);
		if (mappingInfo == null) {
			String tmpURI = req.getRequestURI();
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, tmpURI + "는 제공하지 않는 서비스입니다.");
			return;
		}
		String viewName = handlerInvoker.invokeHandler(mappingInfo, req, resp);
		if (viewName != null) {
			viewProcessor.viewProcess(viewName, req, resp);
		} else {
			if (!resp.isCommitted()) {
				resp.sendError(500, "뷰네임이 누락되었음");
			}
		}
	}
}