package com.trj.thttp.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofitAndRxjava extends HttpBase{

    private volatile static HttpRetrofitAndRxjava mInstance;

    public HttpAPIRxjava hApi;

    protected HttpRetrofitAndRxjava() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加RxJava支持
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(genericClient())
                .build();
        hApi = retrofit.create(HttpAPIRxjava.class);
    }

    public static HttpRetrofitAndRxjava getInstance() {
        if (mInstance == null) {
            synchronized (HttpRetrofitAndRxjava.class) {
                if (mInstance == null){
                    mInstance = new HttpRetrofitAndRxjava();
                }
            }
        }
        return mInstance;
    }
}
