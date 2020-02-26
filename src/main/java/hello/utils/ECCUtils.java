package hello.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.*;

/**
 * @创建人 sgwang
 * @name ECCUtils
 * @user 91119
 * @创建时间 2020/2/21
 * @描述 椭圆曲线加密
 */
public class ECCUtils {
    private static final String KEY_ALGORITHM_WHOLE = "EC"; // 椭圆曲线算法，包含其下的所有算法
    private static final String KEY_ALGORITHM_ECIES = "ECIES"; // 椭圆曲线下的 加密解密算法
    private static final String KEY_ALGORITHM_SIGN = "SHA256withECDSA"; // 椭圆曲线下的 签名算法
    private static final String PROVIDER_BC = "BC"; // 包括椭圆曲线加密

    private static int KEY_SIZE = 256; // BouncyCastle库对keysize有指定要求的. 支持这些选值：192, 239, 256, 224, 384, 521

    static {
        Provider provider = new BouncyCastleProvider(); // 拓展轻量级加密包
        Security.addProvider(provider); // 类生命周期，没有反射基本只会创建一次
    }

    /**
     * 生成密钥对
     *
     * @return KeyPair
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM_WHOLE, PROVIDER_BC);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 生成密钥对(不推荐直接使用)
     *
     * @param keySize
     * @return KeyPair
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static KeyPair getKeyPair(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM_WHOLE, PROVIDER_BC);
        keyPairGenerator.initialize(keySize);
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
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_WHOLE, PROVIDER_BC);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 通过公钥byte[]将公钥还原
     *
     * @param keyBytes 直接来源于getEncoded()
     * @return PrivateKey
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_WHOLE, PROVIDER_BC);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * ----------------------- 以上，根据byte[] 还原公钥/私钥 -----------------------
     * ----------------------- 以下，根据(w, params)公钥，(s, params)私钥 还原公钥/私钥 -----------------------
     */

    /**
     * 根据公钥特征值，返回公钥。即使用(w, params)值还原公钥
     */
    public static PublicKey getPublicKey(ECPoint w, ECParameterSpec params) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        ECPublicKeySpec keySpec = new ECPublicKeySpec(w, params);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_WHOLE, PROVIDER_BC);

        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 根据私钥特征值，返回私钥。即使用(s, param)值还原私钥
     */
    public static PrivateKey getPrivateKey(BigInteger s, ECParameterSpec params) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        ECPrivateKeySpec keySpec = new ECPrivateKeySpec(s, params);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_WHOLE, PROVIDER_BC);

        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * ----------------------- 以上，根据(w, params)公钥，(s, params)私钥 还原公钥/私钥  -----------------------
     * ----------------------- 以下，使用公钥/私钥，进行加密/解密 -----------------------
     */

    /**
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_ECIES, PROVIDER_BC); // 加密算法,ECIES. 拓展：签名算法叫ECDSA
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
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_ECIES, PROVIDER_BC); // 解密算法,ECIES. 拓展：签名算法叫ECDSA
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * ----------------------- 以上，使用公钥/私钥，进行加密/解密 -----------------------
     * ----------------------- 以下，打印公钥/私钥 -----------------------
     */

    public static String printECCPublicKeyStr(PublicKey publicKey) throws Exception {
        ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
        System.out.println("ECPublicKey: " + ecPublicKey.toString());
        return ecPublicKey.toString();
    }

    public static String printECCPrivateKey(PrivateKey privateKey) throws Exception {
        ECPrivateKey ecPrivateKey = (ECPrivateKey) privateKey;
        System.out.println("ECPrivateKey: " + ecPrivateKey.toString());
        return ecPrivateKey.toString();
    }

    /**
     * ----------------------- 以上，打印公钥/私钥 -----------------------
     * ----------------------- 以下，使用私钥/公钥，进行签名/验签 -----------------------
     */

    /**
     * 通过私钥 生成签名信息
     * @param data 签名前数据信息
     * @param privateKey 私钥
     * @return byte[]
     * @throws Exception
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
        PrivateKey priKey = getPrivateKey(privateKey);

        Signature signature = Signature.getInstance(KEY_ALGORITHM_SIGN);
        signature.initSign(priKey);
        signature.update(data);

        return signature.sign();
    }

    /**
     * 通过公钥 验证签名信息
     * @param data 签名前数据信息
     * @param publicKey 公钥
     * @param sign 签名
     * @return boolean
     * @throws Exception
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
        PublicKey pubKey = getPublicKey(publicKey);

        Signature signature = Signature.getInstance(KEY_ALGORITHM_SIGN);
        signature.initVerify(pubKey);
        signature.update(data);

        return signature.verify(sign);
    }


    public static void main(String[] args) throws Exception {
        KeyPair keyPair = ECCUtils.getKeyPair();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

        // 打印公钥/私钥
        ECCUtils.printECCPublicKeyStr(publicKey);
        ECCUtils.printECCPrivateKey(privateKey);

        // 还原 公钥
        PublicKey getPublicKey = ECCUtils.getPublicKey(publicKey.getEncoded());
        PublicKey ecPublicKeySpec = ECCUtils.getPublicKey(publicKey.getW(), publicKey.getParams());
        ECCUtils.printECCPublicKeyStr(getPublicKey);
        ECCUtils.printECCPublicKeyStr(ecPublicKeySpec);

        // 还原 私钥
        PrivateKey getPrivateKey = ECCUtils.getPrivateKey(privateKey.getEncoded());
        PrivateKey ecPrivateKeySpec = ECCUtils.getPrivateKey(privateKey.getS(), privateKey.getParams());
        ECCUtils.printECCPrivateKey(getPrivateKey);
        ECCUtils.printECCPrivateKey(ecPrivateKeySpec);

        String signStr = "验证签名";
        byte[] signBytes = sign(signStr.getBytes(), privateKey.getEncoded());
        System.out.println("verify: " + verify(signStr.getBytes(), publicKey.getEncoded(), signBytes));
    }

}
