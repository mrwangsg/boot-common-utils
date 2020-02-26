package utils;

import hello.utils.AESUtils;
import hello.utils.Base64Utils;

import javax.crypto.spec.SecretKeySpec;

/**
 * @创建人 sgwang
 * @name AESMain
 * @user 91119
 * @创建时间 2020/2/20
 * @描述 AES五种加密模式（CBC、ECB、CTR、OCF、CFB）
 */
public class AESMain {
    public static void main(String[] args) throws Exception {
        long startTime, endTime;

        String data = "hello world! 王世光" + "hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光hello world! 王世光";
        System.out.println(data.getBytes().length);
        String iv = "4Ct7TyXZa19rgQKK";
        SecretKeySpec secretKeySpec = AESUtils.getSecretKey();

        startTime = System.currentTimeMillis();
        System.out.println("Before: " + data);
        byte[] befBytes = AESUtils.encryptByCBC(data.getBytes(), secretKeySpec, iv.getBytes());
        endTime = System.currentTimeMillis();
        System.out.println("AES加密程序运行时间： " + (endTime - startTime) + " ms");

        // 字节数组 转化为 字符串 再网络传输
        String befBytesStr = Base64Utils.byteArrayToString(befBytes);
        String secretKeySpecStr = Base64Utils.byteArrayToString(secretKeySpec.getEncoded());
        String ivStr = Base64Utils.byteArrayToString(iv.getBytes());
        System.out.println("befBytesStr: " + befBytesStr + "    secretKeySpecStr: " + secretKeySpecStr + "  ivStr: " + ivStr);

        // ------------------- 接收密文后的处理 ------------------- //

        byte[] aftBytes = Base64Utils.StringToByteArray(befBytesStr);
        byte[] secretKeySpecBytes = Base64Utils.StringToByteArray(secretKeySpecStr);
        byte[] ivBytes = Base64Utils.StringToByteArray(ivStr);

        startTime = System.currentTimeMillis();
        String aftStr = new String(AESUtils.decryptByCBC(aftBytes, AESUtils.createSecretKeySpec(secretKeySpecBytes), ivBytes));
        System.out.println("After: " + aftStr);
        endTime = System.currentTimeMillis();
        System.out.println("AES解密程序运行时间： " + (endTime - startTime) + " ms");
    }
}
