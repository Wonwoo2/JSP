package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

public class ModelDataArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		ModelData modelData = parameter.getAnnotation(ModelData.class);
		Class<?> parameterType = parameter.getType();
		return modelData != null && !(ClassUtils.isPrimitiveOrWrapper(parameterType) || String.class.equals(parameterType));
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest request, HttpServletResponse response) {
		ModelData modelData = parameter.getAnnotation(ModelData.class);
		Class<?> parameterType = parameter.getType();
		try {
			Object realParameter = parameterType.newInstance();
			request.setAttribute(modelData.name(), realParameter);
			Map<String, String[]> parameterMap = request.getParameterMap();
			BeanUtils.populate(realParameter, parameterMap);
			return realParameter;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}