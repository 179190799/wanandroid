package com.chf.wanandroid.ui.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	 

	  /*
	   * MD5 32位密码
	   */
	  public static String MD32(String str) {
	        try {
	                MessageDigest md = MessageDigest.getInstance("MD5");
	                md.update(str.getBytes());
	                byte b[] = md.digest();
	                int i;
	                StringBuffer buf = new StringBuffer("");
	                for (int offset = 0; offset < b.length; offset++) {
	                        i = b[offset];
	                        if (i < 0)
	                        i += 256;
	                        if (i < 16)
	                        buf.append("0");
	                        buf.append(Integer.toHexString(i));
	                }
	                return buf.toString();// 32位的加密
	    } catch (NoSuchAlgorithmException e) {
	                 e.printStackTrace();
	                 return "";
	        }
	}
}

