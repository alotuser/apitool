package com.zd.apitool;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ZipUtil;
/**
 * Gzip压缩工具类
 * @author I6view
 *
 */
public class GzipUtil {

	/**
	 * Gzip压缩处理，默认UTF-8编码 
	 * @param content 被压缩的字符串
	 * @return
	 */
	public static String gzip(String content) {
		 return new String(ZipUtil.gzip(content, CharsetUtil.UTF_8),StandardCharsets.ISO_8859_1);
	}
	/**
	 * Gzip压缩处理 
	 * @param content 被压缩的字符串
	 * @param charset 编码
	 * @return
	 */
	public static String gzip(String content,String charset) {
		 return new String(ZipUtil.gzip(content, CharsetUtil.UTF_8),StandardCharsets.ISO_8859_1);
	}
	/**
	 * Gzip压缩处理 
	 * @param content 被压缩的字符串
	 * @param charset 编码
	 * @return
	 */
	public static String gzip(String content,Charset charset) {
		 return new String(ZipUtil.gzip(content, charset.name()),StandardCharsets.ISO_8859_1);
	}
	
	/**
	 * Gzip解压缩处理，默认UTF-8编码
	 * @param data 被压缩的字符串
	 * @return
	 */
	public static String unGzip(String data) {
		return ZipUtil.unGzip(data.getBytes(StandardCharsets.ISO_8859_1),CharsetUtil.UTF_8);
	}
	/**
	 *  Gzip解压缩处理
	 * @param data 被压缩的字符串
	 * @param charset 编码
	 * @return
	 */
	public static String unGzip(String data,String charset) {
		return ZipUtil.unGzip(data.getBytes(StandardCharsets.ISO_8859_1),charset);
	}
	/**
	 *  Gzip解压缩处理
	 * @param data 被压缩的字符串
	 * @param charset 编码
	 * @return
	 */
	public static String unGzip(String data,Charset charset) {
		return ZipUtil.unGzip(data.getBytes(StandardCharsets.ISO_8859_1),charset.name());
	}
}
