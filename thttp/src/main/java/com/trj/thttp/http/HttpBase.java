package com.trj.thttp.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

public class HttpBase {

    public static final String baseUrl = "https://api.jiahubang.com/";

    public static final String encodeStr = "application/json; charset=utf-8";

    /**
     * 超时时间60s
     */
    protected static final long DEFAULT_TIMEOUT = 60;


    /**
     * 添加统一header,超时时间,http日志打印
     * body采用UTF-8编码
     *
     * @return
     */
    protected static OkHttpClient genericClient() {
        //新建log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder requestBuilder = request.newBuilder();
                        request = requestBuilder
                                .addHeader("Content-Type", encodeStr)
                                .post(RequestBody.create(MediaType.parse(encodeStr),
                                        bodyToString(request.body())))//关键部分，设置requestBody的编码格式为UTF-8
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * 将json串转化为RequestBody对象
     * @param jsonStr
     * @return
     */
    public RequestBody getRequestBody(String jsonStr){
        return RequestBody.create(MediaType.parse(HttpRetrofit.encodeStr), jsonStr);
    }


}
