package kr.or.ddit.vo;

import org.junit.Test;

public class RegularExpressionTest {
	
	@Test
	public void regexTest() {
		String password = "!3";
		String regex = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).*";
		System.out.println(password.matches(regex));
	}
}