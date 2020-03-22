package hello.demo;

import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * @创建人 sgwang
 * @name Operator
 * @user Anti
 * @创建时间 2020/3/21
 * @描述 操作符
 */
public class Operator {

    // 把当前流序列的元素, 收集到 【集合】 中
    public static void bufferFun() {
        Flux.just("--------------------- bufferFun() ---------------------").subscribe(System.out::println);
        Flux.range(1, 40).buffer(20).subscribe(System.out::println);
        Flux.intervalMillis(100).bufferMillis(1001).take(2).toStream().forEach(System.out::println);
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.just("--------------------- bufferFun() ---------------------").subscribe(System.out::println);
    }

    // 对流中包含的元素进行【过滤】, 只留下满足 Predicate 指定条件的 元素
    public static void filterFun() {
        Flux.just("--------------------- filterFun() ---------------------").subscribe(System.out::println);
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.just("--------------------- filterFun() ---------------------").subscribe(System.out::println);
    }

    // 把当前流中的元素与另外一个流中的元素按照一对一的方式进行【合并成一对】
    public static void zipWithFun() {
        Flux.just("--------------------- zipWithFun() ---------------------").subscribe(System.out::println);
        Flux.just("a", "b", "e").zipWith(Flux.just("c", "d"))
                .subscribe(System.out::println);
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);
        Flux.just("--------------------- zipWithFun() ---------------------").subscribe(System.out::println);
    }

    // take 系列操作符用来从当前流中提取元素。提取的方式可以有很多种。
    public static void takeFun() {
        Flux.just("--------------------- takeFun() ---------------------").subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i % 2 == 1).subscribe(System.out::println);
        Flux.range(1, 1000).take(3).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(4).subscribe(System.out::println);
        Flux.range(1, 10).takeWhile(i -> i % 2 == 1).subscribe(System.out::println);
        Flux.just("--------------------- takeFun() ---------------------").subscribe(System.out::println);
    }

    // reduce 对流中包含的所有元素进行【累计】
    public static void reduceFun() {
        Flux.just("--------------------- reduceFun() ---------------------").subscribe(System.out::println);
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println); // 累计的初始值
        Flux.just("--------------------- reduceFun() ---------------------").subscribe(System.out::println);
    }

    // merge 把多个流【合并】成一个【Flux 序列】
    // merge 按照所有流中元素的实际产生顺序来合并
    // mergeSequential 则按照所有流被订阅的顺序
    public static void mergeFun() {
        Flux.just("--------------------- mergeFun() ---------------------").subscribe(System.out::println);
        Flux.merge(Flux.intervalMillis(0, 10).take(3), Flux.intervalMillis(10, 10).take(3))
                .toStream()
                .forEach(System.out::println);
        Flux.mergeSequential(Flux.intervalMillis(0, 100).take(3), Flux.intervalMillis(50, 100).take(3))
                .toStream()
                .forEach(System.out::println);
        Flux.just("--------------------- mergeFun() ---------------------").subscribe(System.out::println);
    }

    /**
     * ------------------------- 序列 分割线 流 -------------------------
     */

    // 把流中的每个元素转换成一个流，再把所有流中的元素进行合并
    public static void flatMapFun() {
        Flux.just("--------------------- flatMapFun() ---------------------").subscribe(System.out::println);
        Flux.just(2, 5)
                .flatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))
                .toStream()
                .forEach(System.out::println);
        Flux.just("--------------------- flatMapFun() ---------------------").subscribe(System.out::println);
    }

    // 把流中的每个元素转换成一个流，再把所有流进行合并
    public static void concatMapFun() {
        Flux.just("--------------------- concatMapFun() ---------------------").subscribe(System.out::println);
        Flux.just(5, 10)
                .concatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))
                .toStream()
                .forEach(System.out::println);
        Flux.just("--------------------- concatMapFun() ---------------------").subscribe(System.out::println);
    }

    // 把所有流中的最新产生的元素合并成一个新的元素，作为返回结果流中的元素
    public static void combineLatestFun() {
        Flux.just("--------------------- combineLatestFun() ---------------------").subscribe(System.out::println);
        Flux.combineLatest(
                Arrays::toString,
                Flux.intervalMillis(100).take(5),
                Flux.intervalMillis(50, 100).take(5)
        ).toStream().forEach(System.out::println);
        Flux.just("--------------------- combineLatestFun() ---------------------").subscribe(System.out::println);
    }

    public static void main(String[] args) {
        Operator.bufferFun();
        Operator.filterFun();
        Operator.zipWithFun();
        Operator.takeFun();
        Operator.reduceFun();
        Operator.mergeFun();

        Operator.flatMapFun();
        Operator.concatMapFun();
        Operator.combineLatestFun();
    }

}
