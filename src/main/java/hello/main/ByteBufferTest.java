package hello.main;

import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @创建人 sgwang
 * @name ByteBufferTest
 * @user shiguang.wang
 * @创建时间 2020/7/31
 * @描述
 */
public class ByteBufferTest {
    /**
     * 缓存区设置
     */
    private final static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    /**
     * 测试文件缓存
     */
    private static String bootFilePath;

    public static void main(String[] args) throws IOException {
        bootFilePath = ResourceUtils.getFile("classpath:application.properties").getPath();

        // 读取模式打开
        RandomAccessFile raFile = new RandomAccessFile(bootFilePath, "r");
        FileChannel fileChannel = raFile.getChannel();

        while (fileChannel.read(byteBuffer) != -1) {
            // 修改为读模式
            byteBuffer.flip();

            // 打印字符
            while (byteBuffer.hasRemaining()){
                System.err.print((char)byteBuffer.get());
            }

            // 清空，设置为写模式
            byteBuffer.clear();
        }
    }

}
