package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class RequestParameterArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestParameter requestParameter = parameter.getAnnotation(RequestParameter.class);
		Class<?> parameterType = parameter.getType();
		return requestParameter != null && (ClassUtils.isPrimitiveOrWrapper(parameterType)) || String.class.equals(parameterType);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest request, HttpServletResponse response) {
		RequestParameter requestParameter = parameter.getAnnotation(RequestParameter.class);
		Class<?> parameterType = parameter.getType();
		String parameterName = requestParameter.name();
		String parameterValue = request.getParameter(parameterName);
		Object realParameter = null;
		if (requestParameter.required() && StringUtils.isBlank(parameterValue)) {
			throw new IllegalArgumentException(parameterName + " 은 필수 요청 파라미터입니다. 하지만 누락됐습니다.");
		} else {
			if (StringUtils.isBlank(parameterValue)) {
				parameterValue = requestParameter.defaultValue();
			}
			if (String.class.equals(parameterType)) {
				realParameter = parameterValue;
			} else if (byte.class.equals(parameterType) || Byte.class.equals(parameterType)) {
				realParameter = Byte.parseByte(parameterValue);
			} else if (short.class.equals(parameterType) || Short.class.equals(parameterType)) {
				realParameter = Short.parseShort(parameterValue);
			} else if (int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
				realParameter = Integer.parseInt(parameterValue);
			} else if (long.class.equals(parameterType) || Long.class.equals(parameterType)) {
				realParameter = Long.parseLong(parameterValue);
			} else if (float.class.equals(parameterType) || Float.class.equals(parameterType)) {
				realParameter = Float.parseFloat(parameterValue);
			} else if (double.class.equals(parameterType) || Double.class.equals(parameterType)) {
				realParameter = Double.parseDouble(parameterValue);
			} else if (boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
				realParameter = Boolean.parseBoolean(parameterValue);
			} else if (char.class.equals(parameterType) || Character.class.equals(parameterType)) {
				realParameter = parameterValue.charAt(0);
			} 

		}
		return realParameter;
	}
}