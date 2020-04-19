package hello.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import hello.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人 sgwang
 * @name DataCenter
 * @user Anti
 * @创建时间 2020/4/18
 * @描述
 */
public class DataCenter {

    public static Map<String, Map<String, JSON>> getStepsMapData() {
        Map<String, Map<String, JSON>> stepsMap = new ConcurrentHashMap<>();

        Map<String, JSON> temp01 = new ConcurrentHashMap<>();
        temp01.put("response", JSON.parseObject("{\"password\":\"123456\",\"username\":\"wangsg\"}"));
        stepsMap.put("step01", temp01);

        Map<String, JSON> temp02 = new ConcurrentHashMap<>();
        temp02.put("params", JSON.parseArray("[{\"Content-Type\":\"application/json\"},{\"Length\":\"100\"}]"));
        stepsMap.put("step02", temp02);

        Map<String, JSON> temp03 = new ConcurrentHashMap<>();
        temp03.put("query", JSON.parseArray("[\"demo\",\"test\"]"));
        stepsMap.put("step03", temp03);

//        这种情况不符合JSON格式，不存在
//        Map<String, JSON> temp04 = new ConcurrentHashMap<>();
//        temp04.put("rest", JSON.parseObject("just a string"));
//        stepsMap.put("step04", temp04);

        Map<String, JSON> temp05 = new ConcurrentHashMap<>();
        temp05.put("arrOfObjType", JSON.parseArray("[{\"user01\":{\"password\":\"123456\",\"username\":\"wangsg\"}}]"));
        stepsMap.put("step05", temp05);

        Map<String, JSON> temp06 = new ConcurrentHashMap<>();
        temp06.put("arrOfArrType", JSON.parseObject("{\"userList\":[{\"password\":\"123456\",\"username\":\"wangsg\"},{\"password\":\"just 123456\",\"username\":\"just name\"}]}"));
        stepsMap.put("step06", temp06);

        // "step01.response.username == wangsg"
        // "step02.params[0].Content-Type == wangsg"
        // "step03.query[0] == demo"
        // "step04.rest == just a string""
        // "step05.arrOfObjType[0].user01.password == 123456"
        // "step06.arrOfArrType.userList[0].password == 123456"

        return stepsMap;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("wangsg");
        user.setPassword("123456");

        System.err.println(JSON.toJSONString(user));

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map01 = new HashMap<String, String>();
        map01.put("Content-Type", "application/json");
        Map<String, String> map02 = new HashMap<String, String>();
        map02.put("Length", "100");
        list.add(map01);
        list.add(map02);
        System.err.println(JSON.toJSONString(list));

        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        System.err.println(array.get(0));

        List<String> strList = new ArrayList<>();
        strList.add("demo");
        strList.add("test");
        System.err.println(JSON.toJSONString(strList));

        System.err.println(JSON.toJSONString("just a string"));

        List<Map<String, User>> list02 = new ArrayList<>();
        Map<String, User> map021 = new HashMap<String, User>();
        map021.put("user01", user);
        list02.add(map021);
        System.err.println(JSON.toJSONString(list02));

        Map<String, List<User>> mapListUser = new ConcurrentHashMap<>();
        List<User> listUser = new ArrayList<>();
        User user02 = new User();
        user02.setUsername("just name");
        user02.setPassword("just 123456");
        listUser.add(user);
        listUser.add(user02);
        mapListUser.put("userList", listUser);
        System.err.println(JSON.toJSONString(mapListUser));
    }

}
