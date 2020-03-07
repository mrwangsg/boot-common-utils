package hello.utils;

import hello.HttpResult;

import java.util.List;

/**
 * @Author :  sgwang
 * @Version :  1.0
 * @Title :  GenericsUtil
 * @CreateDate :  2020/3/7 22:44
 * @Description :  TODO
 */
public class GenericsUtil {

    /**
     * 集合<? extends Enum> 通配符限定, 限定只能使用限定类的方法. (即：不能操作与对象有关的方法)
     *
     * @param enumList
     */
    public static void getEnumPropName(List<? extends Enum> enumList) {
        for (Enum temp : enumList) {
            System.err.println(temp.name());
        }
    }

    /**
     * <T> void 这里<T> 仅仅是因为参数用了T泛型，所有需要声明一下.
     *
     * @param <T> temp
     */
    public static <T> void justPrint(T temp) {
        System.err.println(temp.toString());
    }

    /**
     * <T> HttpResult<T> 第一个<T> 仅仅是因为参数用了T泛型，所有需要声明一下；第二个<T> 是因为返回类型属于泛型
     * @param <T> temp
     * @return HttpResult
     */
    public static <T> HttpResult<T> getHttpResult(T temp) {
        HttpResult<T> res = new HttpResult<T>();
        res.setResult(temp);
        return res;
    }


}
