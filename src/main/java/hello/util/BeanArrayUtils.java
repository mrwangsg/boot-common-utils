package hello.util;

import hello.error.BeanReflectException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 sgwang
 * @name BeanArrayUtils
 * @user Anti
 * @创建时间 2020/4/26
 * @描述
 */
public abstract class BeanArrayUtils {

    public static <S, T> List<T> copyPropReturn(List<S> source, Class<T> editable) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(editable, "Editable must not be null");

        List<T> outList = new ArrayList<T>();
        for (S item : source) {
            try {
                T temp = editable.newInstance();
                BeanUtils.copyProperties(item, temp, editable);
                outList.add(temp);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new BeanReflectException(e.getMessage());
            }

        }

        return outList;
    }

}
