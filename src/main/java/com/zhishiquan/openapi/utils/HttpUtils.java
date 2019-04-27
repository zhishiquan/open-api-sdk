package com.zhishiquan.openapi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhishiquan.openapi.interceptor.LoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http工具类
 * @author jl
 */
@Slf4j
public final class HttpUtils {
    private static OkHttpClient client;

    static {
        HttpUtils.client = new OkHttpClient.Builder()
                //链接超时
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                //读取超时
                .readTimeout(10000,TimeUnit.MILLISECONDS)
                //写入超时
                .writeTimeout(10000,TimeUnit.MILLISECONDS)
                // 连接池
                .connectionPool(new ConnectionPool(10, 300, TimeUnit.SECONDS))
                //应用拦截器：统一添加消息头
//                .addInterceptor(new HttpHeadInterceptor())
                //网络拦截器
//                .addNetworkInterceptor(new NetworkspaceInterceptor())
                //应用拦截器：打印日志
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    /**
     * 发送json
     * @param url 请求url
     * @param body requestBody
     * @return JSONObject 失败返回null
     */
    public static JSONObject post(String url, Object body, Headers headers) {
        //数据类型为json格式，
        MediaType header = MediaType.parse("application/json; charset=utf-8");
        String json = JSON.toJSONString(body);
        RequestBody requestBody = RequestBody.create(header, json);
        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            if(log.isDebugEnabled()) {
                log.debug(MessageFormat.format("调用post json url:{0}, params:{1}", url, json));
            }
            return JSON.parseObject(resStr);
        } catch (IOException e) {
            log.error("调用post json发送请求失败", e);
        }
        return null;
    }

    /**
     * 发送表单
     * @param url url
     * @param params 参数
     * @return String  失败返回null
     */
    public static String form(String url, Map<String, String> params) {
        FormBody.Builder requestBodyBuilder = new FormBody.Builder(Charset.forName("utf-8"));
        for(Map.Entry<String, String> entry : params.entrySet()) {
            requestBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = requestBodyBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("调用post form发送请求失败", e);
        }
        return null;
    }
}
