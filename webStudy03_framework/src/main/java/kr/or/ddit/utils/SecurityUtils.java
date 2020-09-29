package kr.or.ddit.utils;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import lombok.Builder;
import lombok.Getter;

public class SecurityUtils {
	
	public static enum RSAKey { PRIVATEKEY, PUBLICKEY }
	
	@Getter
	@Builder
	public static class CipherVO {
		private SecretKey secretKey;
		private byte[] iv;
		
		private PrivateKey privateKey;
		private PublicKey publicKey;
		
	}
	
	public static String encryptSha512(String plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = plain.getBytes();
			byte[] encrypted = md.digest(input);
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decryptRSA(String encoded, RSAKey keyKind, CipherVO cipherVo) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			PrivateKey privateKey = cipherVo.getPrivateKey();
			PublicKey publicKey = cipherVo.getPublicKey();
			Key decryptKey = RSAKey.PRIVATEKEY.equals(keyKind) ? privateKey : publicKey;
			cipher.init(Cipher.DECRYPT_MODE, decryptKey);
			
			byte[] decoded = Base64.getDecoder().decode(encoded);
			byte[] decrypted = cipher.doFinal(decoded);
			return new String(decrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String encryptRSA(String plain, RSAKey keyKind, Map<String, CipherVO> resultMap) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate(); // 전자서명에 대해서 암호화
			PublicKey publicKey = keyPair.getPublic(); // 전송 되는 데이터에 대해서 암호화
			Key encryptKey = RSAKey.PUBLICKEY.equals(keyKind) ? publicKey : privateKey; 
			resultMap.put("cipherSpec", 
			CipherVO.builder()
					.privateKey(privateKey)
					.publicKey(publicKey)
					.build());

			cipher.init(Cipher.ENCRYPT_MODE, encryptKey);
			byte[] input = plain.getBytes();
			byte[] encrypted = cipher.doFinal(input);
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decryptAES(String encoded, CipherVO cipherVo) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKey key = cipherVo.getSecretKey();
			byte[] iv = cipherVo.getIv();
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			byte[] decoded = Base64.getDecoder().decode(encoded);
			byte[] decrypted = cipher.doFinal(decoded);
			return new String(decrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String encryptAES(String plain, Map<String, CipherVO> resultMap) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey key =  keyGenerator.generateKey();
			byte[] iv = new byte[16];
			resultMap.put("cipherSpec",
			CipherVO.builder()
						.secretKey(key)
						.iv(iv)
						.build());
			SecureRandom random = new SecureRandom();
			random.nextBytes(iv);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			byte[] input = plain.getBytes();
			byte[] encrypted = cipher.doFinal(input);
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}