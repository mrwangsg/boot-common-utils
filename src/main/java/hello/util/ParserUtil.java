package hello.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hello.error.ExpressFormatException;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @创建人 sgwang
 * @name ParserUtil
 * @user Anti
 * @创建时间 2020/4/18
 * @描述 仅支持获取value值，1.不支持获取Object或Array；2.不支持嵌套数组格式；3大小写和空格敏感
 * TODO 异常捕获、支持嵌套数组格式
 */
public class ParserUtil {
    static final String type_arr = "arr"; // 数组类型
    static final String type_obj = "obj"; // 对象类型
    static final String type_attr = "attr"; // 属性类型
    static final String step_null_map = "该步骤不存在对应值";

    /**
     * 处理入口，需要原始字符串和数据源
     *
     * @param expressStr
     * @param stepsMap
     * @return
     * @throws ExpressFormatException
     */
    public static Object handlerTask(String expressStr, Map<String, Map<String, JSON>> stepsMap) throws ExpressFormatException {

        // 解析头部固定形式
        Map<String, JSON> scopeMap = ParserUtil.parseStepsExpressStr(expressStr, stepsMap);
        System.err.println("解析头部后：" + expressStr);

        // 解析作用范围固定形式
        expressStr = expressStr.substring(expressStr.indexOf(".") + 1);
        System.err.println("解析范围前：" + expressStr);

        Object result = ParserUtil.parseScopeExpressStr(expressStr, scopeMap);
        System.err.println(result);

        return result;
    }

    /**
     * 解析头部，固定形式{step}.{scope}
     *
     * @param stepsExpressStr
     * @param stepsMap
     * @return Map<String, JSON> scopeMap | 存储<作用域, 对应值>
     */
    private static Map<String, JSON> parseStepsExpressStr(String stepsExpressStr, Map<String, Map<String, JSON>> stepsMap) throws ExpressFormatException {
        String stepStr = stepsExpressStr.substring(0, stepsExpressStr.indexOf("."));

        if (StringUtils.isEmpty(stepStr)) {
            throw new ExpressFormatException();
        }

        Map<String, JSON> scopeMap = stepsMap.get(stepStr);
        if (scopeMap == null) {
            throw new ExpressFormatException(ParserUtil.step_null_map);
        }

        return scopeMap;
    }

    /**
     * 解析作用范围，有三种形式："{scope}."  "{scope}[num]"  "{scope}[num]."
     *
     * @param scopeExpressStr
     * @param scopeMap
     */
    private static Object parseScopeExpressStr(String scopeExpressStr, Map<String, JSON> scopeMap) {
        String scopeStr;

        String scopeType = ParserUtil.diffType(scopeExpressStr);
        if (ParserUtil.type_obj.equals(scopeType)) {
            scopeStr = scopeExpressStr.substring(0, scopeExpressStr.indexOf("."));
            String otherExpressStr = scopeExpressStr.substring(scopeExpressStr.indexOf(".") + 1);

            return ParserUtil.parseOtherExpressStr(otherExpressStr, scopeMap.get(scopeStr));
        } else {
            int numIndex = ParserUtil.arrStrOfIndex(scopeExpressStr);
            scopeStr = scopeExpressStr.substring(0, scopeExpressStr.indexOf("["));
            JSONArray arrJSON = (JSONArray) scopeMap.get(scopeStr);

            if (ParserUtil.arrOfObjType(scopeExpressStr)) {
                // 对于这种"{scope}[num]."形式，截取"]."之后字符串
                String otherExpressStr = scopeExpressStr.substring(scopeExpressStr.indexOf("]") + 2);
                return ParserUtil.parseOtherExpressStr(otherExpressStr, (JSON) arrJSON.get(numIndex));
            } else {
                // 对于这种"{scope}[num]"形式，已经可以直接提取值
                return arrJSON.get(numIndex);
            }
        }
    }

    /**
     * 解析范围部分，一般为response、body、params
     *
     * @param otherExpressStr
     * @param otherJSON
     * @return
     */
    private static Object parseOtherExpressStr(String otherExpressStr, JSON otherJSON) {

        // 存在"." || "]" 说明类型为 obj || arr
        if (otherExpressStr.contains(".") || otherExpressStr.contains("]")) {
            String indexType = diffType(otherExpressStr);   // 区别当前 类型
            String keyStr = "";                             // 当前key字符串
            String nextStr = "";                            // 子孩子字符串
            JSON nextJSON = null;                          // 子孩子数据源

            // 类型格式："[num]."或"[num]"
            if (ParserUtil.type_arr.equals(indexType)) {
                int numIndex = ParserUtil.arrStrOfIndex(otherExpressStr);
                keyStr = otherExpressStr.substring(0, otherExpressStr.indexOf("["));

                if (ParserUtil.arrOfObjType(otherExpressStr)) {
                    // otherJSON 是对象类型数组 即"]"之后存在"."
                    JSONArray arrJSON = (JSONArray) ((JSONObject) otherJSON).get(keyStr);
                    nextStr = otherExpressStr.substring(otherExpressStr.indexOf("]") + 2);
                    nextJSON = (JSON) arrJSON.get(numIndex);
                    return ParserUtil.parseOtherExpressStr(nextStr, nextJSON);
                } else {
                    // otherJSON 是基本类型数组 即"]"终止
                    return ((JSONArray) otherJSON).get(numIndex);
                }
            } else if (ParserUtil.type_obj.equals(indexType)) {
                keyStr = otherExpressStr.substring(0, otherExpressStr.indexOf("."));
                nextStr = otherExpressStr.substring(otherExpressStr.indexOf(".") + 1);
                nextJSON = (JSON) ((JSONObject) otherJSON).get(keyStr);
                return ParserUtil.parseOtherExpressStr(nextStr, nextJSON);
            }

        } else {
            // 不存在"."或"]" 说明已经是尽头 可以获知value值
            return ((JSONObject) otherJSON).get(otherExpressStr);
        }


        return null;
    }

    /**
     * @描述 根据字符串 返回arr | obj | attr
     * @参数 String
     * @返回值 String 备注：arr | obj | attr
     */
    private static String diffType(String indexStr) {

        if (indexStr.contains(".") && indexStr.contains("]")) {
            if (indexStr.indexOf(".") < indexStr.indexOf("]")) {
                return ParserUtil.type_obj;
            } else {
                return ParserUtil.type_arr;
            }
        } else if (indexStr.contains(".")) {
            return ParserUtil.type_obj;
        } else if (indexStr.contains("]")) {
            return ParserUtil.type_arr;
        }

        return ParserUtil.type_attr;
    }

    /**
     * @描述 获取数组类型字符串中的数字，[num] -> num
     * @参数 String arrStr
     * @返回值 Integer
     */
    private static Integer arrStrOfIndex(String arrStr) {
        String index = arrStr.substring(arrStr.indexOf("[") + 1, arrStr.indexOf("]"));
        return Integer.parseInt(index);
    }

    /**
     * 判断是否为对象类型数组
     * "[num]." 或 "[num]"
     *
     * @param arrStr
     * @return Boolean
     */
    private static Boolean arrOfObjType(String arrStr) {
        if (arrStr.contains(".") && (arrStr.indexOf(".") - 1) == arrStr.indexOf("]")) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws ExpressFormatException {
        Map<String, Map<String, JSON>> stepsMap = DataCenter.getStepsMapData();
        String expressStr = "";

        expressStr = "step01.response.username";
        ParserUtil.handlerTask(expressStr, stepsMap);

        expressStr = "step02.params[0].Content-Type";
        ParserUtil.handlerTask(expressStr, stepsMap);

        expressStr = "step03.query[0]";
        ParserUtil.handlerTask(expressStr, stepsMap);

        expressStr = "step05.arrOfObjType[0].user01.password";
        ParserUtil.handlerTask(expressStr, stepsMap);

        expressStr = "step06.arrOfArrType.userList[0].password";
        ParserUtil.handlerTask(expressStr, stepsMap);
    }

}
