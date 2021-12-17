# apitool
 apitool工具类

Maven:在项目的pom.xml的dependencies中加入以下内容:
``` xml
<dependency>
    <groupId>com.github.alotuser</groupId>
    <artifactId>apitool</artifactId>
    <version>1.0.1</version>
</dependency>
```

accessKey和secretKey 用户密钥（保管好账号密钥，不要外泄）

参数：
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
