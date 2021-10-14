package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class ShaEncoder {
	
	public static String randomSaltBySHA1PRNG() {
		String salt = "";
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); // 시스템 생성기에서 시드하기 때문에, 리소스를 덜 사용함 
			byte[] bytes = new byte[16];
			random.nextBytes(bytes); //  SecureRandom를 취득한 뒤, nextBytes를 호출해 난수 바이트를 생성
			salt = new String(Base64.getEncoder().encode(bytes)); // 8비트 이진 데이터를 아스키 영역의 문자들로 이뤄진 문자열로 변경
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}
	
	public static String transferToSHA_256(String input, String salt) {
		String res = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); //해싱해주는 부분
			md.update(input.getBytes());
			md.update(salt.getBytes());
			res = String.format("%064x", new BigInteger(1, md.digest())); // 16진수 64바이트로 결과값 나옴
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return res;
	}

}
