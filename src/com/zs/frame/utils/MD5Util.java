package com.zs.frame.utils;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;


/**
 * 加密
 * @author ml
 * @date 2017-07
 * @company 广东振森智能科技有限公司
 */
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
