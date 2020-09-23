package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberUpdateController {
	
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value = "/MemberUpdate.do", method = HttpMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) session.getAttribute("member");
		MemberVO member = service.retrieveMember(authMember.getMem_id());
		
		req.setAttribute("member", member);
		
		String goPage = "member/modifyForm";
		
		return goPage;
	}
	
	@URIMapping(value = "/MemberUpdate.do", method = HttpMethod.POST)
	public String doPost(@ModelData(name = "member") MemberVO member, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, StringBuffer> errors = new LinkedHashMap<String, StringBuffer>();
		req.setAttribute("errors", errors);
		
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(member, errors, UpdateGroup.class);
		
		String goPage = null;
		String message = null;
		
		if (valid) {
			ServiceResult result = service.modifyMember(member);
			
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "member/modifyForm";
				message = "비밀번호 오류입니다.";
				break;
			case FAILED:
				goPage = "member/modifyForm";
				message = "서버 문제로 수정이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				goPage = "redirect:/mypage.do";
				break;
			}
		} else {
			goPage = "member/modifyForm";
		}
		
		req.setAttribute("message", message);
		
		return goPage;
	}
}