package hello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @创建人 sgwang
 * @name ObjBeanBO
 * @user Anti
 * @创建时间 2020/4/26
 * @描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjBeanBO {

    // 属性名称
    private String name;

    // 用户
    private UserBO user;

}