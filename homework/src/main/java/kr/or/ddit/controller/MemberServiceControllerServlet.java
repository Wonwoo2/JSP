package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/MemberService.do")
public class MemberServiceControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IMemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String serviceParam = req.getParameter("service");
		String userid = req.getParameter("userid");
		String accept = req.getHeader("Accept");
		
		if (StringUtils.isBlank(serviceParam)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
			return;
		}
		
		MemberVO memVo = null;
		int result = -1;
		String mime = "text/html;charset=UTF-8";
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			mime = "application/json;charset=UTF-8";
		}
		
		resp.setContentType(mime);
		if ("select".equals(serviceParam)) {
			if (StringUtils.isBlank(userid)) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
				return;
			}
			
			try (
					PrintWriter out = resp.getWriter();
			) {
				memVo = service.getMember(userid);
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, memVo);
			}
		} else if ("delete".equals(serviceParam)) {
			result = service.deleteMember(userid);
			
			if(result <= 0) {
				userid = null;
			}
			try (
					PrintWriter out = resp.getWriter();
			) {
				out.print(userid);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String serviceParam = req.getParameter("service");
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("usernm");
		String pass = req.getParameter("pass");
		String passCheck = req.getParameter("passCheck");
		String alias = req.getParameter("alias");
		String zipcode = req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String filename = req.getParameter("filename");
		String realfilename = req.getParameter("realfilename");
		
		if (StringUtils.isBlank(serviceParam)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
			return;
		}
		
		int result = -1;
		boolean valid = true;
		Map<String, String> paramMap = new LinkedHashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("usernm", usernm);
		paramMap.put("pass", pass);
		paramMap.put("alias", alias);
		paramMap.put("addr1", addr1);
		paramMap.put("addr2", addr2);
		
		String url = null;
		if ("insert".equals(serviceParam)) {
			paramMap.put("passCheck", passCheck);
			
			valid = validation(paramMap, serviceParam);
			if(valid) {
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
				String todayStr = sdf.format(today);
				MemberVO memVo = MemberVO.getBuilder()
											.userid(userid)
											.usernm(usernm)
											.pass(pass)
											.alias(alias)
											.reg_dt(todayStr)
											.zipcode(zipcode)
											.addr1(addr1)
											.addr2(addr2).build();
				result = service.registMember(memVo);
				if (result > 0) {
					url = "/MemberMgmt.do";
				} else {
					url = "/WEB-INF/MemberInsert.jsp";
				}
			}
		} else if ("update".equals(serviceParam)) {
			paramMap.put("filename", filename);
			paramMap.put("realfilename", realfilename);
			
			valid = validation(paramMap, serviceParam);
			MemberVO memVo = MemberVO.getBuilder()
					.userid(userid)
					.usernm(usernm)
					.pass(pass)
					.alias(alias)
					.zipcode(zipcode)
					.addr1(addr1)
					.addr2(addr2)
					.filename(filename)
					.realfilename(realfilename).build();
			result = service.editMember(memVo);
			
			if (result > 0) {
				url = "/MemberMgmt.do";
			} else {
				url = "/WEB-INF/MemberUpdate.jsp";
			}
		}
		
		if(result > 0) {
			resp.sendRedirect(url);
		} else {
			req.getRequestDispatcher(url);
		}
	}

	private boolean validation(Map<String, String> paramMap, String serviceParam) {
		boolean valid = true;
		Set<String> keySet = paramMap.keySet();
		
		for (String key : keySet) {
			if(StringUtils.isBlank(paramMap.get(key))) {
				valid = false;
				break;
			}
		}
		
		if ("insert".equals(serviceParam)) {
			String pass = paramMap.get("pass");
			String passCheck = paramMap.get("passCheck");
			
			if (StringUtils.isNotBlank(pass) && StringUtils.isNotBlank(passCheck) && pass.equals(passCheck)) {
				valid = true;
			} else {
				valid = false;
			}
		}
		return valid;
	}
}