package test.service;

import test.model.Student;

import java.util.List;

/**
 * @创建人 sgwang
 * @name StudentService
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述
 */
public interface StudentService {

    List<Student> getAll();

    Student getFirst();

    List<Student> selectStuByCur();

}
