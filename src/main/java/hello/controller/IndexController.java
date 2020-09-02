package hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

    public static class Key {
        String title;

        public Key(String title) {
            this.title = title;
        }
    }

    public static void main(String[] args) {
        String str = "12";

        System.err.println(str.indexOf("hsb"));
        System.err.println(str.substring("hsb".length()));
    }

}
