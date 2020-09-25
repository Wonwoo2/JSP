package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class LogoutServlet {

	@URIMapping(value = "/login/logout.do", method = HttpMethod.POST)
	public String doPost(HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		if(session == null || session.isNew()) {
			resp.sendError(400);
			return null;
		} else {
			session.invalidate();
			return "redirect:/";
		}
	}
}