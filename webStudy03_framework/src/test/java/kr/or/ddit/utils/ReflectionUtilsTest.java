package kr.or.ddit.utils;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.member.dao.MemberControllerPsedo;
import kr.or.ddit.member.dao.MemberDAOImplTest;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

public class ReflectionUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMethodsWithAnnotationAtClass() {
		Map<Method, Annotation> methods = 
				ReflectionUtils.getMethodsWithAnnotationAtClass(MemberControllerPsedo.class, URIMapping.class, String.class, HttpServletRequest.class, HttpServletResponse.class);
		for (Entry<Method, Annotation> entry : methods.entrySet()) {
			System.out.println(entry.getKey().getName());
			System.out.println(((URIMapping) entry.getValue()).value());
			System.out.println(((URIMapping) entry.getValue()).method());
		}
		
	}

//	@Test
	public void testGetMethodsAtClass() {
		List<Method> methods = ReflectionUtils.getMethodsAtClass(MemberDAOImplTest.class, void.class);
		System.out.println(methods);
	}

//	@Test
	public void testGetClassesWithAnnotationAtBasePackages() {
		Map<Class<?>, Annotation> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(CommandHandler.class, "kr.or.ddit.member");
		for (Entry<Class<?>, Annotation> entry : classes.entrySet()) {
			System.out.printf("%s : %s", entry.getKey().getName(), entry.getValue().toString());
		}
	}

//	@Test
	public void testGetAllClassesAtBasePackages() {
		List<Class<?>> classes = ReflectionUtils.getAllClassesAtBasePackages("kr.or.ddit.member");
		
		for (Class<?> tmp : classes) {
			System.out.println(tmp.getName());
		}
	}

}
