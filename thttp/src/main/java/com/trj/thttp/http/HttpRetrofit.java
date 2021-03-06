package com.trj.thttp.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofit extends HttpBase {

    private volatile static HttpRetrofit mInstance;

    public HttpAPI hApi;

    protected HttpRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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

}
