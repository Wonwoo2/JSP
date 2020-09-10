package kr.or.ddit.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키의 생성과 획득을 지원하는 유틸리티
 */
public class CookieUtils {
	private HttpServletRequest request;
	private Map<String, Cookie> cookieMap;
	
	public CookieUtils(HttpServletRequest request) {
		super();
		this.request = request;
		cookieMap = new LinkedHashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}
	
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	public boolean exists(String name) {
		return cookieMap.containsKey(name);
	}
	
	public String getCookieValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name);
		String value = null;
		if(cookie != null) {
			value = URLDecoder.decode(cookie.getValue(), "UTF-8");
		}
		return value;
	}

	public static Cookie createCookie(String name, String value) throws IOException {
		value = URLEncoder.encode(value, "UTF-8");
		Cookie cookie = new Cookie(name, value);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static enum TextType { DOMAIN, PATH }
	
	/**
	 * @param name
	 * @param value
	 * @param text
	 * @param type text 파라미터 사용 조건을 결정
	 * @return
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, String text, TextType type) throws IOException {
		Cookie cookie = createCookie(name, value);
		if(TextType.DOMAIN == type) {
			cookie.setDomain(text);
		} else if(TextType.PATH == type) {
			cookie.setPath(text);
		}
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String text, int maxAge, TextType type) throws IOException {
		Cookie cookie = createCookie(name, value, text, type);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path) throws IOException {
		Cookie cookie = createCookie(name, value);
		cookie.setPath(path);
		cookie.setDomain(domain);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
		Cookie cookie = createCookie(name, value, domain, path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}