package com.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;


public class TokenUtils
{

	public static final String MAGIC_KEY = "obfuscate";


	public static String createToken(UserDetails userDetails)
	{
		/* Expires in one hour */
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;

		return userDetails.getUsername() + ":" + expires + ":" + TokenUtils.computeSignature(userDetails, expires);
	}


	public static String computeSignature(UserDetails userDetails, long expires)
	{

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		return new String(Hex.encode(digest.digest((userDetails.getUsername() + ":" + expires + ":" +
													userDetails.getPassword() + ":" + TokenUtils.MAGIC_KEY).getBytes())));
	}


	public static String getUserNameFromToken(String authToken)
	{
		if (null == authToken) {
			return null;
		}

		String[] parts = authToken.split(":");
		return parts[0];
	}


	public static boolean validateToken(String authToken, UserDetails userDetails) {
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];

		return expires >= System.currentTimeMillis() && signature.equals(TokenUtils.computeSignature(userDetails,
																									 expires));

	}
}
