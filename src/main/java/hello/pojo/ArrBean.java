package hello.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @创建人 sgwang
 * @name ArrBean
 * @user Anti
 * @创建时间 2020/4/26
 * @描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrBean {

    // 属性名称
    private String name;

    // 用户集合
    private List<User> userList;

    @Override
    public String toString() {
        return "ArrBean{" +
                "name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
