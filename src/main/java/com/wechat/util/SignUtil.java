package com.wechat.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 公众平台验证签名
 * @author Administrator
 *
 */
public class SignUtil {
	private static final String token = "haiwaiyueqiu";
	
	/**
	 * 验证签名: 公众平台验证服务器地址的有效性
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		String[] arr = new String[]{token, timestamp, nonce};
		//字典排序
		Arrays.sort(arr);
		
		//拼接字符串
		StringBuffer content = new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		
		//sha1加密
		String temp = getSha1(content.toString());
		
		return temp.equals(signature);
	}
	
	/**
	 * SHA1加密算法
	 * @param str
	 * @return
	 */
	public static String getSha1(String str){
		if(str == null || str.length() == 0){
			return null;
		}
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for(int i=0; i<j; i++){
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
	
}
