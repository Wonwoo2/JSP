package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/MemberDelete.do")
public class MemberDeleteController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMem_id());
		
		req.setAttribute("member", member);
		
		String goPage = "/WEB-INF/views/member/deleteForm.jsp";
		
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> paramMap = req.getParameterMap();
		
		try {
			BeanUtils.populate(member, paramMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		Map<String, StringBuffer> errors = new LinkedHashMap<String, StringBuffer>();
		req.setAttribute("errors", errors);
		
		boolean valid = true;
		
		String goPage = null;
		boolean redirect = false; 
		String message = null;
		
		if (valid) {
			ServiceResult result = service.removeMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "/WEB-INF/view/member/deleteForm.jsp";
				message = "비밀번호 오류입니다.";
				break;
			case FAILED:
				goPage = "/WEB-INF/view/member/deleteForm.jsp";
				message = "서버 문제로 수정이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				goPage = "/login/loginForm.jsp";
				req.getSession().removeAttribute("authMember");
				redirect = true;
				break;
			}
		} else {
			goPage = "/WEB-INF/views/member/deleteForm.jsp";
		}
		
		req.setAttribute("message", message);
		
		if (redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}