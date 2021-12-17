package com.zd.apitool;

import java.nio.charset.Charset;

import com.zd.apitool.pool.CharPool;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
/**
 * AES对称加解密工具类
 * @author I6view
 *
 */
public class AesUtil {

	/**
	 * AES加密后再Base64加密，默认UTF-8编码
	 * @param pwd 密码
	 * @param data 数据
	 * @return
	 */
	public static String encryptBase64(String pwd,String data) {
		byte[] key= getKeyBytes(pwd);
		return SecureUtil.aes(key).encryptBase64(data, CharsetUtil.CHARSET_UTF_8);
	}
	/**
	 * AES加密后再Base64加密
	 * @param pwd 密码
	 * @param data 数据
	 * @param charset 编码
	 * @return
	 */
	public static String encryptBase64(String pwd,String data,String charset) {
		byte[] key= getKeyBytes(pwd);
		return SecureUtil.aes(key).encryptBase64(data,charset);
	}
	/**
	 * AES加密后再Base64加密
	 * @param pwd 密码
	 * @param data 数据
	 * @param charset 编码
	 * @return
	 */
	public static String encryptBase64(String pwd,String data,Charset charset) {
		byte[] key= getKeyBytes(pwd);
		return SecureUtil.aes(key).encryptBase64(data, charset);
	}
	/**
	 * 解密Hex（16进制）或Base64表示的字符串，默认UTF-8编码
	 * @param pwd 密码
	 * @param data 数据
	 * @return
	 */
	public static String decryptStr(String pwd,String data) {
		byte[] key= getKeyBytes(pwd);
		return SecureUtil.aes(key).decryptStr(data);
	}
	/**
	 * 解密Hex（16进制）或Base64表示的字符串
	 * @param pwd 密码
	 * @param data 数据
	 * @param charset 编码
	 * @return
	 */
	public static String decryptStr(String pwd,String data,String charset) {
		return decryptStr(pwd,data,CharsetUtil.charset(charset));
	}
	/**
	 * 解密Hex（16进制）或Base64表示的字符串
	 * @param pwd 密码
	 * @param data 数据
	 * @param charset 编码
	 * @return
	 */
	public static String decryptStr(String pwd,String data,Charset charset) {
		byte[] key= getKeyBytes(pwd);
		return SecureUtil.aes(key).decryptStr(data,charset);
	}
	/**
	 * KeyBytes
	 * @param pwd 密码
	 * @return
	 */
	private static byte[] getKeyBytes(String pwd) {
		return StrUtil.fillAfter(pwd, CharPool.ZERO, 16).getBytes();
	}
	
	
	
}
