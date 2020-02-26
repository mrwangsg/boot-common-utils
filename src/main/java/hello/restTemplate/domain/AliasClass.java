package hello.restTemplate.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @创建人 sgwang
 * @name AliasClass
 * @user 91119
 * @创建时间 2019/6/14
 * @描述
 */
@Entity
@Table(name = "alias_class",
        indexes = {
                @Index(name = "create_time", columnList = "create_time"),
                @Index(name = "update_time", columnList = "update_time"),
                @Index(name = "class_name", columnList = "class_name"),
                @Index(name = "class_teacher", columnList = "class_teacher")
        })
@SQLDelete(sql = "UPDATE alias_class SET is_delete = 1 WHERE uuid = ?")
@Where(clause = "is_delete = 0")    // 过滤查询结果前 is_delete != 1
public class AliasClass extends SuperModel {

    @Column(name = "class_name", columnDefinition = "varchar(20) NOT NULL COMMENT '班级名称'")
    @NotNull
    private String className;

    @Column(name = "class_teacher", columnDefinition = "varchar(20) NOT NULL COMMENT '班主任'")
    @NotNull
    private String classTeacher;

    @Override
    public String toString() {
        return super.toString() + "   AliasClass{" +
                "className='" + className + '\'' +
                ", classTeacher='" + classTeacher + '\'' +
                '}';
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

}
