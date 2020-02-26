package hello.restTemplate.config;

import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * @创建人 sgwang
 * @name ClientFactory
 * @user shiguang.wang
 * @创建时间 2019/7/12
 * @描述
 */
public interface ClientFactory {
    ClientHttpRequestFactory execute();
}
