package com.trj.thttp.http;

import com.trj.thttp.bean.resp.LoginInfo;
import com.trj.thttp.bean.resp.RespBean;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpAPI {

    //    注意： 接口中的每个方法的参数都需要使用注解标注，否则会报错

    @GET("word/word")
    Call<ResponseBody> getData(@Query("num") String num, @Query("page") String page);

    @Headers("apikey:123456")
    @GET("word/word")
    Call<RespBean> getBeans(@Query("num") String num, @Query("page") String page);


    //    post 请求相关
//    Call<RespBPerson> login(@Field("data")String username,@Field("data")String password);

    int HTTP_LOGIN_YZM = 1;
    int HTTP_LOGIN_YZM2 = 2;

    //登录接口
    @POST("JHBWorker/ordinary/loginByVerify")
    Call<RespBean<LoginInfo>> loginYzm2(@Body RequestBody body);

    @POST("JHBWorker/ordinary/loginByVerify")
    Call<ResponseBody> loginYzm(@Body RequestBody body);

}
