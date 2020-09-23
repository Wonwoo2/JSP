package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface IHandlerMapper {
	public URIMappingInfo findCommandHandler(HttpServletRequest request);
}
