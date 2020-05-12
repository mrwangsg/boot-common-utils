package hello.error;

import org.springframework.beans.BeansException;

/**
 * @创建人 sgwang
 * @name BeanReflectException
 * @user Anti
 * @创建时间 2020/4/26
 * @描述：自定义异常。baen反射实例对象时，异常返回
 */
public class BeanReflectException extends BeansException {
    /**
     * Create a new BeansException with the specified message.
     *
     * @param msg the detail message
     */
    public BeanReflectException(String msg) {
        super(msg);
    }

    /**
     * Create a new BeansException with the specified message
     * and root cause.
     *
     * @param msg   the detail message
     * @param cause the root cause
     */
    public BeanReflectException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
