package hello.nio.server;

/**
 * @创建人 sgwang
 * @name Server
 * @user 91119
 * @创建时间 2019/7/28
 * @描述
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle, "Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}

