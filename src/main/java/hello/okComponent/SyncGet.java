package hello.okComponent;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @创建人 sgwang
 * @name SyncGet
 * @user shiguang.wang
 * @创建时间 2019/7/3
 * @描述
 */
public class SyncGet {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        // execute发起同步消息，这是一个耗时的过程，所以可以在这里多线程
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        // HTTP 头的数据结构是 Map<String, List<String>>
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        System.out.println(response.body().string());
    }
}
