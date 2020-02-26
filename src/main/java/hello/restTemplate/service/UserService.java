package hello.restTemplate.service;


import hello.restTemplate.dao.UserRepository;
import hello.restTemplate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 sgwang
 * @name UserService
 * @user shiguang.wang
 * @创建时间 2019/7/8
 * @描述
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
