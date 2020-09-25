package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;
import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelData;
import kr.or.ddit.mvc.annotation.resolvers.RequestParameter;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberRegistController {

	private IMemberService service = MemberServiceImpl.getInstance();

	@URIMapping(value = "/registMember.do", method = HttpMethod.GET)
	public String doGet(HttpServletRequest req) throws ServletException, IOException {
		String goPage = "member/registForm";
		return goPage;
	}

	@URIMapping(value = "/registMember.do", method = HttpMethod.POST)
	public String doPost(@ModelData(name = "registMember") MemberVO member, HttpServletRequest req) throws ServletException, IOException {
		if (req instanceof FileUploadRequestWrapper) {
			PartWrapper partWrapper = ((FileUploadRequestWrapper) req).getPartWrapper("mem_image");
			if (partWrapper != null) {
				member.setMem_image(partWrapper);
			}
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
		if (valid) {
			ServiceResult result = service.registMember(member);
			
			switch (result) {
			case PKDUPLICATED:
				goPage = "member/registForm";
				msg = "아이디 중복, 확인 후 다시 넣으세요.";
				break;
			case FAILED:
				goPage = "member/registForm";
				msg = "서버 문제로 등록이 완료되지 않았습니다. 잠시 후 다시 시도해주세요.";
				break;
			default:
				goPage = "redirect:/login/loginForm.jsp";
				break;
			}
		} else {
			goPage = "member/registForm";
		}
		req.setAttribute("msg", msg);
		return goPage;
		
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
	
	@URIMapping(value = "/idCheck.do", method = HttpMethod.POST)
	public String idCheck(@RequestParameter(name = "mem_id") String mem_id, 
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boolean validID = false;
		try {
			service.retrieveMember(mem_id);
		} catch (CustomException e) {
			validID = true;
		}
		Map<String, Object> resultMap = new LinkedHashMap<>();
		resultMap.put("validID", validID);
		
		if (validID) {
			resultMap.put("mem_id", mem_id);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try (
				PrintWriter out = resp.getWriter();
		) {
			mapper.writeValue(out, resultMap);
		}
		return null;
	}
	
}