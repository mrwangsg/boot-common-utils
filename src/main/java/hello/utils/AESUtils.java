package hello.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @创建人 sgwang
 * @name AESUtils
 * @user 91119
 * @创建时间 2020/2/20
 * @描述
 */
public class AESUtils {
    private static final String KEY_ALGORITHM = "AES"; // 对称密钥算法
    private static final String AES_CBC_PKCS5 = "AES/CBC/PKCS5Padding"; // 工作模式：CBC. 算法/模式/补码方式
    private static final String AES_ECB_PKCS5 = "AES/ECB/PKCS5Padding"; // 工作模式：ECB. 算法/模式/补码方式

    private static int KEY_SIZE = 128; // AES可以使用128、192、和256位密钥


    /**
     * 生成AES专属的密钥
     * @return SecretKeySpec
     * @throws NoSuchAlgorithmException
     */
    public static SecretKeySpec getSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
        kgen.init(KEY_SIZE);

        SecretKey secretKey = kgen.generateKey();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);

        return secretKeySpec;
    }

    /**
     * 生成AES专属的密钥(不推荐使用)
     *
     * @param random 随机数种子
     * @return SecretKeySpec
     * @throws NoSuchAlgorithmException
     */
    public static SecretKeySpec getSecretKey(String random) throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
        kgen.init(KEY_SIZE, new SecureRandom(random.getBytes()));

        SecretKey secretKey = kgen.generateKey();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);

        return secretKeySpec;
    }

    /**
     * 根据密钥编码字节，转为密钥对象
     * @param bytes 直接来源于getEncoded()
     * @return SecretKeySpec
     */
    public static SecretKeySpec createSecretKeySpec(byte[] bytes){
        return new SecretKeySpec(bytes, KEY_ALGORITHM);
    }

    protected static void beforeCheck(SecretKeySpec secretKeySpec, byte[] iv) {
        if (secretKeySpec == null || secretKeySpec.getEncoded().length != 16) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        if (iv == null || iv.length != 16) {
            throw new RuntimeException("Invalid AES iv length (must be 16 bytes)");
        }
    }

    public static byte[] encryptByCBC(byte[] data, SecretKeySpec secretKeySpec, byte[] iv) throws Exception {
        beforeCheck(secretKeySpec, iv);

        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5); // 创建密码器;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        return cipher.doFinal(data);
    }

    public static byte[] decryptByCBC(byte[] data, SecretKeySpec secretKeySpec, byte[] iv) throws Exception {
        beforeCheck(secretKeySpec, iv);

        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5); // 创建密码器
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv); // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec); //
        return cipher.doFinal(data);
    }

}
