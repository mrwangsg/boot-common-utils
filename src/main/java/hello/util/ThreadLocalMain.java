package hello.util;

/**
 * @创建人 sgwang
 * @name ThreadLocalMain
 * @user Anti
 * @创建时间 2020/7/26
 * @描述
 */
public class ThreadLocalMain {

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                ThreadLocalUtil.setValueLeft("left_1");
                System.out.println("thread1:" + ThreadLocalUtil.getValueLeft());
                ThreadLocalUtil.clearThreadLocalLeft();

                ThreadLocalUtil.setValueRight("right_1");
                System.out.println("thread1:" + ThreadLocalUtil.getValueRight());
                ThreadLocalUtil.clearThreadLocalRight();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                ThreadLocalUtil.setValueLeft("left_2");
                System.out.println("thread2:" + ThreadLocalUtil.getValueLeft());
                ThreadLocalUtil.clearThreadLocalLeft();

                ThreadLocalUtil.setValueRight("right_2");
                System.out.println("thread2:" + ThreadLocalUtil.getValueRight());
                ThreadLocalUtil.clearThreadLocalRight();
            }
        }.start();

    }
}
