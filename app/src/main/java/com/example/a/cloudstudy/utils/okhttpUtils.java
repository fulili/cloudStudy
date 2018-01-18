package com.example.a.cloudstudy.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * Created by jfl on 2018/1/12.
 */

public class okhttpUtils {
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    public static void sendHttpRequest(String address,Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendHttpRequestPost(String path, String json, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON ,json);
//        RequestBody requestBody1=new FormEncodingBuilder().build();
        Request request = new Request.Builder()
                .url(path)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
