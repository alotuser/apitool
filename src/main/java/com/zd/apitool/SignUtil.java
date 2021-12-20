package com.zd.apitool;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.zd.apitool.pool.StrPool;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 签名工具类
 * @author I6view
 *
 */
public class SignUtil {

	/**
	 * 对参数做md5签名<br>
	 * 参数签名为对Map参数按照key的顺序排序后拼接为字符串，然后根据提供的签名算法生成签名字符串<br>
	 * 拼接后的字符串键值对之间无符号，键值对之间无符号，忽略null值
	 *
	 * @param params      参数
	 * @param otherParams 其它附加参数字符串（例如密钥）
	 * @return 签名
	 */
	public static String signParamsMd5(Map<?, ?> params, String... otherParams) {
		return SecureUtil.signParamsMd5(params,otherParams);
	}
	/**
	 * 对参数做md5签名<br>
	 * 参数签名为对Map参数按照key的顺序排序后拼接为字符串，然后根据提供的签名算法生成签名字符串<br>
	 * 拼接后的字符串键值对之间无符号，键值对之间无符号
	 * @param params	  参数
	 * @param otherParams 其它附加参数字符串（例如密钥）
	 * @return
	 */
	public static String signParamsMd5Upper(Map<String, String> params,String... otherParams) {
		return signParamsMd5Str(params,StrPool.EMPTY,StrPool.EMPTY,otherParams).toUpperCase();
	}
	/**
	 * 对参数做md5签名，并将得到的字符串所有字符转换为大写<br>
	 * 参数签名为对Map参数按照key的顺序排序后拼接为字符串，然后根据提供的签名算法生成签名字符串<br>
	 * 拼接后的字符串键值对之间无符号，键值对之间无符号
	 *
	 * @param params			参数
	 * @param separator			entry之间的连接符
	 * @param keyValueSeparator	kv之间的连接符
	 * @param otherParams		其它附加参数字符串（例如密钥）
	 * @return
	 */
	public static String signParamsMd5Str(Map<String, String> params,String separator, String keyValueSeparator,String... otherParams) {
		Map<String,String> keyvals=new HashMap<String, String>();
		String otherParamsStr=StrUtil.EMPTY;
		if (ArrayUtil.isNotEmpty(otherParams)) {
			 for (int i = 0; i < otherParams.length; i += 2) {
		            String key = otherParams[i];
		            String value = otherParams[i+1];
		            keyvals.put(key, value);
		        }
			 otherParamsStr= MapUtil.join(keyvals, separator, keyValueSeparator);
		}
		TreeMap<String, String> tmp= new TreeMap<String, String>(params);
		String data= MapUtil.join(tmp, separator, keyValueSeparator,separator,otherParamsStr);
		return SecureUtil.md5(data);
	}
	
	
	
}
