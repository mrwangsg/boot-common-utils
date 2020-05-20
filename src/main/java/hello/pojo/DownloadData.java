package hello.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @创建人 sgwang
 * @name DownloadData
 * @user Anti
 * @创建时间 2020/5/20
 * @描述
 */
@Data
public class DownloadData {
    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleData;

}
