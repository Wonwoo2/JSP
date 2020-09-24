package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.filter.wrapper.FileUploadRequestWrapper;

public class FileUploadCheckFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(BlindFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 객체 생성및 초기화 완료", getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 파일이 포함된 요청 식별
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		if (contentType != null && contentType.startsWith("multipart/")) {
			// 파일이 포함된 요청이 PartWrapper를 가지도록 - RequestWrapper
			FileUploadRequestWrapper wrapper = new FileUploadRequestWrapper(req);
			
			chain.doFilter(wrapper, response);
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{} 객체 소멸", getClass().getName());
	}
}