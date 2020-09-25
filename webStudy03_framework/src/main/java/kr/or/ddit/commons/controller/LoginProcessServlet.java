package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class LoginProcessServlet {

	private IAuthenticateService service = new AuthenticateServiceImpl();
	
	@URIMapping(value = "/login/loginProcess.do", method = HttpMethod.POST)
	public String doPost(@RequestParameter(name = "mem_id", required = true) String mem_id,
						@RequestParameter(name = "mem_pass", required = true) String mem_pass,
						@RequestParameter(name = "saveId", required = false, defaultValue = "") String saveId,
						HttpServletRequest req, HttpServletResponse resp,
						HttpSession session) throws ServletException, IOException {
		
		if(session == null || session.isNew()) {
			resp.sendError(404, "잘못된 요청입니다.");
			return null;
		}
		
		String goPage = null;

		String msg = null;
		int maxAge = 0;
		Object result = service.authenticate(MemberVO.builder().mem_id(mem_id).mem_pass(mem_pass).build());
		
		if (result instanceof MemberVO) {
			goPage = "redirect:/";
			
			session.setAttribute("loginMember", result);
			
			if("save".equals(saveId)) {
				maxAge = 60 * 60 * 24 * 7;
			}
			
			Cookie idCookie = CookieUtils.createCookie("idCookie", mem_id, req.getContextPath(), maxAge, TextType.PATH);
			resp.addCookie(idCookie);
		} else {
			goPage = "redirect:/login/loginForm.jsp";
			
			if (ServiceResult.NOTEXIST == result) {
				msg = mem_id + "에 해당하는 회원이 없습니다.";
			} else {
				msg = "비밀번호가 틀렸습니다.";
			}
			session.setAttribute("msg", msg);
		}
		
		return goPage;
	}
}