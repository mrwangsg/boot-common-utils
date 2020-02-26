package hello.restTemplate.exception;

/**
 * @创建人 sgwang
 * @name SimpleHttpException
 * @user shiguang.wang
 * @创建时间 2019/7/16
 * @描述
 */
public class SimpleHttpException extends RuntimeException {

    private static String logo = "SimpleHttpException: ";

    public SimpleHttpException(String msg) {
        super(logo + msg);
    }

}
