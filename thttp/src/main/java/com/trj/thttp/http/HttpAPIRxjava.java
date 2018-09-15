package com.trj.thttp.http;

import com.trj.thttp.bean.resp.LoginInfo;
import com.trj.thttp.bean.resp.RespBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpAPIRxjava {

    //登录接口
    @FormUrlEncoded
    @POST("JHBWorker/ordinary/loginByVerify")
    Observable<RespBean<LoginInfo>> loginYzm2(@Body RequestBody body);

}
