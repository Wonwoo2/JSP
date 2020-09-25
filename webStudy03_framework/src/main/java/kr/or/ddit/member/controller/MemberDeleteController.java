package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberDeleteController {
	
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value = "/memberDelete.do", method = HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpSession session) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) session.getAttribute("loginMember");
		MemberVO member = service.retrieveMember(authMember.getMem_id());
		
		req.setAttribute("deleteMember", member);
		
		String goPage = "member/deleteForm";
		
		return goPage;
	}
	
	@URIMapping(value = "/memberDelete.do", method = HttpMethod.POST)
	public String doPost(@ModelData(name = "member") MemberVO member, HttpServletRequest req) throws ServletException, IOException {
		Map<String, StringBuffer> errors = new LinkedHashMap<String, StringBuffer>();
		req.setAttribute("errors", errors);
		
		boolean valid = true;
		
		String goPage = null;
		String message = null;
		
		if (valid) {
			ServiceResult result = service.removeMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "member/deleteForm";
				message = "비밀번호 오류입니다.";
				break;
			case FAILED:
				goPage = "member/deleteForm";
				message = "서버 문제로 삭제가 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				goPage = "redirect:/login/loginForm.jsp";
				req.getSession().removeAttribute("authMember");
				break;
			}
		} else {
			goPage = "member/deleteForm";
		}
		
		req.setAttribute("message", message);
		
		return goPage;
	}
}