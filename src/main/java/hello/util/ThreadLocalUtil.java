package hello.util;

/**
 * @创建人 sgwang
 * @name ThreadLocalUtil
 * @user Anti
 * @创建时间 2020/7/26
 * @描述
 */
public class ThreadLocalUtil {

    /**
     * 线程左节点变量
     */
    private static ThreadLocal<String> threadLocalLeft = new ThreadLocal<>();

    /**
     * 线程有节点变量
     */
    private static ThreadLocal<String> threadLocalRight = new ThreadLocal<>();

    /**
     * 初始化动作，也可以不做初始化
     *
     * @param initValue
     */
    public static void initThreadLocalLeft(String initValue) {
        ThreadLocalUtil.threadLocalLeft = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return initValue;
            }
        };
    }

    /**
     * 初始化动作，也可以不做初始化
     *
     * @param initValue
     */
    public static void initThreadLocalRight(String initValue) {
        ThreadLocalUtil.threadLocalLeft = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return initValue;
            }
        };
    }

    /**
     * 设置，线程变量值
     *
     * @param leftValue
     */
    public static void setValueLeft(String leftValue) {
        ThreadLocalUtil.threadLocalLeft.set(leftValue);
    }

    /**
     * 获取，线程变量值
     *
     * @return
     */
    public static String getValueLeft() {
        return threadLocalLeft.get();
    }

    /**
     * 清除，线程变量值。不清除，可能会导致内存泄漏，涉及到弱引用。
     *      ThreadLocal内存泄漏的根源是：由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key的value就会导致内存泄漏，而不是因为弱引用
     * 研究对象有ThreadLocal、Thread、Thread.ThreadLocalMap<ThreadLocal, value>
     * 1. 若Map中key使用强引用：当ThreadLocal引用释放回收时，ThreadLocalMap还持有ThreadLocal的强引用，“如果”没有手动删除，ThreadLocal不会被回收，导致Entry内存泄漏
     * 2. 若Map中key使用弱引用：当ThreadLocal引用释放回收时，由于ThreadLocalMap持有ThreadLocal的弱引用，“即使”没有手动删除，ThreadLocal也会被回收；
     *      value在下一次ThreadLocalMap调用set,get，remove的时候会被清除。
     */
    public static void clearThreadLocalLeft() {
        threadLocalLeft.remove();
    }


    public static void setValueRight(String rightValue) {
        ThreadLocalUtil.threadLocalRight.set(rightValue);
    }


    public static String getValueRight() {
        return threadLocalRight.get();
    }


    public static void clearThreadLocalRight() {
        threadLocalRight.remove();
    }
}
