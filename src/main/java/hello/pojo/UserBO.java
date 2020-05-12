package hello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @创建人 sgwang
 * @name UserBO
 * @user Anti
 * @创建时间 2020/4/26
 * @描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBO {

    // 账号
    private String username;

    // 密码
    private String password;

}

