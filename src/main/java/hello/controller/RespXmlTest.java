package hello.controller;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @创建人 sgwang
 * @name RespXmlTest
 * @user shiguang.wang
 * @创建时间 2020/11/2
 * @描述
 */
public class RespXmlTest {

    private static String filePath = "/gphome/gpweb/deploy/apps/tmp/NormalResp.xml";
//    private static String filePath = "C:\\project\\boot-common-utils\\src\\main\\resources\\NormalResp.xml";

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(60080);
        System.out.println("启动服务器....");

        while (true) {
            Socket s = ss.accept();
            System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");

            // 读取客户端发送来的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String mess = br.readLine();
            System.out.println("客户端：" + mess);

            byte[] bytes = getFileBytes(filePath);
            System.err.println("字节长度：" + bytes.length);
            System.err.println(new String(bytes));

            BufferedOutputStream boStream = new BufferedOutputStream(s.getOutputStream());
            boStream.write(bytes);
            boStream.flush();
        }
    }

    private static byte[] getResp(String defaultFilePath) throws FileNotFoundException {
        File xmlFile = ResourceUtils.getFile(defaultFilePath);

        return fileToByteArray(xmlFile);
    }

    /**
     * 将文件读成byte[]数组
     *
     * @param file
     * @return
     */
    public static byte[] fileToByteArray(File file) {

        FileInputStream fileInputStream = null;
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        byte[] bt = null;
        try {
            if (!file.exists() || file.isDirectory()) {
                return null;
            }
            fileInputStream = new FileInputStream(file);

            in = new BufferedInputStream(fileInputStream);
            out = new ByteArrayOutputStream();
            byte[] temp = new byte[5 * 1024 * 1024];  //每次读取 1M
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }

            bt = out.toByteArray();
            // for(int i = 0; i < bt.length; i++)

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bt;
    }

    public static byte[] getFileBytes(String filePath) throws Exception {

        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        int len = inputStream.read(bytes);
        System.out.println("文件字节长度为" + len);
        System.out.println(new String(bytes));
        inputStream.close();

        byte[] resBytes = new byte[bytes.length + 8];
        String lenStr = addZeroForStr(String.valueOf(bytes.length), 8);

        System.arraycopy(lenStr.getBytes(), 0, resBytes, 0, 8);
        System.arraycopy(bytes, 0, resBytes, 8, bytes.length);

        return resBytes;
    }

    /**
     * 左补0
     *
     * @param str
     * @param strLength
     * @return
     */
    private static String addZeroForStr(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append("0").append(str);
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

}
