# apitool
## apitool工具类

### Maven项目的pom.xml的dependencies中加入以下内容:
``` xml
<dependency>
    <groupId>com.github.alotuser</groupId>
    <artifactId>apitool</artifactId>
    <version>1.0.2</version>
</dependency>
```
### demo代码演示
``` java
String accessKey="1001";
String secretKey="1001";

//客户端
String data="{\"fileDate\":\"13:53\",\"foodCode\":\"223010\",\"foodAmount\":\"100\"}";
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
//1.判断签名 sign=newSign
//2.还原数据
String newData=AesUtil.decryptStr(secretKey, GzipUtil.unGzip(data));
System.out.println("newData:"+newData);
//3.解析数据
//......
```

### accessKey和secretKey 用户密钥（保管好账号密钥，不要外泄）

### 参数：
	accessKey 访问用户ID
	data	  数据生成规则（1.先对提交内容进行ASE加密，再压缩成字符串）
	timestamp 时间戳
	sign      签名（按照请求参数名的字母升序排列非空请求参数，需要secretKey值进行签名）


	a)数据生成规则：
		1.先对提交内容进行ASE加密，再压缩成字符串

	b)签名获取步骤：
	 例如参数有 accessKey=101&data=XXXXXXXX&timestamp=12345678101
	 1.参数名的字母升序排列(如上)
	 2.需要secretKey值拼接，如：accessKey=101&data=XXXXXXXX&timestamp=12345678101&secretKey=ASDFGHJKL
	 3.对其进行MD5运算，并将得到的字符串所有字符转换为大写
