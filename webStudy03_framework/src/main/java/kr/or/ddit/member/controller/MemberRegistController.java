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
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/registMember.do")
public class MemberRegistController extends HttpServlet {

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
		// 1. 요청 분석
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		req.setCharacterEncoding("UTF-8");
		Map<String, String[]> paramMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, paramMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// 2. 검증(DB 스키마 구조 참고)
		Map<String, StringBuffer> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(member, errors, InsertGroup.class);
		
		
		// 3. 통과
		// 4. 통과한 경우, 로직을 이용한 등록
		String msg = null;
		String goPage = null;
		boolean redirect = false;
		if (valid) {
			ServiceResult result = service.registMember(member);
			
			switch (result) {
			case PKDUPLICATED:
				goPage = "/WEB-INF/views/member/registForm.jsp";
				msg = "아이디 중복, 확인 후 다시 넣으세요.";
				break;
			case FAILED:
				goPage = "/WEB-INF/views/member/registForm.jsp";
				msg = "서버 문제로 등록이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				goPage = "/login/loginForm.jsp";
				redirect = true;
				break;
			}
		} else {
			goPage = "/WEB-INF/views/member/registForm.jsp";
		}
		
		req.setAttribute("msg", msg);
		if (redirect) {
			resp.sendRedirect(req.getContextPath() +  goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
		/*
		 * -- reflection 코드 --
		 * 
		 * for (Entry<String, String[]> entry : paramMap.entrySet()) { String paramName
		 * = entry.getKey(); String[] paramValue = entry.getValue();
		 * 
		 * Class<?> clzType = member.getClass(); try { Field field =
		 * clzType.getDeclaredField(paramName); field.setAccessible(true);
		 * 
		 * Class<?> fieldType = field.getType(); if
		 * (String.class.isAssignableFrom(fieldType)) { field.set(member,
		 * paramValue[0]); } else if (Number.class.isAssignableFrom(fieldType)) { Number
		 * number = (Number) fieldType.newInstance(); Method method =
		 * fieldType.getDeclaredMethod("parseInt", String.class); number = (Number)
		 * method.invoke(number, paramValue[0]); field.set(member, number); }
		 * 
		 * } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
		 * | IllegalAccessException | InstantiationException | InvocationTargetException
		 * | NoSuchMethodException e) { continue; } }
		 */

	}

//	public boolean validation(MemberVO member, Map<String, StringBuffer> errors) {
//		// 타입, 필수 여부, 길이, 형식
//		boolean valid = true;
//		if (StringUtils.isBlank(member.getMem_id())) {
//			valid = false;
//			errors.put("mem_id", new StringBuffer("아이디 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_pass())) {
//			valid = false;
//			errors.put("mem_pass", new StringBuffer("비밀번호 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_name())) {
//			valid = false;
//			errors.put("mem_name", new StringBuffer("회원명 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_regno1())) {
//			valid = false;
//			errors.put("mem_regno1", new StringBuffer("주민번호1 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_regno2())) {
//			valid = false;
//			errors.put("mem_regno2", new StringBuffer("주민번호2 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_zip())) {
//			valid = false;
//			errors.put("mem_zip", new StringBuffer("우편번호 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_add1())) {
//			valid = false;
//			errors.put("mem_add1", new StringBuffer("주소1 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_add2())) {
//			valid = false;
//			errors.put("mem_add2", new StringBuffer("주소2 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_hometel())) {
//			valid = false;
//			errors.put("mem_hometel", new StringBuffer("집전화번호 필수데이터 누락"));
//		}
//		if (StringUtils.isBlank(member.getMem_comtel())) {
//			valid = false;
//			errors.put("mem_comtel", new StringBuffer("회사전화번호 필수데이터 누락"));
//		}
//		return valid;
//	}
}