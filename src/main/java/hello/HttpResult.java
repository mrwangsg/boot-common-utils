package hello;

/**
 * @Author        :  sgwang
 * @Version       :  1.0
 * @Title         :  HttpResult
 * @CreateDate    :  2020/3/8 0:32
 * @Description   :  泛型类
 */
public class HttpResult<T> {

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
