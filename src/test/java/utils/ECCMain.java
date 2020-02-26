package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hello.utils.Base64Utils;
import hello.utils.ECCUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 sgwang
 * @name ECCMain
 * @user 91119
 * @创建时间 2020/2/21
 * @描述 椭圆曲线加密/解密
 */
public class ECCMain {

    public static void main(String[] argu) throws Exception {
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        KeyPair keyPair = ECCUtils.getKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        endTime = System.currentTimeMillis();
        System.out.println("生成EC密钥程序运行时间： " + (endTime - startTime) + " ms");

        System.out.println("publicKey: " + Base64Utils.byteArrayToString(publicKey.getEncoded()));
        System.out.println("privateKey: " + Base64Utils.byteArrayToString(privateKey.getEncoded()));

        Map<String, Object> params = new HashMap<>();
        params.put("username", "王世光");
        params.put("password", "123456");

        String reqInfo = JSON.toJSONString(params);
        System.out.println("加密前的请求数据: " + reqInfo);

        startTime = System.currentTimeMillis();
        byte[] reqBytes = ECCUtils.encryptByPublicKey(reqInfo.getBytes(), publicKey);
        String sendData = Base64Utils.byteArrayToString(reqBytes);
        System.out.println("加密后的请求数据: " + sendData);
        endTime = System.currentTimeMillis();
        System.out.println("EC加密程序运行时间： " + (endTime - startTime) + " ms");

        // ------------------- 接收密文后的处理 ------------------- //

        startTime = System.currentTimeMillis();
        String getData = sendData;
        byte[] resBytes = ECCUtils.decryptByPrivateKey(Base64Utils.StringToByteArray(getData), privateKey);
        String resInfo = new String(resBytes, "UTF-8");
        System.out.println("解密后的响应数据: " + resInfo);
        endTime = System.currentTimeMillis();
        System.out.println("EC解密程序运行时间： " + (endTime - startTime) + " ms");

        JSONObject jsonObj = JSONObject.parseObject(reqInfo);
        String password = jsonObj.getString("password");
        String username = jsonObj.getString("username");
        System.out.println("解密后的明文: username:" + username + " password:" + password);
    }
}
