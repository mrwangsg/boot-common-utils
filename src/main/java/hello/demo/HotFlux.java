package hello.demo;

import reactor.core.publisher.Flux;

/**
 * @创建人 sgwang
 * @name HotFlux
 * @user Anti
 * @创建时间 2020/3/22
 * @描述 冷序列的含义是不论订阅者在何时订阅该序列，总是能收到序列中产生的【全部消息】。
 *       热序列，则是在持续不断地产生消息，订阅者只能获取到在【其订阅之后】产生的消息。
 */
public class HotFlux {

    // 原始的序列中包含 5 个间隔为 1 秒的元素。通过 publish()方法把一个 Flux 对象转换成 ConnectableFlux 对象。
    // 方法 autoConnect()的作用是当 ConnectableFlux 对象有一个订阅者时就开始产生消息。
    //
    // 代码 source.subscribe()的作用是订阅该 ConnectableFlux 对象，让其开始产生数据。
    // 接着当前线程睡眠 2 秒钟，
    // 第二个订阅者此时只能获得到该序列中的后 2 个元素，因此所输出的是数字 2 到 4。
    public static void main(String[] args) throws InterruptedException {
        final Flux<Long> source = Flux.intervalMillis(1000)
                .take(5)
                .publish()
                .autoConnect();
        source.subscribe();
        Thread.sleep(2000);
        source.toStream().forEach(System.out::println);
    }
}
