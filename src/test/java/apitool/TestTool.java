package apitool;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zd.apitool.AesUtil;
import com.zd.apitool.GzipUtil;
import com.zd.apitool.SignUtil;

import cn.hutool.core.util.StrUtil;

public class TestTool {

	public static void main(String[] args) {
		String accessKey="1001";
		String secretKey="1001";
		
		//客户端
		String data="{\"fileDate\":\"13:53\",\"foodCode\":\"223010\",\"foodAmount\":\"100\",\"foodEnergy\":127,\"menuCode\":0,\"foodType\":0,\"menuType\":0}";
		data=GzipUtil.gzip(AesUtil.encryptBase64(secretKey, data));	
		Map<String,String> params=new HashMap<String, String>();
		params.put("accessKey", accessKey);
		params.put("data", data);
		params.put("timestamp", StrUtil.toString(new Date().getTime()));
		String sign= SignUtil.signParamsMd5(params, "secretKey",secretKey);
		System.out.println("sign:"+sign);
		System.out.println("data:"+data);
		
		//服务端
		String newSign=SignUtil.signParamsMd5(params, "secretKey",secretKey);
		System.out.println("newSign:"+newSign);
		//1.判断签名 
		//sign=newSign
		//2.还原数据
		String newData=AesUtil.decryptStr(secretKey, GzipUtil.unGzip(data));
		System.out.println("newData:"+newData);
		//3.解析数据
		//......
	}

}
