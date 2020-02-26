package hello.restTemplate.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@MappedSuperclass
public class SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition="int(11) NOT NULL COMMENT '主键'")
    private Integer uuid;

    @Column(name = "create_time", columnDefinition="datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    @Generated(GenerationTime.INSERT)
    private Timestamp createTime;

    @Column(name = "update_time", columnDefinition="datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    @Generated(GenerationTime.ALWAYS)
    private Timestamp updateTime;

    @Column(name = "is_delete", columnDefinition = "tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否逻辑删除. 0代表否，1代表是'")
    private Byte delete = 0;

    @Override
    public String toString() {
        return "SuperModel{" +
                "uuid=" + uuid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delete=" + delete +
                '}';
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDelete() {
        return delete;
    }

    public void setDelete(Byte delete) {
        this.delete = delete;
    }
}
