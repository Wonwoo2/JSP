package kr.or.ddit.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		if(StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		String goPage = null;
		boolean redirect = false;
		// HttpSession session = req.getSession(false);
		HttpSession session = req.getSession();
		
		if(session == null || session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "현재 요청이 최초 요청일 수 없음");
			return;
		}
		
		String msg = null;
		if(mem_id.equals(mem_pass)) {
			goPage = "/";
			redirect = true;
			session.setAttribute("authId", mem_id);
		} else {
			goPage = "/login/loginForm.jsp";
			msg = "비번 오류";
			req.setAttribute("msg", msg);
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}