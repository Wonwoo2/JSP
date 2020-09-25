package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.service.AuthenticateServiceImpl;
import kr.or.ddit.commons.service.IAuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.MemberVO;



@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IAuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청 파라미터 확보
//		
//		2. 데이터 검증
//			- 통과 : 3번 처리
//			- 불통 : 400 에러 발생
//		
//		3. 로그인 인증 처리(입력한 아이디와 비밀번호가 동일하면 성공)
//			- 인증 성공 : welcome page로 이동(redirect)
//				index.jsp 생성 (authID 라는 속성으로 현재 로그인한 유저의 아이디를 출력)
//			- 인증 실패(비밀번호 오류 간주) : loginForm.jsp 로 이동 (동일한 아이디를 다시 입력하지 않도록)

		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String saveId = req.getParameter("saveId");

		if (StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		HttpSession session = req.getSession();
		
		if(session == null || session.isNew()) {
			resp.sendError(404, "잘못된 요청입니다.");
			return;
		}
		
		
		String goPage = null;
		boolean redirect = false;

		String msg = null;
		int maxAge = 0;
		Object result = service.authenticate(MemberVO.builder().mem_id(mem_id).mem_pass(mem_pass).build());
		
		if (result instanceof MemberVO) {
			goPage = "/";
			
			session.setAttribute("loginMember", result);
			
			if("save".equals(saveId)) {
				maxAge = 60 * 60 * 24 * 7;
			}
			
			Cookie idCookie = CookieUtils.createCookie("idCookie", mem_id, req.getContextPath(), maxAge, TextType.PATH);
			resp.addCookie(idCookie);
		} else {
			goPage = "/login/loginForm.jsp";
			redirect = true;
			
			if (ServiceResult.NOTEXIST == result) {
				msg = mem_id + "에 해당하는 회원이 없습니다.";
			} else {
				msg = "비밀번호가 틀렸습니다.";
			}
			session.setAttribute("msg", msg);
		}
		
		if (redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}