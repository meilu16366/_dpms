package com.kx.frame.utils;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;


public class MD5Util {
	
	/**
	 * 加密
	 * @param password
	 * @return
	 */
	public static String encode(String password){
		String word = "";
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			word = base64en.encode(md5.digest(password.getBytes("utf-8")));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return word;
	}
	
	
}
