package com.zhishiquan.openapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

@Slf4j
public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "Okhttp";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        log.info(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        log.info(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        log.info(TAG, content);

        response = response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
        return response;
    }
}  