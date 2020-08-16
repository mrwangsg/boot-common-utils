package hello.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @创建人 sgwang
 * @name ByteHexUtil
 * @user shiguang.wang
 * @创建时间 2020/8/16
 * @描述
 */
public class ByteHexUtil {


    /**
     * 字节转十六进制
     *
     * @param b 需要进行转换的byte字节
     * @return 转换后的Hex字符串
     */
    public static String byteToHex(byte b) {
        // 使用0xff，是因为使用Integer时，byte先转为int，变成4字节，然后需要0xff与掉一些
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    // 将10进制转换为16进制
    public static String tenToHex(Integer tenInt) {
        return Integer.toHexString(tenInt);
    }

    // 将10进制转换为16进制
    public static int hexToTen(String strHex) {
        // 直接输出int型，因为没有正负数之分，所以结果都是正的。byte型就不一样了，首位为符号位有正负之分
        // (byte) Integer.parseInt("89", 16) = -119
        //        Integer.parseInt("89", 16) = 137
        return Integer.parseInt(strHex, 16);

    }


    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                strBuffer.append(0);
            }
            strBuffer.append(hex);
        }
        return strBuffer.toString();
    }


    /**
     * Hex字符串转byte
     *
     * @param strHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToOneByte(String strHex) {
        return (byte) Integer.parseInt(strHex, 16);
    }

    /**
     * hex字符串转byte数组
     *
     * @param strHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToArrayByte(String strHex) {
        int hexlen = strHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            strHex = "0" + strHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToOneByte(strHex.substring(i, i + 2));
            j++;
        }
        return result;
    }


    public static void main(String[] args) {
        System.err.println(Integer.toHexString(-119 & 0xff));
        System.err.println(tenToHex(-119));
        System.err.println(hexToTen("89"));

        String str = "Hello world! 王世光";

        System.err.println(Arrays.toString(hexToArrayByte("48656c6c6f20776f726c642120e78e8be4b896e58589")));
        System.err.println(Arrays.toString(hexToArrayByte("48656c6c6f20776f726c642120e78e8be4b896e58589")));


        System.err.println(Arrays.toString(str.getBytes(StandardCharsets.UTF_8)) + ",  len: " + str.getBytes(StandardCharsets.UTF_8).length);
        System.err.println(bytesToHex(str.getBytes(StandardCharsets.UTF_8)));

        byte[] temp = str.getBytes(StandardCharsets.UTF_8);
        for (byte b : temp) {
            System.err.print(b);
        }

    }

}
