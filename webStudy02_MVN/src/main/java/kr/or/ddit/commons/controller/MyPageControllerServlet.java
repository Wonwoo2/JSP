package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MyPageControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String goPage = null;
		boolean redirect = false;
		if (authMember == null) {
			goPage = "/login/loginForm.jsp";
			redirect = true;
		} else {
			req.setAttribute("authMember", authMember);
			goPage = "/WEB-INF/views/member/mypage.jsp";
		}
		
		if (redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}