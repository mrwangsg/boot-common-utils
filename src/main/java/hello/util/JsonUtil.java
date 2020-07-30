package hello.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @创建人 sgwang
 * @name JsonUtil
 * @user shiguang.wang
 * @创建时间 2020/7/30
 * @描述
 */
public class JsonUtil {

    /**
     * 将json字符串转为Map对象.如果json复杂，结果可能是map嵌套map
     *
     * @param jsonStr 入参，json格式字符串
     * @return Map<String, Object> || null
     */
    public static Map<String, Object> jsonStrToMap(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        if (jsonStr != null && !"".equals(jsonStr)) {
            //最外层解析
            JSONObject json = JSONObject.parseObject(jsonStr);
            for (Object key : json.keySet()) {
                Object value = json.get(key);
                //如果内层还是数组的话，继续解析
                if (value instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Iterator<Object> it = ((JSONArray) value).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = (JSONObject) it.next();
                        list.add(jsonStrToMap(json2.toString()));
                    }
                    map.put(value.toString(), list);
                } else {
                    map.put(key.toString(), value);
                }
            }
            return map;
        } else {
            return null;
        }
    }
}
