package kr.or.ddit.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  클라이언트의 명령을 식별하기 위한 조건 설정
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface URIMapping {
	String value();		// URI 조건
	HttpMethod method() default HttpMethod.GET;
}