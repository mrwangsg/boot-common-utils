package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 sgwang
 * @name UserMapperImpl
 * @user Anti
 * @创建时间 2020/3/8
 * @描述
 */
public class UserMapperImpl implements UserMapper {

    private User user;

    @Override
    public User getUserById(String userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    @Override
    public User getUserByNameAndPass(String name, String pass) {
        User user = new User();
        user.setName(name);
        user.setPass(pass);
        return user;
    }

    @Override
    public List<User> listUser() {
        List<User> users = new ArrayList<>();

        User user01 = new User();
        user01.setName("list01");

        User user02 = new User();
        user02.setName("list02");

        users.add(user01);
        users.add(user02);

        return users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
