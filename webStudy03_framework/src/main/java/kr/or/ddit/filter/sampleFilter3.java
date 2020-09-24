package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  요청에 대한 전처리와 응답에 대한 후처리를 담당하는 객체
 *  Decoration Filter Pattern 으로 보안처리(인증/인가를 바탕으로한 접근제어), 파일업로드 처리
 */
public class sampleFilter3 implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(sampleFilter3.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 객체 생성 및 초기화 완료", getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청 전처리
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{} 요청 전처리3", req.getRequestURI());
		chain.doFilter(request, response);		// 다음 필터나 최종 자원 쪽으로 제어가 이동
		// 응답 전처리
		HttpServletResponse resp = (HttpServletResponse) response;
		logger.info("{} 응답 후처리3, 남은 버퍼의 용량 {}", resp.getBufferSize());
	}

	@Override
	public void destroy() {
		logger.info("{} 객체 소멸", getClass().getName());
	}
}