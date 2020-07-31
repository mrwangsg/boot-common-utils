package hello.main;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @创建人 sgwang
 * @name AsyncTask
 * @user shiguang.wang
 * @创建时间 2020/7/30
 * @描述
 */
public class AsyncTask {

    /**
     * 任务线程池
     */
    final static ListeningExecutorService taskExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

    /**
     * 回调线程池
     */
    final static ListeningExecutorService callbackExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public static void main(String[] args) {
        sample01();
        sample02();
    }

    /**
     * 测试使用：ListenableFuture 和 FutureCallback，任务和回调
     */
    private static void sample01() {
        // 任务task
        ListenableFuture<Boolean> booleanTask = taskExecutorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws InterruptedException {
                Thread.sleep(5000);
                System.err.println("booleanTask: " + "执行任务中");
                return true;
            }
        });

        // 处理task
        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean result) {
                System.err.println("booleanTask: result->" + result + ",    callback->执行回调中");
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, taskExecutorService);
    }

    /**
     * 测试链式调用
     */
    private static void sample02() {
        // 任务task1
        ListenableFuture<Boolean> task1 = taskExecutorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                System.err.println("task01执行通过！");
                return true;
            }
        });

        // 当task1执行完毕会回调执行Function的apply方法，如果有task1有异常抛出，则task2也抛出相同异常，不执行apply
        ListenableFuture<String> task2 = Futures.transform(task1, new Function<Boolean, String>() {
            @Override
            public String apply(@Nullable Boolean beforeResult) {
                System.err.println("task02执行通过！");
                return "task02";
            }
        }, callbackExecutorService);

        ListenableFuture<Integer> task3 = Futures.transform(task2, new Function<String, Integer>() {
            @Override
            public Integer apply(String beforeResult) {
                System.err.println("task03执行通过！");
                return 3;
            }
        }, callbackExecutorService);

        //处理最终的异步任务
        Futures.addCallback(task3, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                System.err.println("task03: result->" + result + ",    callback->执行回调中");
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, callbackExecutorService);
    }

}
