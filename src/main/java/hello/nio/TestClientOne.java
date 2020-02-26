package hello.nio;

import hello.nio.client.Client;

import java.util.Scanner;

/**
 * @创建人 sgwang
 * @name TestClientOne
 * @user 91119
 * @创建时间 2019/7/28
 * @描述
 */

public class TestClientOne {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //运行客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) ;
    }
}

