package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProcessor implements IViewProcessor {
	
	private String prefix = "";
	private String suffix = "";

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/*
	 *  viewName이 "redirect:" 으로 시작되는 경우, redirection
	 */
	@Override
	public void viewProcess(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean redirect = viewName.startsWith("redirect:");
		if (redirect) {
			viewName = viewName.substring("redirect:".length());
			response.sendRedirect(request.getContextPath() +  viewName);
		} else {
			viewName = prefix + viewName + suffix;
			request.getRequestDispatcher(viewName).forward(request, response);
		}
	}
}