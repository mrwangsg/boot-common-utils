package test.csv.convertor;

import com.tuyang.beanutils.BeanCopyConvertor;

/**
 * @创建人 sgwang
 * @name String2TextConvertor
 * @user shiguang.wang
 * @创建时间 2020/8/30
 * @描述 格式化内容为文本形式，即"'"开头
 */
public class String2TextConvertor implements BeanCopyConvertor<String, String> {

    @Override
    public String convertTo(String str) {
        if (str != null) {
            return "'" + str;
        }
        return "";
    }

}
