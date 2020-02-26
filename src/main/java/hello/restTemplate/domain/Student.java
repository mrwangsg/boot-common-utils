package hello.restTemplate.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

/**
 * @创建人 sgwang
 * @name Student
 * @user 91119
 * @创建时间 2019/6/14
 * @描述
 */
@Entity
@Table(name = "student",
        indexes = {
                @Index(name = "create_time", columnList = "create_time"),
                @Index(name = "update_time", columnList = "update_time"),
                @Index(name = "student_num", columnList = "student_num"),
                @Index(name = "student_name", columnList = "student_name"),
                @Index(name = "birth_date", columnList = "birth_date"),
        })
@SQLDelete(sql = "UPDATE student SET is_delete = 1 WHERE uuid = ?")
@Where(clause = "is_delete = 0")    // 过滤查询结果前 is_delete != 1
public class Student extends SuperModel {

    @Column(name = "student_num", columnDefinition = "varchar(20) NOT NULL COMMENT '学生学号'")
    private String studentNum;

    @Column(name = "student_name", columnDefinition = "varchar(20) NOT NULL COMMENT '学生姓名'")
    private String studentName;

    @Column(name = "sex", columnDefinition = "tinyint(1) NOT NULL COMMENT '0代表男，1代表女'")
    private Byte sex;

    @Column(name = "birth_date", columnDefinition = "date NOT NULL DEFAULT '1900-00-00'  COMMENT '出生日期'")
    private Date birthDate;

    @Column(name = "address", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '地址'")
    private String address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "alias_class_id")
    private AliasClass aliasClass;

    @Override
    public String toString() {
        return super.toString() + "  Student{" +
                "studentName='" + studentName + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", aliasClass=" + aliasClass +
                '}';
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AliasClass getAliasClass() {
        return aliasClass;
    }

    public void setAliasClass(AliasClass aliasClass) {
        this.aliasClass = aliasClass;
    }
}
