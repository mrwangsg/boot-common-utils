package test.csv.bean;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.tuyang.beanutils.annotation.BeanCopySource;
import com.tuyang.beanutils.annotation.CopyProperty;
import lombok.Data;
import test.csv.convertor.String2TextConvertor;
import test.model.Student;

import java.util.Date;

/**
 * @创建人 sgwang
 * @name StudentCsvBean
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述 将数据库出来的实体，转换为csv实体。PS：position必须从index=0开始
 */
@Data
@BeanCopySource(source = Student.class)
public class StudentCsvBean {

    @CsvBindByName(column = "姓名")
    @CsvBindByPosition(position = 0)
    private String studentName;

    @CsvBindByName(column = "性别")
    @CsvBindByPosition(position = 1)
    private String sex;

    @CsvBindByName(column = "学号")
    @CsvBindByPosition(position = 2)
    @CopyProperty(convertor = String2TextConvertor.class)
    private String studentNum;

    @CsvBindByName(column = "班级")
    @CsvBindByPosition(position = 3)
    private Long aliasClassId;

    @CsvBindByName(column = "出生日期")
    @CsvBindByPosition(position = 4)
    @CsvDate("yyyy-MM-dd")
    private Date birthDate;

    @CsvBindByName(column = "居住地址")
    @CsvBindByPosition(position = 5)
    private String address;

}
