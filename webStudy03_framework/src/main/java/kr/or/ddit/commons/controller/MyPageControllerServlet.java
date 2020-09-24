package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MyPageControllerServlet {
	
	@URIMapping(value = "/mypage.do", method = HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		MemberVO member = (MemberVO) session.getAttribute("member");
		String goPage = null;
		req.setAttribute("member", member);
		goPage = "member/mypage";
		return goPage;
	}
}