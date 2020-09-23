package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 *  각 커맨드를 처리할 수 있는 핸들러 메소드와 1:1 구조
 */
public class URIMappingInfo {
	private URIMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public URIMappingInfo(URIMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
	
	public URIMappingCondition getMappingCondition() {
		return mappingCondition;
	}
	
	public Object getCommandHandler() {
		return commandHandler;
	}
	
	public Method getHandlerMethod() {
		return handlerMethod;
	}

	@Override
	public String toString() {
		return String.format("%s : %s.%s", mappingCondition, commandHandler.getClass().getName(), handlerMethod.getName());
	}
}