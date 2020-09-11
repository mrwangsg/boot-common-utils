package test.dao;

import test.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    List<Student> selectAll();

    Student selectByPrimaryKey(Integer integer);

    List<Student> selectStuByCur(Map<String, Object> params);
}