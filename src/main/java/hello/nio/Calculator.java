package hello.nio;

/**
 * @创建人 sgwang
 * @name Calculator
 * @user 91119
 * @创建时间 2019/7/28
 * @描述
 */
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
public final class Calculator {
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public static Object cal(String expression) throws ScriptException{
        return jse.eval(expression);
    }
}

