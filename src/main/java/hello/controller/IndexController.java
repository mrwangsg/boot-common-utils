package hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author :  sgwang
 * @Version :  1.0
 * @Title :  IndexController
 * @CreateDate :  2020/3/7 22:41
 * @Description :  TODO
 */
@RestController
public class IndexController {

    @RequestMapping
    public String index() {
        return "Index util !";
    }

    public static void main(String[] args) {
        Set<String> setNames = System.getProperties().stringPropertyNames();
        for (String str : setNames){
            System.out.println(str + ":  " + System.getProperties().getProperty(str));
        }


        HashMap<String, String> map1 = new HashMap<>();
        map1.put("123", "aaa");
        map1.put("23456", "bbb");

        System.out.println("map1的循环遍历");
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey());
        }

        HashMap<String, String> map2 = new HashMap<>(map1.size());
        map2.put("123", "aaa");
        map2.put("23456", "bbb");
        System.out.println("map2的循环遍历");
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            System.out.println(entry.getKey());
        }

        Map<String, String> tempMap = new ConcurrentHashMap<>();

        String str = "1 Set集合介绍\n" +
                "Collection接口可以存放重复元素，也可以存放不重复元素。List可以存放重复元素，Set就是不重复的元素。\n" +
                "通过元素的equals方法，来判断是否为重复元素。\n" +
                "Set集合取出元素的方式可以采用：迭代器，增强 for\n" +
                "\n" +
                "2 HashSet（哈希表）\n" +
                "此类实现了Set接口，由哈希表（实际是HashMap实例）支持。它不保证set的迭代顺序，特别是它不保证该顺序恒久不变。此类允许使用null元素。";
        char[] charArr = str.toCharArray();

        for (int index =0 ;index<charArr.length; index++){
            tempMap.put(String.valueOf(charArr[index]), "test01");
        }
        tempMap.put("中", "test01");
        tempMap.put("国", "test01");
        tempMap.put("汉", "test01");
        tempMap.put("字", "test01");

        Iterator<Map.Entry<String, String>> it = tempMap.entrySet().iterator();
        int index = 0;
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            it.remove();
            tempMap.put(String.valueOf(index), "test002");

            index++;
        }

        System.err.println(index);
    }


}
