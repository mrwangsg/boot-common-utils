package hello.demo;

import hello.pojo.*;
import hello.util.BeanArrayUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @创建人 sgwang
 * @name SpringCopy
 * @user Anti
 * @创建时间 2020/4/26
 * @描述: 测试结果时，Spring 工具：BeanUtils.copyProperties()
 * 只能拷贝：同类Class且同属性名, 并且只提供对象引用地址拷贝。
 * 效果例如：
 * User testUser = new User();     Object obj = (Object) testUser;    UserBO testUserBO = (UserBO) obj;
 * List<User> testUsers = new ArrayList();     List<Object> objs = (List) testUsers;    List<UserBO> testUserBOs = (UserBO) objs;
 */
public class SpringCopy {

    public static void main(String[] args) {
        NestBean nestBean01 = new NestBean();
        nestBean01.setName("第一层");

        NestBean nestBean02 = new NestBean();
        nestBean02.setName("第二层");

        NestBean nestBean03 = new NestBean();
        nestBean03.setName("第三层");

        nestBean01.setExtendInfo(nestBean02);
        nestBean02.setExtendInfo(nestBean03);

        NestBean nestBean = new NestBean();
        BeanUtils.copyProperties(nestBean01, nestBean);
        System.err.println(nestBean01);
        System.err.println(nestBean);


        User demoUser = new User();
        demoUser.setUsername("wangsg");
        demoUser.setPassword("123456");

        ObjBean objBean = new ObjBean();
        objBean.setName("obj-bean-demo");
        objBean.setUser(demoUser);

        ObjBeanBO objBeanBO = new ObjBeanBO();
        BeanUtils.copyProperties(objBean, objBeanBO);
        System.err.println(objBean);
        System.err.println(objBeanBO);

        ArrBean arrBean = new ArrBean();
        arrBean.setName("arr-bean-demo");
        arrBean.setUserList(Arrays.asList(demoUser));

        ArrBeanBO arrBeanBO = new ArrBeanBO();
        BeanUtils.copyProperties(arrBean, arrBeanBO);
        List tempList = BeanArrayUtils.copyPropReturn(arrBean.getUserList(), UserBO.class);
        arrBeanBO.setUserList(tempList);

        System.err.println(arrBean);
        System.err.println(arrBeanBO);
        System.err.println(tempList);

        List userList = new ArrayList<>();
        userList.add(demoUser);
        test(userList);
        System.err.println(userList);
    }

    private static void test(List<User> userList) {
        userList.stream().forEach((item) -> {
            item.setPassword(item.getPassword() + "test");
        });

        User demoUser = new User();
        demoUser.setUsername("inner");
        demoUser.setPassword("123456");

        userList.clear();
        userList.add(demoUser);
        userList = new ArrayList<>();
        userList.add(demoUser);


    }

}
