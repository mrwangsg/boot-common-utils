package hello.restTemplate.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @创建人 sgwang
 * @name HTTPClientBean
 * @user shiguang.wang
 * @创建时间 2019/7/12
 * @描述
 */
@Component
public class HTTPClientBean implements ClientFactory {
    private PoolingHttpClientConnectionManager connectionManager;

    @Override
    public ClientHttpRequestFactory execute() {
        HttpClient httpClient = httpClient();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    private HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(3);
        connectionManager.setDefaultMaxPerRoute(3);
        // 注解意思是 检测不活跃时长(参数)的连接 将设置为半关闭状态。有助于连接回收
        connectionManager.setValidateAfterInactivity(2000);

        // 启动监控线程 定时清理关闭和空闲连接
        new IdleConnectionMonitorThread(connectionManager).start();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000) //服务器返回数据(response)的时间，超过抛出read timeout
                .setConnectTimeout(3000) //连接上服务器(握手成功)的时间，超出抛出connect timeout
                .setConnectionRequestTimeout(10000)//从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .build();

        // 第二个参数设置true，则表示无论如何 同一个请求均发送三次。false表示，只有在失败的时候，尝试再次发送
        HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(3, false);
        return HttpClientBuilder.create()
                .setRetryHandler(retryHandler)
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }


    // 连接关闭策略
    class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections
                        // that have been idle longer than 30 sec
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }

    }

}
