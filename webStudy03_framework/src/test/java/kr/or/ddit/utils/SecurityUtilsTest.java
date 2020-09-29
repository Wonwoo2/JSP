package kr.or.ddit.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.utils.SecurityUtils.CipherVO;
import kr.or.ddit.utils.SecurityUtils.RSAKey;

public class SecurityUtilsTest {
	String plain;
	Map<String, CipherVO> resultMap = new HashMap<>();
	@Before
	public void setUp() throws Exception {
		plain = "한글데이터 평문";
		resultMap.clear();
	}

	@Test
	public void testEncryptSha512() {
		
	}

	@Test
	public void testEncryptAES() {
		System.out.println("=============================AES=============================");
		String encoded = SecurityUtils.encryptAES(plain, resultMap);
		System.out.println(encoded);
		String plain = SecurityUtils.decryptAES(encoded, resultMap.get("cipherSpec"));
		System.out.println(plain);
		System.out.println("=============================================================");
		
	}
	
	@Test
	public void testEncryptRSA() {
		System.out.println("=============================RSA=============================");
		String encoded = SecurityUtils.encryptRSA(plain, RSAKey.PRIVATEKEY, resultMap);
		System.out.println(encoded);
		String plain = SecurityUtils.decryptRSA(encoded, RSAKey.PUBLICKEY, resultMap.get("cipherSpec"));
		System.out.println(plain);
		System.out.println("=============================================================");
	}

}
