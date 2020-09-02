package hello.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @创建人 sgwang
 * @name MessageHelper
 * @user shiguang.wang
 * @创建时间 2020/8/11
 * @描述 用于生成全局流水号
 */
public class MessageHelper {

    /**
     * 原始报文长度
     */
    private static final int PACK_MSG_LEN = 20;
    /**
     * 16进制报文长度
     */
    private static final int PARSE_MSG_LEN = 72 >> 2;
    /**
     * 报文分组转换为16进制后的长度
     */
    private static final int SUB_MSG_LEN = 24 >> 2;
    /**
     * 16进制
     */
    private static final int HEX = 16;
    /**
     * 传入的日期长度
     */
    private static final int DATE_LEN = 6;
    /**
     * 日期转换为16进制后的长度
     */
    private static final int DATE_CONVERT_LEN = HEX >> 2;
    /**
     * 一天的毫秒数
     */
    private static final int MSECS_PER_DAY = 60 * 60 * 24 * 1000;
    /**
     * 系统编号长度
     */
    private static final int SYS_CODE_LEN = 4;

    /**
     * 以2000年1月1号作为基准日期
     */
    private static final String BASEDATE = "20000101";
    /**
     * 年份开始前缀
     */
    private static final String YEAR_START_PREFIX = "20";
    /**
     * 日期格式
     */
    private static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * <p>将原始报文转换为16进制报文
     * 转换规则：原始报文为“系统编号（4位）+序列号（16位）”
     * 转换后的16进制报文为“(系统编号+序列号)（18位16进制）”
     * </p>
     * 其中：报文分组转换的截取方式是(7，7，6)
     *
     * @param msg 原始报文
     * @return
     */
    public static String Global_SerialNo_Str2bin(String msg) {
        if (msg == null || msg.length() != PACK_MSG_LEN) {
            throw new IllegalArgumentException("Pack message length must equal " + PACK_MSG_LEN);
        }
        if (!Pattern.matches("\\d+", msg)) {
            throw new IllegalArgumentException("Message value must number");
        }
        StringBuilder submsgs = new StringBuilder();
        //报文截取位置
        Integer[] positions = new Integer[]{0, 7, 14, 20};
        for (int i = 1; i <= 3; i++) {
            String substr = msg.substring(positions[i - 1], positions[i]);
            Integer t = Integer.valueOf(substr);
            submsgs.append(padding(Integer.toHexString(t), SUB_MSG_LEN));
        }
        return submsgs.toString();
    }

    /**
     * 将16进制报文转换为原始报文
     * 转换规则：16进制报文为“系统编号（6位16进制）+ 交易日期（4位16进制）”
     * 转换后的原始报文为“系统编号（4位）+序列号（16位）”
     *
     * @param msg
     * @return
     */
    public static String Global_SerialNo_Bin2str(String msg) {
        if (msg == null || msg.length() != PARSE_MSG_LEN) {
            throw new IllegalArgumentException("Parse message length must equal " + PARSE_MSG_LEN);
        }

        StringBuilder submsgs = new StringBuilder();
        //报文截取位置
        Integer[] positions = new Integer[]{0, 6, 12, 18};
        for (int i = 1; i <= 3; i++) {
            String substr = msg.substring(positions[i - 1], positions[i]);
            Integer t = 0;
            try {
                t = Integer.valueOf(substr, HEX);
                submsgs.append(t.toString());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Message must be hex string");
            }
        }
        return submsgs.toString();
    }

    /**
     * 计算6位日期与2000年1月1号差值的偏移量并转换
     *
     * @param datestr
     * @return
     */
    public static String Global_Jioyrq_Str2bin(String datestr) {
        return dateOffset(datestr);
    }

    /**
     * 根据偏移量得到6位日期
     *
     * @param dateOffsetStr
     * @return
     */
    public static String Global_Jioyrq_Bin2str(String dateOffsetStr) {
        Integer t = 0;
        try {
            t = Integer.valueOf(dateOffsetStr, HEX);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Date offset must be hex string");
        }
        return getDate(t);
    }

    /**
     * 字符串长度不够在其前面补0
     *
     * @param str
     * @param targetLen 目标长度
     * @return
     */
    private static String padding(String str, int targetLen) {
        int n = targetLen - str.length();
        for (int i = 0; i < n; i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 计算传入日期与基准日期之间的差值
     *
     * @param datestr
     * @return
     */
    private static String dateOffset(String datestr) {
        if (datestr == null || datestr.trim().length() != DATE_LEN) {
            throw new IllegalArgumentException("Date value can not be null or length must equal " + DATE_LEN);
        }

        long diff = datediff(YEAR_START_PREFIX + datestr, BASEDATE);

        return padding(Long.toHexString(diff), DATE_CONVERT_LEN);
    }

    /**
     * 获取日期并截取后6位
     *
     * @param offset
     * @return
     */
    private static String getDate(int offset) {
        long time = parseDate(BASEDATE) + Long.valueOf(offset) * MSECS_PER_DAY;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(new Date(time)).substring(YEAR_START_PREFIX.length());
    }

    /**
     * 比较两个日期之间的差值
     *
     * @param endDate
     * @param startDate
     * @return
     */
    private static long datediff(String endDate, String startDate) {
        long time = parseDate(endDate) - parseDate(startDate);
        return time / MSECS_PER_DAY;
    }

    /**
     * 获取日期对应的时间数
     *
     * @param datestr
     * @return
     */
    private static long parseDate(String datestr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        long time = 0;
        try {
            time = simpleDateFormat.parse(datestr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将报文分成两部分，分别是：<br/>
     * 数组一中存放“系统编号（4位）+ 序列号（16位）”，
     * 数组二中存放“交易日期（6位）”
     *
     * @param msg
     * @return
     */
    private static String[] splitMsg(String msg) {
        String[] strs = new String[2];
        String before = msg.substring(0, SYS_CODE_LEN);
        String middle = msg.substring(SYS_CODE_LEN, SYS_CODE_LEN + DATE_LEN);
        String end = msg.substring(SYS_CODE_LEN + DATE_LEN, msg.length());
        strs[0] = before + end;
        strs[1] = middle;
        return strs;
    }

    /**
     * 合并日期
     *
     * @param msg
     * @param datestr
     * @return
     */
    private static String combineDate(String msg, String datestr) {
        String before = msg.substring(0, SYS_CODE_LEN);
        String end = msg.substring(SYS_CODE_LEN, msg.length());
        return before + datestr + end;
    }

    //////////////////////////////////////////////////////////
    public static String Str2bin(String msg) {
        String submsgs = new String();

        if (Integer.valueOf(msg) > 255) {
            throw new IllegalArgumentException("不能大于 255 ");
        }

        Integer t = Integer.valueOf(msg);
        submsgs = Integer.toHexString(t); //转换byte编码
        return submsgs.toString();
    }

    public static String Bin2str(String msg) {
        String submsgs = new String();

        Integer t = Integer.valueOf(msg);
        submsgs = padding(t.toString(), 3);    //返回字符串，不够三位前面补0
        return submsgs.toString();
    }

    /////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        String s = "12341234567891234567";

        String h = "12d4cb45b353039447";

        String datestr = "160201";
        String dateoffset = "16f3";
        System.out.println(Global_SerialNo_Str2bin(s));
        System.out.println(Global_SerialNo_Bin2str(h));
        System.out.println(Global_Jioyrq_Str2bin(datestr));
        System.out.println(Global_Jioyrq_Bin2str(dateoffset));
    }
}

