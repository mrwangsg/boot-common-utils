package hello.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @创建人 sgwang
 * @name ErrorFlux
 * @user Anti
 * @创建时间 2020/3/22
 * @描述 handle 错误消息
 * concatWith 类似字符串处理 序列后追加操作
 */
public class ErrorFlux {

    // 1. 可以只处理其中包含的正常消息，
    // 2. 也可以同时处理错误消息和完成消息
    public static void createErrorFun() {
        Flux.just("------------------- createErrorFun -------------------").subscribe(System.out::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);
    }

    // 异常时，返回默认值
    public static void onErrorReturnFun() {
        Flux.just("------------------- onErrorReturnFun -------------------").subscribe(System.out::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);
    }

    // 异常时，返回自定义序列
    public static void switchOnErrorFun() {
        Flux.just("------------------- switchOnErrorFun -------------------").subscribe(System.out::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .switchOnError(Mono.just(0))
                .subscribe(System.out::println);
    }

    // 根据不同的异常类型, 选择要使用的产生元素的流
    public static void onErrorResumeWithFun() {
        Flux.just("------------------- onErrorResumeWithFun -------------------").subscribe(System.out::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResumeWith(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);
    }

    public static void retryFun() {
        Flux.just("------------------- retryFun -------------------").subscribe(System.out::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        ErrorFlux.createErrorFun();
        ErrorFlux.onErrorReturnFun();
        ErrorFlux.switchOnErrorFun();
        ErrorFlux.onErrorResumeWithFun();
        ErrorFlux.retryFun();
    }

}
