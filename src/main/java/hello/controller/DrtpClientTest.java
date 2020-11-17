package hello.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import com.kingstar.bcc.BccClientManager;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

/**
 * @创建人 sgwang
 * @name DrtpClientTest
 * @user shiguang.wang
 * @创建时间 2020/10/20
 * @描述
 */
public class DrtpClientTest {

    /**
     * 消息标准编码 GBK
     */
    public static final String CPACK_MSG_GBK = "GBK";

    private static String cpackPath = "/gphome/gpweb/deploy/apps/adapter-czrcb-comstar-1.0/drtp-lib/cpack.dat";

    // 第一个参数：配置文件路径，第二参数：cpack.dat路径，第三参数：功能号
    public static void main(String[] args) throws Exception {

        System.err.println(Arrays.toString(args));

        //读取classpath下的XXX.setting，不使用变量
        Setting setting = new Setting(FileUtil.file(args[0]), CharsetUtil.CHARSET_UTF_8, true);

        // cpack.dat依赖路径
        String cpackPath = args[1];

        // 初始化drtp连接信息
        Map<String, String> basicMap = setting.getMap("basic");
        String drtpIp = basicMap.get("drtp.ip");
        int drtpPort = Integer.valueOf(basicMap.get("drtp.port"));
        int branchNo = Integer.valueOf(basicMap.get("drtp.branchNo"));
        int functionNo = Integer.valueOf(basicMap.get("drtp.functionNo"));

        // 设置客户端接入个数上限
        if (!BccClientManager.setCltInit(1)) {
            System.out.println("setCltInit(1)失败，这是不可能的，因为这个进程这里首次初试化!");
            return;
        }

        // 初始化drtp节点
        int drtpNo = BccClientManager.addDrtpNode(drtpIp, drtpPort);
        long iHandle = BccClientManager.newXpackHandle(cpackPath);

        // 设置功能号
        if (args[2].endsWith("10001")) {
            test_10001(iHandle, setting);

        } else if (args[2].endsWith("1007491")) {
            test_1007491(iHandle, setting);

        } else if (args[2].endsWith("1007492")) {
            test_1007492(iHandle, setting);

        } else if (args[2].endsWith("1007493")) {
            test_1007493(iHandle, setting);

        } else if (args[2].endsWith("1007494")) {
            test_1007494(iHandle, setting);

        } else if (args[2].endsWith("111000314")) {
            test_111000314(iHandle, setting);

        } else if (args[2].endsWith("111000313")) {
            test_111000313(iHandle, setting);

        } else if (args[2].endsWith("111000312")) {
            test_111000312(iHandle, setting);

        } else if (args[2].endsWith("111000311")) {
            test_111000311(iHandle, setting);

        } else {
            System.out.println("不存在相应接口！");
            return;
        }

        final int[] errCode = {0};
        final byte[] errMessage = new byte[1024];
        if (!BccClientManager.callRequest(iHandle, drtpNo, branchNo, functionNo, 6000, errCode, errMessage)) {
            System.out.printf("通过节点%d(%s:%d)向服务器(%d,%d)调用 10000 功能失败\n", drtpNo, drtpIp, drtpPort, branchNo, functionNo);
            try {
                System.out.println(errCode.length);
                System.out.println(errCode[0] + "##" + convertBytesToStr(errMessage) + "\n");
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else {
            final int[] iretCode = {0};
            final int[] ireccnt = {0};
            BccClientManager.getClt().GetRetCode(iHandle, iretCode); // 应该调用成功的
            BccClientManager.getClt().GetRecordCount(iHandle, ireccnt); // 应该调用成功的，且ireccnt返回为1，即单包记录返回
            System.out.printf("通过节点%d(%s:%d)向服务器(%d,%d)调用 10000 功能成功:", drtpNo, drtpIp, drtpPort, branchNo, functionNo);
            System.out.println("返回码=" + iretCode[0] + "返回记录数=" + ireccnt[0] + "\n");
        }

    }

    private static void test_111000311(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 111000311);
        Map<String, String> configMap = setting.getMap("111000311");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            setCpackValue(iHandle, entry);
        }
    }

    private static void test_111000312(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 111000312);
        Map<String, String> configMap = setting.getMap("111000312");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            setCpackValue(iHandle, entry);
        }
    }

    private static void test_111000313(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 111000313);

        Map<String, String> configMap = setting.getMap("111000313");

        // 调用者名称，首包==0
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            if (entry.getKey().equals("rows")){
                continue;
            }
            setCpackValue(iHandle, entry);
        }

        // 后续发包个数
        String rows = configMap.getOrDefault("rows", "0");
        for (int indexRow = 1; indexRow <= Integer.valueOf(rows); indexRow++) {
            String arrRowIndex = "111000313" + "=" + indexRow;
            Map<String, String> rowConfigMap = setting.getMap(arrRowIndex);
            for (Map.Entry<String, String> entry : rowConfigMap.entrySet()) {
                setCpackValueByIndexRow(iHandle, entry, indexRow);
            }
        }

    }

    private static void test_111000314(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 111000314);

        Map<String, String> configMap = setting.getMap("111000314");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            if (entry.getKey().equals("rows")){
                continue;
            }
            setCpackValue(iHandle, entry);
        }

        // 后续发包个数
        String rows = configMap.getOrDefault("rows", "0");
        for (int indexRow = 1; indexRow <= Integer.valueOf(rows); indexRow++) {
            String arrRowIndex = "111000314" + "=" + indexRow;
            Map<String, String> rowConfigMap = setting.getMap(arrRowIndex);
            for (Map.Entry<String, String> entry : rowConfigMap.entrySet()) {
                setCpackValueByIndexRow(iHandle, entry, indexRow);
            }
        }

    }

    private static void test_1007494(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 1007494);
        Map<String, String> configMap = setting.getMap("1007494");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            setCpackValue(iHandle, entry);
        }
    }

    private static void test_1007493(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 1007493);
        Map<String, String> configMap = setting.getMap("1007493");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            setCpackValue(iHandle, entry);
        }
    }

    private static void test_1007492(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 1007492);
        Map<String, String> configMap = setting.getMap("1007492");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            setCpackValue(iHandle, entry);
        }
    }

    private static void test_1007491(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 1007491);
        Map<String, String> configMap = setting.getMap("1007491");

        // 调用者名称
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            setCpackValue(iHandle, entry);
        }
    }

    private static void test_10001(long iHandle, Setting setting) throws UnsupportedEncodingException {
        BccClientManager.setRequestType(iHandle, 10001);

        // 调用者名称
        BccClientManager.setStringFieldByName(iHandle, 0, "vslong_3", "202020200123");

        BccClientManager.setStringFieldByName(iHandle, 0, "vsshort_17", "1");
        BccClientManager.setStringFieldByName(iHandle, 0, "vsshort_16", "insert");

        BccClientManager.setDoubleFieldByName(iHandle, 0, "vsshort_15", Double.valueOf("200.01"));
        BccClientManager.setIntFieldByName(iHandle, 0, "vsshort_14", Integer.valueOf("4"));
        BccClientManager.setStringFieldByName(iHandle, 0, "vsshort_13", "20201020");
        BccClientManager.setStringFieldByName(iHandle, 0, "vsshort_12", "16:24:00");
        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_9", "c");

        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_8", "31");
        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_7", "31");
        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_6", "");
        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_5", "31");

        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_4", "code200");
        BccClientManager.setStringFieldByName(iHandle, 0, "vssmall_3", "");
        BccClientManager.setStringFieldByName(iHandle, 0, "vsshort_19", "");
        BccClientManager.setStringFieldByName(iHandle, 0, "sbill_no", "CUSTOMER");
    }

    private static void setCpackValue(long iHandle, Map.Entry<String, String> entry) throws UnsupportedEncodingException {
        String[] temp = entry.getValue().split(",");

        if (temp[2].equals("$empty")) {
            temp[2] = "";
        }

        if (temp[1].equals("str") || temp[1].equals("chr")) {
            BccClientManager.setStringFieldByName(iHandle, 0, temp[0], temp[2]);

        } else if (temp[1].equals("int")) {
            BccClientManager.setIntFieldByName(iHandle, 0, temp[0], Integer.valueOf(temp[2]));

        } else if (temp[1].equals("dou")) {
            BccClientManager.setDoubleFieldByName(iHandle, 0, temp[0], Double.valueOf(temp[2]));

        }
    }

    private static void setCpackValueByIndexRow(long iHandle, Map.Entry<String, String> entry, int indexRow) throws UnsupportedEncodingException {
        String[] temp = entry.getValue().split(",");

        if (temp[2].equals("$empty")) {
            temp[2] = "";
        }

        if (temp[1].equals("str") || temp[1].equals("chr")) {
            BccClientManager.setStringFieldByName(iHandle, indexRow, temp[0], temp[2]);

        } else if (temp[1].equals("int")) {
            BccClientManager.setIntFieldByName(iHandle, indexRow, temp[0], Integer.valueOf(temp[2]));

        } else if (temp[1].equals("dou")) {
            BccClientManager.setDoubleFieldByName(iHandle, indexRow, temp[0], Double.valueOf(temp[2]));

        }
    }

    /**
     * cpack string字节码处理
     * 所有从C端返回或drtp依赖库内部抛出的字节，都采用了gbk编码
     *
     * @param bytes
     * @return String
     */
    public static String convertBytesToStr(byte[] bytes) throws UnsupportedEncodingException {
        for (int length = 0; length < bytes.length; length++) {
            if ('\0' == bytes[length]) {
                return new String(bytes, 0, length, CPACK_MSG_GBK);
            }
        }

        return null;
    }

}
