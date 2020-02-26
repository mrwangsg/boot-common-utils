package hello.restTemplate.config;

import hello.restTemplate.handler.SimpleResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 sgwang
 * @name RestTemplateConfig
 * @user 91119
 * @创建时间 2019/7/8
 * @描述
 */
@Configuration
public class RestTemplateConfig {

    @Value("${rest.template.http.request.factory.strategy}")
    private String requestFactory;

    @Autowired
    private SimpleClientBean simpleClientBean;

    @Autowired
    private HTTPClientBean httpClientBean;

    @Autowired
    private SimpleResponseHandler simpleResponseHandler;

    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestFactory httpRequestFactory = getHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        // 添加格式转换器。tip如果添加了，会删除RestTemplate之前默认的转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        // 设置异常处理
        restTemplate.setErrorHandler(simpleResponseHandler);

        return restTemplate;
    }

    // 选择一个策略生成 相应一个工厂
    private ClientHttpRequestFactory getHttpRequestFactory() {
        ClientHttpRequestFactory httpRequestFactory = null;

        // 穷举有 SimpleClient、HttpClient、OkHttp3Client、Netty4Client
        switch (requestFactory) {
            case "SimpleClient":
                httpRequestFactory = simpleClientBean.execute();
                break;
            case "HttpClient":
                httpRequestFactory = httpClientBean.execute();
                break;
            case "OkHttp3Client":
                break;
            case "Netty4Client":
                break;
            default:
                break;
        }

        return httpRequestFactory;
    }

}
