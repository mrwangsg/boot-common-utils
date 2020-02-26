package utils;

import hello.utils.Base64Utils;

import java.util.Base64;

/**
 * @创建人 sgwang
 * @name Base64Main
 * @user 91119
 * @创建时间 2020/2/20
 * @描述
 */
public class Base64Main {
    public static void main(String[] args) throws Exception {
        String name = "王世光";

        String temp = new String(Base64.getDecoder().decode(Base64.getEncoder().encode(name.getBytes("UTF-8"))), "UTF-8");
        System.out.println(temp);

        temp = new String(Base64Utils.StringToByteArray(Base64Utils.byteArrayToString(name.getBytes("UTF-8"))), "UTF-8");
        System.out.println(temp);
    }
}
