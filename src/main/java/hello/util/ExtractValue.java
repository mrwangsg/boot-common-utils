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

}
