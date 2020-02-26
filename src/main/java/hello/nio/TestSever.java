package hello.nio;

import hello.nio.server.Server;

/**
 * @创建人 sgwang
 * @name TestSever
 * @user 91119
 * @创建时间 2019/7/28
 * @描述
 */
public class TestSever {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
    }
}
