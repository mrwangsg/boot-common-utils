package hello.newattr.datetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @创建人 sgwang
 * @name DateTimeUtil
 * @user Anti
 * @创建时间 2020/3/22
 * @描述
 */
public class DateTimeUtil {

    //时间转字符串格式化
    public static String localDateTimeToStr(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return dateTime.format(dtf);
    }

    //字符串转时间
    public static LocalDateTime strToLocalDateTime(String dateTimeStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeStr, dtf);
    }

    // localDateTime 转 时间戳(毫秒)
    public static long toLongTimestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    // 时间戳(毫秒) 转 localDateTime
    public static LocalDateTime getByLongTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    // localDateTime 转 Timestamp(时间戳)
    public static Timestamp toTimestamp(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }

    // Timestamp(时间戳) 转 localDateTime
    public static LocalDateTime getByTimestamp(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(DateTimeUtil.localDateTimeToStr(currentTime));

        String dateTimeStr = "2018-07-28 14:11:15";
        System.out.println(DateTimeUtil.strToLocalDateTime(dateTimeStr));

        long longTimestamp = DateTimeUtil.toLongTimestamp(currentTime);
        System.out.println(longTimestamp);
        System.out.println(DateTimeUtil.getByLongTimestamp(longTimestamp));

        Timestamp timestamp = DateTimeUtil.toTimestamp(currentTime);
        System.out.println(timestamp);
        System.out.println(DateTimeUtil.getByTimestamp(timestamp));

        System.out.println(Timestamp.valueOf("2020-03-22 22:39:43.434123"));
    }
}
