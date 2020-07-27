package hello.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @创建人 sgwang
 * @name DateUtil
 * @user shiguang.wang
 * @创建时间 2020/7/27
 * @描述
 */
public class DateUtil {

    /**
     * 时间格式化标准格式
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 使用threadLocal进行隔离
     */
    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateUtil.DATE_FORMAT));

    /**
     * 根据时间格式字符串，转为Date时间
     *
     * @param strTime
     * @return Date || null
     */
    public static Date parseDate(String strTime) {
        Date date = null;
        try {
            date = threadLocal.get().parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据Date时间，转为时间格式字符串
     *
     * @param date
     * @return String || null
     */
    public static String formatDate(Date date) {
        String str = null;
        try {
            str = threadLocal.get().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 清除ThreadLocal，避免缓存泄漏
     */
    public static void clearThreadLocal() {
        threadLocal.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int index = 0; index < count; index++) {
            new Thread(() -> {
                System.err.println("----------- 分割线 --------");
                System.err.println(index);
                Date date = new Date();
                String dateStr = DateUtil.formatDate(date);
                System.err.println(dateStr);

                System.err.println(date);
                System.err.println(DateUtil.parseDate(dateStr));

                DateUtil.clearThreadLocal();
                countDownLatch.countDown();
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        countDownLatch.await();
    }


}
