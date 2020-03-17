package hello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @创建人 sgwang
 * @name User
 * @user Anti
 * @创建时间 2020/3/17
 * @描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String userId;
    String username;
    String password;
}
