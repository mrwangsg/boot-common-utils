package hello.restTemplate.domain;

import javax.persistence.*;

/**
 * @创建人 sgwang
 * @name User
 * @user shiguang.wang
 * @创建时间 2019/7/8
 * @描述
 */
@Entity
@Table
public class User {
    public User() {
    }

    public User(String name, int age, String sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    private String sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}