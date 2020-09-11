package test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @创建人 sgwang
 * @name TestStartApp
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述
 */
@SpringBootApplication
@MapperScan(value = "test.dao")
public class TestStartApp {

    public static void main(String[] args) {
        SpringApplication.run(TestStartApp.class, args);
    }

}
