package hello.demo;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * @创建人 sgwang
 * @name CreateFlux
 * @user Anti
 * @创建时间 2020/3/21
 * @描述
 */
public class CreateFlux {

    // 静态方法 创建Flux序列
    public static void easyFun() {
        Flux.just("--------------------- easyFun() ---------------------").subscribe(System.out::println);
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        Flux.intervalMillis(1000).subscribe(System.out::println);
        Flux.just("--------------------- easyFun() ---------------------").subscribe(System.out::println);
    }

    // 同步和逐一 产生Flux序列
    public static void generateFun() {
        Flux.just("--------------------- generateFun() ---------------------").subscribe(System.out::println);
        // generate()方法通过同步和逐一的方式来产生 Flux 序列
        // 序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()和 error(Throwable)方法来完成的
        // 逐一生成的含义是在具体的生成逻辑中，next()方法只能最多被调用一次
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        // generate(Callable<S> stateSupplier, BiFunction<S,SynchronousSink<T>,S> generator)，
        // 其中 stateSupplier 用来提供初始的状态对象。
        // 在进行序列生成时，状态对象会作为 generator 使用的第一个参数传入，
        // 可以在对应的逻辑中对该状态对象进行修改, 以供下一次生成时使用。
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(10);
            list.add(value);
            sink.next(value);
            if (list.size() == 4) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
        Flux.just("--------------------- generateFun() ---------------------").subscribe(System.out::println);
    }

    // create()对比generate(), 使用了 FluxSink 对象,
    // FluxSink 支持同步和异步的消息产生, 并且可以在一次调用中产生多个元素
    public static void createFun() {
        Flux.just("--------------------- createFun() ---------------------").subscribe(System.out::println);
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        Flux.just("--------------------- createFun() ---------------------").subscribe(System.out::println);
    }

    public static void main(String[] args) {

        CreateFlux.easyFun();
        CreateFlux.generateFun();
        CreateFlux.createFun();
    }

}
