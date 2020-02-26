package hello.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @创建人 sgwang
 * @name Base64Utils
 * @user 91119
 * @创建时间 2020/2/20
 * @描述
 */
public class Base64Utils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 使用Base64编码后，再使用默认字符集，转为字符串
     *
     * @param bytes
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String byteArrayToString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(bytes), DEFAULT_CHARSET);
    }

    public static String byteArrayToString(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(bytes), charsetName);
    }


    /**
     * 使用默认字符集，转为字符串后，再使用Base64解码
     *
     * @param str
     * @return byte[]
     * @throws UnsupportedEncodingException
     */
    public static byte[] StringToByteArray(String str) throws UnsupportedEncodingException {
        return Base64.getDecoder().decode(str.getBytes(DEFAULT_CHARSET));
    }

    public static byte[] StringToByteArray(String str, String charsetName) throws UnsupportedEncodingException {
        return Base64.getDecoder().decode(str.getBytes(charsetName));
    }

}
