package com.trj.thttp.http;

import com.google.gson.Gson;
import com.trj.tlib.uils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofit {

    public static final String baseUrl = "https://api.jiahubang.com/";

    public static final String encodeStr = "application/json; charset=utf-8";

    /**
     * 超时时间60s
     */
    private static final long DEFAULT_TIMEOUT = 60;

    private volatile static HttpRetrofit mInstance;

    public HttpAPI hApi;

    private HttpRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(genericClient())
                .build();
        hApi = retrofit.create(HttpAPI.class);
    }

    public static HttpRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (HttpRetrofit.class) {
                if (mInstance == null){
                    mInstance = new HttpRetrofit();
                }

            }
        }
        return mInstance;
    }


    /**
     * 添加统一header,超时时间,http日志打印
     * body采用UTF-8编码
     *
     * @return
     */
    public static OkHttpClient genericClient() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
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
//                .addInterceptor(logging)
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

    public RequestBody getRequestBody(String jsonStr){
        return RequestBody.create(MediaType.parse(HttpRetrofit.encodeStr), jsonStr);
    }

}
