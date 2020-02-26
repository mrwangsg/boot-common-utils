package hello.okComponent;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @创建人 sgwang
 * @name Headers
 * @user shiguang.wang
 * @创建时间 2019/7/3
 * @描述
 */
public class Headers {

    // HTTP 头的数据结构是 Map<String, List<String>>类型

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .header("User-Agent", "My super agent")
                .addHeader("Accept", "text/html")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.header("Cache-Control"));
        System.out.println(response.headers("Set-Cookie"));
    }
}
