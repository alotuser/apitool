package com.zd.apitool;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;


/**
 * base64加密加盐
 * @author I6view
 *
 */
public class Base64Plus {

	
	
	
	/**
	 * base64编码再加密，默认UTF-8编码
	 *
	 * @param pwd 密码
	 * @param data 被编码的base64字符串
	 * @return 被加密后的字符串
	 */
	public static String encodeStr(Integer pwd,CharSequence data) {

		String enData= Base64.encode(data);
		if(null!=pwd) {
			String 			in	=	StrUtil.toString(pwd);
			StringBuilder 	sb	=	StrUtil.builder(enData);
			for (int j = 0; j < in.length(); j++) {
				int a= Integer.parseInt(StrUtil.toString(in.charAt(j)));
				for (int i = 0,len=getDiv(sb, a),s=a+1; i <= len; i++) {
					sb.insert(i*s , RandomUtil.randomString(1));
				}
			}
			enData=sb.toString();
		}
		
		return enData;
	}
	
	/**
	 * base64解码
	 * @param pwd  密码
	 * @param data 被解码的base64字符串
	 * @return 密文解密的结果
	 */
	public static String decodeStr(Integer pwd,CharSequence data) {
		
		CharSequence deData=data;
		if(null!=pwd) {
			String 			in	=	StrUtil.toString(pwd);
			StringBuilder 	sb	=	StrUtil.builder(deData);
			
			for (int j = in.length()-1; j >=0; j--) {
				int a= Integer.parseInt(StrUtil.toString(in.charAt(j)));
				for (int i = 0,len=getDiv(sb, a),s=a+1; i <= len; i++) {
					int del=i*s-i;
					if(del<=sb.length()) {
						sb.deleteCharAt(del);
					}
				} 
			}
			deData=sb.toString();
		}
		return Base64.decodeStr(deData);
		
		//return deData.toString();
	}
	
	
	/**
	 * 长度
	 * @param sb
	 * @param size
	 * @return 排除0
	 */
	private static int getDiv(StringBuilder sb,int size) {
		
		if(0!=size) {
			return sb.length()/size;
		}
		return 0;
		
	}
	

	
}
