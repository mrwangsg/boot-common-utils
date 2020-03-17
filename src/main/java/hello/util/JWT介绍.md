#### 认证、授权、凭证科普 `https://xw.qq.com/cmsid/20190328A011W400`

#### 使用说明 `https://www.jianshu.com/p/e88d3f8151db`

1. Header `JWT 的 header 中承载了两部分信息`
   ```
   { "alg": "RS256", "typ": "JWT" }
   alg: 声明加密的算法;
   typ: 声明类型
   ```
2. Payload `payload 是主体部分，承载着有效的 JWT 数据包，它包含三个部分(标准声明、公共声明、私有声明）`
    ```
   2.1 标准声明的字段
   interface Stantar {
     iss?: string; // JWT的签发者
     sub?: string; // JWT所面向的用户
     aud?: string; // 接收JWT的一方
     exp?: number; // JWT的过期时间
     nbf?: number; // 在xxx日期之间，该JWT都是可用的
     iat?: number; // 该JWT签发的时间
     jti?: number; //JWT的唯一身份标识
   }
   
   2.2 公共声明的字段
   interface Public {
     [key: string]: any;
   }
   
   2.3 私有声明的字段
   interface Private {
     [key: string]: any;
   }
    ```
3. Signature `是签证信息，该签证信息是通过header和payload，加上secret，通过算法加密生成`
    ```
   公式 signature = 加密算法(header + "." + payload, 密钥);
   ```
4. 加密算法 `是单向函数散列算法，常见的有MD5、SHA、HAMC`
    ```
    MD5(message-digest algorithm 5) （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验。
        不管文件多大，经过MD5后都能生成唯一的MD5值
    SHA (Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，安全性高于MD5
    HMAC (Hash Message Authentication Code)，散列消息鉴别码，基于密钥的Hash算法的认证协议。
        用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。常用于接口签名验证
    ```