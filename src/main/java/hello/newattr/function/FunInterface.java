package hello.newattr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @创建人 sgwang
 * @name FunInterface
 * @user Anti
 * @创建时间 2020/3/22
 * @描述 四大核心 内置 函数式接口
 * {
 * Consumner<T>、消费型接口、void accept(T t)
 * Supplier<T>、供给型接口、T get(void)
 * Function<T , R>、函数型接口、R apply（T t）
 * Predicate<T>、断言型接口、boolean test(T t)
 * }
 */
public class FunInterface {

    /**
     * 消费型接口 Consumer<T>
     * void accept(T t)
     */
    public static void testConsumer() {
        consumerFun(500, (x) -> System.out.println(x));
    }

    /**
     * 供给型接口，Supplier<T>
     * T get(void)
     */
    public static void testSupplier() {
        final Random ran = new Random();
        List<Integer> list = supplierFun(3, () -> ran.nextInt(10));

        list.forEach(System.out::println);
    }

    /**
     * 函数型接口：Function<R, T>
     * R apply（T t）
     */
    public static void testFunction() {
        String s = functionFun(" asdf ", x -> x.substring(0, 2));
        System.out.println(s);
        String s1 = functionFun(" asdf ", x -> x.trim());
        System.out.println(s1);
    }

    /**
     * 断言型接口：Predicate<T>
     * boolean test(T t)
     */
    public static void testPredicate() {
        List<Integer> list = new ArrayList<>();
        list.add(102);
        list.add(172);
        list.add(13);

        List<Integer> resList = predicateFun(list, x -> (x > 100));
        resList.forEach(System.out::println);
    }

    public static void consumerFun(double money, Consumer<Double> c) {
        c.accept(money);
    }

    public static List<Integer> supplierFun(int sum, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            list.add(sup.get());
        }
        return list;
    }

    public static String functionFun(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    public static List<Integer> predicateFun(List<Integer> list, Predicate<Integer> pre) {
        List<Integer> temp = new ArrayList<>();
        for (Integer value : list) {
            if (pre.test(value))
                temp.add(value);
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println("-------------- 分割线 --------------");
        FunInterface.testConsumer();

        System.out.println("-------------- 分割线 --------------");
        FunInterface.testSupplier();

        System.out.println("-------------- 分割线 --------------");
        FunInterface.testFunction();

        System.out.println("-------------- 分割线 --------------");
        FunInterface.testPredicate();
    }
}
