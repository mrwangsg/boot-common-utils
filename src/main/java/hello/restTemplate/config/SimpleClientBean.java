package hello.restTemplate.config;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;

/**
 * @创建人 sgwang
 * @name SimpleClientBean
 * @user shiguang.wang
 * @创建时间 2019/7/12
 * @描述
 */
@Component
public class SimpleClientBean implements ClientFactory{

    @Override
    public ClientHttpRequestFactory execute() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(3000);

        return requestFactory;
    }

}
