package hello.util;

import com.alibaba.fastjson.JSON;
import hello.error.ExpressFormatException;

import java.util.Map;

/**
 * @创建人 sgwang
 * @name ExtractValue
 * @user Anti
 * @创建时间 2020/4/18
 * @描述 提取的值 类型 arr、obj、attr
 */
public class ExtractValue {

    public void doStart() throws ExpressFormatException {

        Map<String, Map<String, JSON>> stepsMap = DataCenter.getStepsMapData();

        String expressStr = "step01.response.username";

        ParserUtil.handlerTask(expressStr, stepsMap);

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

        expressStr = "step07.arrOfArrArrType.userListList[0][0].password";
        ParserUtil.handlerTask(expressStr, stepsMap);

        expressStr = "step08.arrOfArrArrArrType.userListListList[0][0][1].password";
        ParserUtil.handlerTask(expressStr, stepsMap);
    }

}
