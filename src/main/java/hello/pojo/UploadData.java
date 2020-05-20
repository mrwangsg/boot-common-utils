package hello.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @创建人 sgwang
 * @name UploadData
 * @user Anti
 * @创建时间 2020/5/20
 * @描述
 */
@Data
public class UploadData {
    private String string;
    private Date date;
    private Double doubleData;
}
