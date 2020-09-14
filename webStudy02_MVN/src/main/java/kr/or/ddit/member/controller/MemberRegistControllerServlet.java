package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/registMember.do")
public class MemberRegistControllerServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/registForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> errors = new LinkedHashMap<String, Object>();
		MemberVO member = null;
		boolean valid = validation(req, errors, member);
		
		if (!valid) {
			resp.sendError((int) errors.get("statusCode"), (String) errors.get("msg"));
			return;
		}
		
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String mem_name = req.getParameter("mem_name");
		String mem_regno1 = req.getParameter("mem_regno1");
		String mem_regno2 = req.getParameter("mem_regno2");
		String mem_bir = req.getParameter("mem_bir");
		String mem_zip = req.getParameter("mem_zip");
		String mem_add1 = req.getParameter("mem_add1");
		String mem_add2 = req.getParameter("mem_add2");
		String mem_hometel = req.getParameter("mem_hometel");
		String mem_comtel = req.getParameter("mem_comtel");
		String mem_hp = req.getParameter("mem_hp");
		String mem_job = req.getParameter("mem_job");
		String mem_like = req.getParameter("mem_like");
		String mem_memorial = req.getParameter("mem_memorial");
		String mem_memorialday = req.getParameter("mem_memorialday");
		String mem_mileage = req.getParameter("mem_mileage");
		String mem_delete = req.getParameter("mem_delete");
		
		member = new MemberVO();
		member.setMem_id(mem_id);
		member.setMem_pass(mem_pass);
		member.setMem_name(mem_name);
		member.setMem_regno1(mem_regno1);
		member.setMem_regno2(mem_regno2);
		member.setMem_bir(mem_bir);
		member.setMem_zip(mem_zip);
		member.setMem_add1(mem_add1);
		member.setMem_add2(mem_add2);
		member.setMem_hometel(mem_hometel);
		member.setMem_comtel(mem_comtel);
		member.setMem_hp(mem_hp);
		member.setMem_job(mem_job);
		member.setMem_like(mem_like);
		member.setMem_memorial(mem_memorial);
		member.setMem_memorialday(mem_memorialday);
		member.setMem_mileage(Integer.parseInt(mem_mileage));
		member.setMem_delete(mem_delete);
		
		ServiceResult result = service.registMember(member);
		String goPage = null;
		boolean redirect = false;
		if (ServiceResult.OK == result) {
			redirect = true;
			goPage = "/";
		} else if (ServiceResult.FAILED == result) {
			req.setAttribute("authMember", member);
			goPage = "/registMember.do";
		} else if (ServiceResult.PKDUPLICATED == result) {
			redirect = true;
			req.setAttribute("authMember", member);
			goPage = "/registMember.do";
		}
		
		if (redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
	}

	public boolean validation(HttpServletRequest req, Map<String, Object> errors, MemberVO member) {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String mem_name = req.getParameter("mem_name");
		String mem_regno1 = req.getParameter("mem_regno1");
		String mem_regno2 = req.getParameter("mem_regno2");
		String mem_bir = req.getParameter("mem_bir");
		String mem_zip = req.getParameter("mem_zip");
		String mem_add1 = req.getParameter("mem_add1");
		String mem_add2 = req.getParameter("mem_add2");
		String mem_hometel = req.getParameter("mem_hometel");
		String mem_comtel = req.getParameter("mem_comtel");
		String mem_hp = req.getParameter("mem_hp");
		String mem_job = req.getParameter("mem_job");
		String mem_like = req.getParameter("mem_like");
		String mem_memorial = req.getParameter("mem_memorial");
		String mem_memorialday = req.getParameter("mem_memorialday");
		String mem_mileage = req.getParameter("mem_mileage");
		String mem_delete = req.getParameter("mem_delete");
		
		int statusCode = 200;
		if (StringUtils.isBlank(mem_id)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "아이디를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_pass)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "비밀번호를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_name)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "회원명를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_regno1)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주민번호1를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_regno2)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주민번호2를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_bir)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "생일를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_zip)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "우편번호를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_add1)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주소1를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_add2)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주소2를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_hometel)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "집전화번호를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_comtel)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "회사전화번호를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_hp)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "휴대폰번호를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_job)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "직업를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_like)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "취미를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_memorial)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "기념일를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_memorialday)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "기념일자를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_mileage)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "마일리지를 입력하지 않았습니다.");
			return false;
		}
		if (StringUtils.isBlank(mem_delete)) {
			statusCode = 405;
			errors.put("statusCode", statusCode);
			errors.put("msg", "탈퇴여부를 입력하지 않았습니다.");
			return false;
		}
		
		
		if (StringUtils.isBlank(mem_id)) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "아이디 길이가 초과입니다.");
		}
		
		if (mem_id.length() > 15) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "아이디 길이가 초과입니다.");
			return false;
		}
		if (mem_pass.length() > 15) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "비밀번호 길이가 초과입니다.");
			return false;
		}
		if (mem_name.length() > 20) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "이름 길이가 초과입니다.");
			return false;
		}
		if (mem_regno1.length() > 6) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주민번호1 길이가 초과입니다.");
			return false;
		}
		if (mem_regno2.length() > 7) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주민번호2 길이가 초과입니다.");
			return false;
		}
		if (mem_bir.length() > 7) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "생일 길이가 초과입니다.");
			return false;
		}
		if (mem_zip.length() > 7) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "우편번호 길이가 초과입니다.");
			return false;
		}
		if (mem_add1.length() > 100) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주소1 길이가 초과입니다.");
			return false;
		}
		if (mem_add2.length() > 80) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "주소2 길이가 초과입니다.");
			return false;
		}
		if (mem_hometel.length() > 14) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "집전화번호 길이가 짧습니다.");
			return false;
		}
		if (mem_comtel.length() > 14) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "회사 전화번호 길이가 짧습니다.");
			return false;
		}
		if (mem_hp.length() > 15) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "휴대폰 번호 길이가 짧습니다.");
			return false;
		}
		if (mem_job.length() > 40) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "직업 길이가 짧습니다.");
			return false;
		}
		if (mem_like.length() > 40) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "취미 길이가 초과입니다.");
			return false;
		}
		if (mem_memorial.length() > 40) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "기념일 길이가 초과입니다.");
			return false;
		}
		if (mem_memorialday.length() > 7) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "기념일 날짜 길이가 초과입니다.");
			return false;
		}
		if (mem_mileage.length() > 22) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "마일리지 길이가 초과입니다.");
			return false;
		}
		if (mem_delete.length() > 1) {
			statusCode = 400;
			errors.put("statusCode", statusCode);
			errors.put("msg", "탈퇴여부 길이가 초과입니다.");
			return false;
		}
		
		return true;
	}
}