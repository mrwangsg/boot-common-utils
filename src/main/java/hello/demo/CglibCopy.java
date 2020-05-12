package hello.demo;

import hello.pojo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Arrays;

/**
 * @创建人 sgwang
 * @name CglibCopy
 * @user Anti
 * @创建时间 2020/4/26
 * @描述
 */
public class CglibCopy {

    public static void main(String[] args) {
        User demoUser = new User();
        demoUser.setUsername("wangsg");
        demoUser.setPassword("123456");

        ObjBean objBean = new ObjBean();
        objBean.setName("obj-bean-demo");
        objBean.setUser(demoUser);

        ObjBeanBO objBeanBO = new ObjBeanBO();
        BeanCopier beanCopier01 = BeanCopier.create(ObjBean.class, ObjBeanBO.class, false);
        beanCopier01.copy(objBean, objBeanBO, null);
        System.err.println(objBean);
        System.err.println(objBeanBO);

        ArrBean arrBean = new ArrBean();
        arrBean.setName("arr-bean-demo");
        arrBean.setUserList(Arrays.asList(demoUser));

        ArrBeanBO arrBeanBO = new ArrBeanBO();
        BeanCopier beanCopier02 = BeanCopier.create(ArrBean.class, ArrBeanBO.class, false);
        beanCopier02.copy(arrBean, arrBeanBO, null);

        System.err.println(arrBean);
        System.err.println(arrBeanBO);
    }
}
