package demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author :  sgwang
 * @Version :  1.0
 * @Title :  ReflectUtilTest
 * @CreateDate :  2020/3/8 17:43
 * @Description :  TODO
 */
public class ReflectUtilTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 1. 具体类的class属性【还可以用实例.getClass()，不推荐】
        Class userMapperImplClass01 = UserMapperImpl.class;
        // 2. 类的包路径
        Class userMapperImplClass02 = Class.forName("demo.UserMapperImpl");

        // new实例
        UserMapper userMapper = (UserMapper) userMapperImplClass01.newInstance();
        System.out.println(userMapper.getUserById("just user id").toString());

        // 实例方法 注入 参数值
        System.out.println("----------------------------- 分割线 ------------------------------");
        UserMapper funUserMapper = (UserMapper) userMapperImplClass01.newInstance();
        Method funThd = userMapperImplClass01.getDeclaredMethod("getUserById", String.class);
        funThd.setAccessible(true); //暴力访问 (忽略 访问修饰符)
        System.out.println(funThd.invoke(funUserMapper, "方法级别 注入 参数值").toString());

        // 实例属性 注入 属性值
        System.out.println("----------------------------- 分割线 ------------------------------");
        UserMapper propUserMapper = (UserMapper) userMapperImplClass01.newInstance();
        Field propField = userMapperImplClass01.getDeclaredField("user");
        propField.setAccessible(true);
        propField.set(propUserMapper, new User("属性级别 注入 属性值"));
        System.out.println(propUserMapper.getUser().toString());

        // 获取所有方法(包括私有), 并包括参数、返回值
        System.out.println("----------------------------- 分割线 ------------------------------");
        Method[] methods = userMapperImplClass01.getDeclaredMethods();
        for (Method temp : methods) {
            System.out.print("方法名: " + temp.getName());
            System.out.println();
            for (Parameter param : temp.getParameters()) {
                System.out.print("参数名：" + param.getName() + "   修饰符等级：" + param.getModifiers() + "   参数类型：" + param.getType() + ";   ");
            }
            System.out.println();
            System.out.println("    返回类型: " + temp.getReturnType());
        }

        System.out.println("----------------------------- 分割线 ------------------------------");
        System.out.println("继承父类：" + userMapperImplClass01.getSuperclass());
        Class[] interfacesClass = userMapperImplClass01.getInterfaces();
        for (Class<?> cls : interfacesClass) {
            System.out.print("接口名:" + cls.getName() + ";   ");
        }

    }

}
