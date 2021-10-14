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
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); // �ý��� �����⿡�� �õ��ϱ� ������, ���ҽ��� �� ����� 
			byte[] bytes = new byte[16];
			random.nextBytes(bytes); //  SecureRandom�� ����� ��, nextBytes�� ȣ���� ���� ����Ʈ�� ����
			salt = new String(Base64.getEncoder().encode(bytes)); // 8��Ʈ ���� �����͸� �ƽ�Ű ������ ���ڵ�� �̷��� ���ڿ��� ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}
	
	public static String transferToSHA_256(String input, String salt) {
		String res = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); //�ؽ����ִ� �κ�
			md.update(input.getBytes());
			md.update(salt.getBytes());
			res = String.format("%064x", new BigInteger(1, md.digest())); // 16���� 64����Ʈ�� ����� ����
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return res;
	}

}
