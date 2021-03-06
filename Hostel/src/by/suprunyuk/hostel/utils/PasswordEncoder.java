package by.suprunyuk.hostel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.suprunyuk.hostel.exception.PasswordEncodingException;

/** 
 * Class for encoding string using SHA-256 algorithm
 * 
 * @author Anton Suprunyuk
 */

public class PasswordEncoder {

	/**
	 * encodes String using SHA-256 algorithm 
	 * 
	 * @param password encoded String representation of user password
	 * @return String representation of encoded password by SHA-256 algorythm
	 * @throws PasswordEncodingException Exception that may occured during encoding
	 */
	public static String encode(String password) throws PasswordEncodingException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new PasswordEncodingException(e);
		}
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length * 2);
		for (byte b : mdArray) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}
}
