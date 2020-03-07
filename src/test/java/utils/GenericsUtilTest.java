package utils;

import hello.HttpResult;
import hello.demo.DemoEnum;
import hello.utils.GenericsUtil;

import java.util.Arrays;

/**
 * @Author :  sgwang
 * @Version :  1.0
 * @Title :  GenericsUtilTest
 * @CreateDate :  2020/3/7 22:53
 * @Description :  根据输入 排名 获取相关奖励
 */
public class GenericsUtilTest {

    public static void main(String[] args) {
        DemoEnum demoEnum = DemoEnum.valueOf("Second");
        System.err.println(demoEnum.getDesc());

        System.err.println("--------------------- 分割线 ------------------");
        GenericsUtil.getEnumPropName(Arrays.asList(DemoEnum.values()));

        System.err.println("--------------------- 分割线 ------------------");
        Integer index = 2;
        DemoEnum res = DemoEnum.getEnumByIndex(index);
        if (res == null) {
            return;
        }
        switch (res) {
            case First:
                System.err.println("I am " + res.getDesc());
                break;
            case Second:
                System.err.println("I am " + res.getDesc());
                break;
            case Third:
                System.err.println("I am " + res.getDesc());
                break;
            default:
                System.err.println("I am default!");
                break;
        }

        System.err.println("--------------------- 分割线 ------------------");
        GenericsUtil.justPrint("测试泛型！！！");

        System.err.println("--------------------- 分割线 ------------------");
        HttpResult httpRes = GenericsUtil.getHttpResult("HttpResult: 测试泛型！！！");
        System.err.println(httpRes.getResult().toString());

    }

}
