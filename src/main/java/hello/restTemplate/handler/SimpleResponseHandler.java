package hello.restTemplate.handler;

import hello.restTemplate.exception.SimpleHttpException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @创建人 sgwang
 * @name SimpleResponseHandler
 * @user shiguang.wang
 * @创建时间 2019/7/16
 * @描述
 */
@Component
public class SimpleResponseHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() == 200) {
            return true;
        }
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (hasError(response)) {
            throw new SimpleHttpException("简单测试！    code: " + response.getRawStatusCode());
        }
        super.handleError(response);
    }
}
