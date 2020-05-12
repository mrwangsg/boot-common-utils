package hello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @创建人 sgwang
 * @name NestBean
 * @user Anti
 * @创建时间 2020/5/12
 * @描述 嵌套bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestBean {
    // 属性名称
    private String name;

    private NestBean extendInfo;
}
