package hello.demo;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @创建人 sgwang
 * @name HardDiff
 * @user Anti
 * @创建时间 2020/3/22
 * @描述 用于举例子 难以区分的
 * demo_link: https://blog.csdn.net/Mark_Chao/article/details/80810030?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 */
public class HardDiff {

    public static void main(String[] args) {
        HardDiff.testMap();
        Mono.just("-------------- 分割线 ----------------").subscribe(System.out::println);
        HardDiff.testFlatMap();

    }

    public static void testMap() {
        String[] words = new String[]{"Hello", "World"};
        List<String[]> list = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        list.forEach(System.out::println);
    }

    public static void testFlatMap() {
        String[] words = new String[]{"Hello", "World"};
        Arrays.stream(words);
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(str -> Arrays.stream(str))
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);
    }
}
