package demo;

import java.util.List;

/**
 * @创建人 sgwang
 * @name UserMapper
 * @user Anti
 * @创建时间 2020/3/8
 * @描述
 */
public interface UserMapper {

    User getUserById(String userId);

    User getUserByNameAndPass(String name, String pass);

    List<User> listUser();

    User getUser();
}
