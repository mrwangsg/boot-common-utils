package hello.utils;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

/**
 * @创建人 sgwang
 * @name RSAUtils
 * @user 91119
 * @创建时间 2020/2/20
 * @描述
 */
public class RSAUtils {
    private static final String KEY_ALGORITHM = "RSA"; // 非对称密钥算法
    private static int KEY_SIZE = 2048; // 密钥字节长度，在512到16384位之间。建议是64的整数倍

    /**
     * ----------------------- 以上，默认参数 -----------------------
     * ----------------------- 以下，生成密钥对 -----------------------
     */

    /**
     * 生成密钥对
     * 耗时的关键点 KeyPairGenerator.getInstance()
     *
     * @param keySize
     * @return KeyPair
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair getKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 生成密钥对
     * 耗时的关键点 KeyPairGenerator.getInstance()
     *
     * @return KeyPair
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * ----------------------- 以上，生成密钥对 -----------------------
     * ----------------------- 以下，根据byte[] 还原公钥/私钥 -----------------------
     */

    /**
     * 通过公钥byte[]将公钥还原
     *
     * @param keyBytes 直接来源于getEncoded()
     * @return PublicKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 通过公钥byte[]将公钥还原
     *
     * @param keyBytes 直接来源于getEncoded()
     * @return PrivateKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * ----------------------- 以上，根据byte[] 还原公钥/私钥 -----------------------
     * ----------------------- 以下，根据(N,e)公钥，(N,d)私钥 还原公钥/私钥 -----------------------
     */

    /**
     * 根据公钥特征值，返回公钥。即使用N、e值还原公钥
     *
     * @param modulus        直接来源于getModulus()
     * @param publicExponent 直接来源于getPublicExponent()
     * @return PublicKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPublicExponent = new BigInteger(publicExponent);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPublicExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 根据私钥特征值，返回私钥。即使用N、d值还原私钥
     *
     * @param modulus         直接来源于getModulus()
     * @param privateExponent 直接来源于getPrivateExponent()
     * @return PublicKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String modulus, String privateExponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);

        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * ----------------------- 以上，根据(N,e)公钥，(N,d)私钥 还原公钥/私钥 -----------------------
     * ----------------------- 以下，使用公钥/私钥，进行加密/解密 -----------------------
     */

    /**
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * ----------------------- 以上，使用公钥/私钥，进行加密/解密 -----------------------
     * ----------------------- 以下，打印公钥/私钥 -----------------------
     */

    public static String printPublicKeyStr(PublicKey publicKey) throws Exception {
        System.out.println("publicKey: " + new String(publicKey.getEncoded()));
        return new String(publicKey.getEncoded());
    }

    public static String printPrivateKeyStr(PrivateKey privateKey) throws Exception {
        System.out.println("privateKey: " + new String(privateKey.getEncoded()));
        return new String(privateKey.getEncoded());
    }


}
