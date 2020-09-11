package test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建人 sgwang
 * @name DateUtil
 * @user shiguang.wang
 * @创建时间 2020/7/27
 * @描述
 */
public class DateUtil {

    /**
     * 标准时间戳，格式化
     */
    public static final String DATETIME_STANDAR_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时间无冒号，格式化
     */
    public static final String TIME_NO_COLON_FORMAT = "yyyy-MM-dd HHmmssSSS";

    /**
     * 日期无横线，格式化
     */
    public static final String DATE_NO_LINE_FORMAT = "yyyyMMdd HH:mm:ss.SSS";

    /**
     * 日期无横线，时间无冒号，格式化
     */
    public static final String DATE_NO_LINE_TIME_NO_COLON_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * 仅仅日期，并无横线，格式化
     */
    public static final String ONLY_DATE_NO_LINE_TIME_FORMAT = "yyyyMMdd";

    /**
     * 根据时间格式字符串，转为Date时间
     *
     * @param strTime
     * @param formatStr
     * @return Date || null
     */
    public static Date parseDate(String strTime, String formatStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(strTime);
    }

    /**
     * 根据Date时间，转为时间格式字符串
     *
     * @param date
     * @return String || null
     */
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    public static void main(String[] args) {
        Date currentTime = new Date();

        System.err.println(DateUtil.formatDate(currentTime, DateUtil.DATETIME_STANDAR_FORMAT));
        System.err.println(DateUtil.formatDate(currentTime, DateUtil.DATE_NO_LINE_FORMAT));
        System.err.println(DateUtil.formatDate(currentTime, DateUtil.TIME_NO_COLON_FORMAT));
        System.err.println(DateUtil.formatDate(currentTime, DateUtil.DATE_NO_LINE_TIME_NO_COLON_FORMAT));

    }

}
