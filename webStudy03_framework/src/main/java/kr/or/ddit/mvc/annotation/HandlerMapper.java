package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ReflectionUtils;

/**
 *  1. 어플리케이션 내의 커맨드 핸들러들에 대한 정보 수집 -> handlerMap을 만듦
 *  2. 요청이 발생하면, 해당 요청을 처리할 수 있는 핸들러 검색
 */
public class HandlerMapper implements IHandlerMapper {
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class);
	
	private Map<URIMappingCondition, URIMappingInfo> handlerMap;
	
	public HandlerMapper(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		Map<Class<?>, Annotation> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(CommandHandler.class, basePackages);
		for (Entry<Class<?>, Annotation> entry : classes.entrySet()) {
			Class<?> handlerType = entry.getKey();
			Object commandHandler = null;
			try {
				commandHandler = handlerType.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			Map<Method, Annotation> methods = 
					ReflectionUtils.getMethodsWithAnnotationAtClass(handlerType, URIMapping.class, String.class);
			for (Entry<Method, Annotation> mtdEntry : methods.entrySet()) {
				Method handlerMethod = mtdEntry.getKey();
				URIMapping mapping = (URIMapping) mtdEntry.getValue();
				String url = mapping.value();
				HttpMethod httpMethod = mapping.method();
				
				URIMappingCondition mappingCondition = new URIMappingCondition(url, httpMethod);
				URIMappingInfo mappingInfo = new URIMappingInfo(mappingCondition, commandHandler, handlerMethod);
				handlerMap.put(mappingCondition, mappingInfo);
				logger.info("핸들러 정보 : {}", mappingInfo);
			}
		}
	}


	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest request) {
		String uri = getMappingURL(request);
		String method = request.getMethod();
		HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
		URIMappingCondition condition = new URIMappingCondition(uri, httpMethod);
		return handlerMap.get(condition);
	}

	private String getMappingURL(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int cpLength = request.getContextPath().length();		// contextPath 버리기, 세션 파라미터 버리기
		uri = uri.substring(cpLength);
		return uri.split(";")[0];
	}
}