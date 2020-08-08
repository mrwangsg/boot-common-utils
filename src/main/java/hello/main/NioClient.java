package hello.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @创建人 sgwang
 * @name NioClient
 * @user shiguang.wang
 * @创建时间 2020/8/8
 * @描述
 */

public class NioClient {
    //管道管理器
    private Selector selector;

    public NioClient init(String serverIp, int port) throws IOException {
        // 获取socket通道
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 获得通道管理器
        selector = Selector.open();

        //客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
        channel.connect(new InetSocketAddress(serverIp, port));
        //为该通道注册SelectionKey.OP_CONNECT事件
        channel.register(selector, SelectionKey.OP_CONNECT);

        return this;
    }

    public void listen() throws IOException {
        System.out.println("客户端启动");

        //轮询访问selector
        while (true) {
            //选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                //删除已选的key，防止重复处理
                ite.remove();

                if (key.isConnectable()) {
                    System.out.println("客户端连接成功：-------------------");
                    SocketChannel channel = (SocketChannel) key.channel();

                    //如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }

                    channel.configureBlocking(false);
                    //向服务器发送消息
                    channel.write(ByteBuffer.wrap("send message to server!".getBytes()));

                    //连接成功后，注册接收服务器消息的事件
                    channel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) { //有可读数据事件。
                    System.out.println("客户端获取到服务器响应： -------------------");
                    SocketChannel channel = (SocketChannel) key.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(512);
                    channel.read(buffer);
                    String message = new String(buffer.array());

                    System.out.println("recevie message from server:, size:" + buffer.position() + "    msg: " + message);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NioClient().init("127.0.0.1", 9999).listen();
    }
}