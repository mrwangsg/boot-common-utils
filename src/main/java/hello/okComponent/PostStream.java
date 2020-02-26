//package com.sgwang.okComponent;
//
//import com.squareup.okhttp.*;
//import okio.BufferedSink;
//
//import java.io.*;
//
///**
// * @创建人 sgwang
// * @name PostStream
// * @user shiguang.wang
// * @创建时间 2019/7/3
// * @描述
// */
//public class PostStream {
//
//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
//        final String postBody = "Hello World";
//
//        RequestBody requestBody = new RequestBody() {
//            @Override
//            public MediaType contentType() {
//                return MEDIA_TYPE_TEXT;
//            }
//
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                FileInputStream inputStream = new FileInputStream(new File("src/main/resources/in.txt"));
//                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(sink.outputStream());
//
//                byte[] bytes = new byte[1024];
//                while (inputStream.read(bytes) != -1){
//                    System.out.println(bytes);
//                    bufferedOutputStream.write(bytes);
//                }
//
//                inputStream.close();
//                bufferedOutputStream.close();
//                sink.close();
//
////                sink.writeUtf8(postBody);
//            }
//
//            @Override
//            public long contentLength() throws IOException {
//                return postBody.length();
//            }
//        };
//
//        Request request = new Request.Builder()
//                .url("http://www.baidu.com")
//                .post(requestBody)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        if (!response.isSuccessful()) {
//            throw new IOException("服务器端错误: " + response);
//        }
//
//        System.out.println(response.body().string());
//    }
//
//}
