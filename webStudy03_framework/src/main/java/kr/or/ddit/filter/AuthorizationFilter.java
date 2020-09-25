package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

/**
 *  보호 차원에 대한 접근 권한 소유여부 확인
 */
//@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	private Map<String, String[]> secured;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		secured = new LinkedHashMap<>();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		secured = (Map<String, String[]>) req.getServletContext().getAttribute("secured");
		String uri = (String) req.getAttribute("uri");
		
		boolean pass = true;
		if (secured.containsKey(uri)) {
			// 보호 자원 요청
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO) session.getAttribute("member");
			String userRole = member.getMem_role();
			String[] roles = secured.get(uri);
			
			int result = Arrays.binarySearch(roles, userRole);
			if (result < 0) {
				pass = false;
			}
		}
		
		if (pass) {
			chain.doFilter(request, response);
		} else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@Override
	public void destroy() {
		
	}
}