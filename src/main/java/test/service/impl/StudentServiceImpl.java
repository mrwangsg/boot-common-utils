package test.service.impl;

import com.tuyang.beanutils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.csv.WriteCsvUtil;
import test.csv.bean.StudentCsvBean;
import test.dao.StudentMapper;
import test.model.Student;
import test.service.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 sgwang
 * @name StudentServiceImpl
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> getAll() {

        List<Student> dataList = studentMapper.selectAll();
        List<StudentCsvBean> csvDataList = new ArrayList<>();

        for (Student fromBean : dataList) {
            StudentCsvBean studentCsv = BeanCopyUtils.copyBean(fromBean, StudentCsvBean.class);
            csvDataList.add(studentCsv);
        }

        try {
            WriteCsvUtil.write2csv(csvDataList, StudentCsvBean.class, "C:\\project\\spdb-provide-data\\src\\main\\resources\\test.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentMapper.selectAll();
    }

    @Override
    public Student getFirst() {
        return studentMapper.selectByPrimaryKey(1);
    }

    @Override
    public List<Student> selectStuByCur() {

        Map paramMap = new HashMap<>();
        paramMap.put("i_operator", null);
        paramMap.put("results", null);
        paramMap.put("total", null);
        studentMapper.selectStuByCur(paramMap);
        int total = (int) paramMap.get("total");
        System.err.println("总数据量：" + total);
        List<Student> dataList = (List<Student>) paramMap.get("results");
        List<StudentCsvBean> csvDataList = new ArrayList<>();

        for (Student fromBean : dataList) {
            StudentCsvBean studentCsv = BeanCopyUtils.copyBean(fromBean, StudentCsvBean.class);
            csvDataList.add(studentCsv);
        }

        try {
            WriteCsvUtil.write2csv(csvDataList, StudentCsvBean.class, "C:\\project\\spdb-provide-data\\src\\main\\resources\\test03.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentMapper.selectStuByCur(paramMap);
    }

}
