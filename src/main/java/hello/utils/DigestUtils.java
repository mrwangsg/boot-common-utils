package hello.utils;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.Provider;
import java.security.Security;

/**
 * @创建人 sgwang
 * @name DigestUtils
 * @user 91119
 * @创建时间 2020/2/24
 * @描述 摘要算法常见包括：md、sha这两类。
 * md包括md2、md4、md5；
 * sha分两大类，一类是sha1，另一类包含多种加密算法：sha224、sha256、sha384、sha512，这些统称为sha2；
 * 　其中sha1加密后的长度是160byte，sha2加密之后的密文长度和shaXxx的数字相同。
 */
public class DigestUtils {

    static {
        Provider provider = new BouncyCastleProvider(); // 拓展轻量级加密包
        Security.addProvider(provider); // 类生命周期，没有反射基本只会创建一次
    }

    private static MD5Digest md5Digest = new MD5Digest();
    private static SHA1Digest sha1Digest = new SHA1Digest();
    private static SHA256Digest sha256Digest = new SHA256Digest();

    /**
     * @param bytes 待摘要的信息
     * @return String
     */
    public static String getMD5ByBC(byte[] bytes) {
        md5Digest.update(bytes, 0, bytes.length);

        byte[] cipherBytes = new byte[md5Digest.getDigestSize()];

        md5Digest.doFinal(cipherBytes, 0);

        return Hex.toHexString(cipherBytes);
    }

    /**
     * @param bytes 待摘要的信息
     * @return String
     */
    public static String getSHA1ByBC(byte[] bytes) {
        sha1Digest.update(bytes, 0, bytes.length);
        byte[] cipherBytes = new byte[sha1Digest.getDigestSize()];

        sha1Digest.doFinal(cipherBytes, 0);

        return Hex.toHexString(cipherBytes);
    }

    /**
     * @param bytes 待摘要的信息
     * @return String
     */
    public static String getSHA256ByBC(byte[] bytes) {
        sha256Digest.update(bytes, 0, bytes.length);
        byte[] cipherBytes = new byte[sha256Digest.getDigestSize()];

        sha256Digest.doFinal(cipherBytes, 0);

        return Hex.toHexString(cipherBytes);
    }

    public static void main(String[] args) {
        String digestStr = "待摘要";

        System.out.println("md5摘要：" + DigestUtils.getMD5ByBC(digestStr.getBytes()));
        System.out.println("sha1摘要：" + DigestUtils.getSHA1ByBC(digestStr.getBytes()));
        System.out.println("sha256摘要：" + DigestUtils.getSHA256ByBC(digestStr.getBytes()));

    }
}
