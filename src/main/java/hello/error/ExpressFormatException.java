package hello.error;

/**
 * @创建人 sgwang
 * @name ExpressFormatException
 * @user Anti
 * @创建时间 2020/4/18
 * @描述
 */
public class ExpressFormatException extends Exception {

    private final static String message = "表达式的格式不正确！";

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public ExpressFormatException() {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ExpressFormatException(String message) {
        super(message);
    }
}
