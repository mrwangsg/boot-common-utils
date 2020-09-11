package test.csv.convertor;

import com.tuyang.beanutils.BeanCopyConvertor;

/**
 * @创建人 sgwang
 * @name GendorConvertor
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述 转换器，转换性别。true为男，false为女
 */
public class GendorConvertor implements BeanCopyConvertor<Boolean, String> {

    private final static String SEX_MAN = "男";
    private final static String SEX_WOMAN = "女";

    @Override
    public String convertTo(Boolean bool) {
        if (bool) {
            return SEX_MAN;
        } else {
            return SEX_WOMAN;
        }
    }
}
