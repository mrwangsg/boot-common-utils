package test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.model.Student;
import test.service.StudentService;

import java.util.List;

/**
 * @创建人 sgwang
 * @name StudentController
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public String getAll() {

        List<Student> result = studentService.getAll();

        logger.info(result.toString());

        return "PrintLogController ! " + LogLevel.TRACE;
    }

    @GetMapping("/first")
    public String getStudent() {

        Student result = studentService.getFirst();

        logger.info(result.toString());

        return "PrintLogController ! " + LogLevel.TRACE;
    }

    @GetMapping("/cur")
    public String getStudentByCur() {

        List<Student> result = studentService.selectStuByCur();

        logger.info(result.toString());

        return "PrintLogController ! " + LogLevel.TRACE;
    }


}
