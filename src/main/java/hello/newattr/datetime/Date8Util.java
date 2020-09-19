package hello.newattr.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @创建人 sgwang
 * @name Date8Util
 * @user shiguang.wang
 * @创建时间 2020/9/18
 * @描述 封装时间工具，只针对：本地日期、本地时间。考虑时区复杂性高，且不常用
 */
public class Date8Util {

    /**
     * 标准时间戳，格式化
     */
    public static final String DATE_TIME_STANDAR_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

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
    public static final String ONLY_DATE_NO_LINE_FORMAT = "yyyyMMdd";

    /**
     * 仅仅日期，并无横线，格式化
     */
    public static final String ONLY_TIME_NO_COLON_FORMAT = "yyyyMMdd";

    /**
     * 两位年＋两位月＋两位日，格式化
     */
    public static final String DATE_TWO_NUM_NO_LINE_FORMAT = "yyMMdd";

    /**
     * 根据时间格式字符串，转为LocalDateTime时间
     *
     * @param strTime
     * @param formatStr
     * @return LocalDateTime
     */
    public static LocalDateTime parseNewDate(String strTime, String formatStr) {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern(formatStr);

        return LocalDateTime.parse(strTime, dtFormat);
    }

    /**
     * 根据Date时间，转为时间格式字符串
     *
     * @param dateTime
     * @return String || null
     */
    public static String formatNewDate(LocalDateTime dateTime, String formatStr) {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern(formatStr);

        return dateTime.format(dtFormat);
    }

    /**
     * 本地时间 转 本地时区时间
     *
     * @param dateTime
     * @return ZonedDateTime
     */
    public static ZonedDateTime local2Zoned(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, ZoneId.systemDefault());
    }

    /**
     * 本地时区时间 转 本地时间
     *
     * @param dateTime
     * @return LocalDateTime
     */
    public static LocalDateTime zoned2Local(ZonedDateTime dateTime) {
        return dateTime.toLocalDateTime();
    }

    /**
     * 时间戳对象 转 本地时区时间
     *
     * @param instant
     * @return ZonedDateTime
     */
    public static ZonedDateTime instant2Zoned(Instant instant) {
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 本地时区时间 转 时间戳对象
     *
     * @param dateTime
     * @return Instant
     */
    public static Instant zoned2Instant(ZonedDateTime dateTime) {
        return dateTime.toInstant();
    }

    /**
     * 时间戳对象 转 单位秒
     *
     * @param instant
     * @return long
     */
    public static long instant2Second(Instant instant) {
        return instant.getEpochSecond();
    }

    /**
     * 时间戳对象 转 单位毫秒
     *
     * @param instant
     * @return long
     */
    public static long instant2Milli(Instant instant) {
        return instant.toEpochMilli();
    }

    /**
     * 单位秒 转 时间戳对象
     *
     * @param second
     * @return Instant
     */
    public static Instant second2Instant(long second) {
        return Instant.ofEpochSecond(second);
    }

    /**
     * 单位毫秒 转 时间戳对象
     *
     * @param milli
     * @return Instant
     */
    public static Instant milli2Instant(long milli) {
        return Instant.ofEpochMilli(milli);
    }

    /**
     * 老ApiDate 转 本地时间
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime oldDate2Local(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 本地时间 转 老ApiDate
     *
     * @param dateTime
     * @return Date
     */
    public static Date local2OldDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        ZonedDateTime zonedDT = ZonedDateTime.now();
        System.err.println(zonedDT);
        System.err.println();

        // 测试 localDateTime
        System.err.println("--------- 测试 localDateTime");
        LocalDateTime localDT01 = Date8Util.zoned2Local(zonedDT);
        ZonedDateTime zonedDT01 = Date8Util.local2Zoned(localDT01);
        System.err.println(localDT01);
        System.err.println(zonedDT01);
        System.err.println();

        // 测试 Instant
        System.err.println("--------- 测试 Instant");
        Instant instant02 = Date8Util.zoned2Instant(zonedDT);
        ZonedDateTime zoned02 = Date8Util.instant2Zoned(instant02);
        System.err.println(instant02);
        System.err.println(zoned02);
        System.err.println();

        // 测试 秒
        System.err.println("--------- 测试 秒");
        Instant instant03 = Date8Util.zoned2Instant(zonedDT);
        long second = Date8Util.instant2Second(instant03);
        Instant instant04 = Date8Util.second2Instant(second);
        System.err.println(instant03);
        System.err.println(instant04);
        System.err.println();

        // 测试 毫秒
        System.err.println("-------- 测试 毫秒");
        Instant instant05 = Date8Util.zoned2Instant(zonedDT);
        long milli = Date8Util.instant2Milli(instant05);
        Instant instant06 = Date8Util.milli2Instant(milli);
        System.err.println(instant05);
        System.err.println(instant06);
        System.err.println();

        // 测试 时间格式化
        System.err.println("-------- 测试 时间格式化");
        String localStr = "2020-01-01 00:00:00.000";
        LocalDateTime localDT03 = Date8Util.parseNewDate(localStr, Date8Util.DATE_TIME_STANDAR_FORMAT);
        String localStr01 = Date8Util.formatNewDate(localDT03, Date8Util.DATE_TIME_STANDAR_FORMAT);
        System.err.println(localStr);
        System.err.println(localStr01);
        System.err.println();

        // 测试 Date和LocalDateTime
        System.err.println("-------- 测试 时间格式化");
        LocalDateTime localDT05 = zonedDT.toLocalDateTime();
        Date oldDate = Date8Util.local2OldDate(localDT05);
        LocalDateTime localDT06 = Date8Util.oldDate2Local(oldDate);
        System.err.println(localDT05);
        System.err.println(localDT06);
        System.err.println();

    }

}
