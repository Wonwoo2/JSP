package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  PlainOldJavaObject 형태로 구현된 핸들러를 reflection으로 호출함
 */
public interface IHandlerInvoker {
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}