package hello.demo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @创建人 sgwang
 * @name SchedulerFlux
 * @user Anti
 * @创建时间 2020/3/22
 * @描述 调度器：操作执行的方式和所在的线程
 */
public class SchedulerFlux {
    /**
     * 1. 当前线程，通过 Schedulers.immediate()方法来创建。
     * 2. 单一的可复用的线程，通过 Schedulers.single()方法来创建。
     * 3. 使用弹性的线程池，通过 Schedulers.elastic()方法来创建。线程池中的线程是可以复用的。
     *      当所需要时，新的线程会被创建。如果一个线程闲置太长时间，则会被销毁。
     *      该调度器适用于 I/O 操作相关的流的处理。
     * 4. 使用对并行操作优化的线程池，通过 Schedulers.parallel()方法来创建。
     *      其中的线程数量取决于 CPU 的核的数量。
     *      该调度器适用于计算密集型的流的处理。
     * 5. 使用支持任务调度的调度器，通过 Schedulers.timer()方法来创建。
     * 6. 从已有的 ExecutorService 对象中创建调度器，通过 Schedulers.fromExecutorService()方法来创建。
     */


    public static void main(String[] args) {
        // publishOn()方法切换的是【操作符】的执行方式
        // subscribeOn()方法切换的是【产生流中元素】时的执行方式
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("{< [%s] %s >}", Thread.currentThread().getName(), x))

                .publishOn(Schedulers.elastic())
                .map(x -> String.format("{< [%s] %s >}", Thread.currentThread().getName(), x))

                .subscribeOn(Schedulers.parallel())
                .toStream()

                .forEach(System.out::println);
        // 运行之后的结果是{< [elastic-2] {< [single-1] parallel-1 >} >}
        // 最内层的线程名字 parallel-1 来自产生流中元素时使用的 Schedulers.parallel()调度器，
        // 中间的线程名称 single-1 来自第一个 map 操作之前的 Schedulers.single()调度器，
        // 最外层的线程名字 elastic-2 来自第二个 map 操作之前的 Schedulers.elastic()调度器。
    }
}
