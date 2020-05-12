package hello.pojo;

import lombok.Data;

import java.util.List;

/**
 * @创建人 sgwang
 * @name ArrBeanBO
 * @user Anti
 * @创建时间 2020/4/26
 * @描述
 */
@Data
public class ArrBeanBO {

    // 属性名称
    private String name;

    // 用户集合
    private List<UserBO> userList;

    @Override
    public String toString() {
        return "ArrBeanBO{" +
                "name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
