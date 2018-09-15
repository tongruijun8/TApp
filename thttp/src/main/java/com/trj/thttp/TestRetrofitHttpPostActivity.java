package com.trj.thttp;

import android.os.Bundle;
import android.view.View;

import com.trj.thttp.bean.req.PhoneVerifyCodeVo;
import com.trj.thttp.bean.req.ReqBean;
import com.trj.thttp.bean.resp.LoginInfo;
import com.trj.thttp.bean.resp.RespBean;
import com.trj.thttp.http.HttpRetrofit;
import com.trj.thttp.http.HttpRetrofitAndRxjava;
import com.trj.thttp.http.TCallback;
import com.trj.tlib.activity.BaseTitleActivity;
import com.trj.tlib.uils.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRetrofitHttpPostActivity extends BaseTitleActivity {

    private HttpRetrofit httpRetrofit;
    private HttpRetrofitAndRxjava httpRetrofitAndRxjava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpRetrofit = HttpRetrofit.getInstance();
        httpRetrofitAndRxjava = HttpRetrofitAndRxjava.getInstance();
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("首页");
    }

    private void test1() {

        Call<RespBean> call = httpRetrofit.hApi.getBeans("1", "10");
        //------------------发送网络请求(异步)--------------------
        call.enqueue(new Callback<RespBean>() {
            @Override
            public void onResponse(Call<RespBean> call, Response<RespBean> response) {
                //请求处理,输出结果-response.body().show();
                RespBean respBean = response.body();
            }

            @Override
            public void onFailure(Call<RespBean> call, Throwable t) {
                //请求失败时候的回调
            }
        });
        //------------------发送网络请求(同步):使用几率小--------------------
//        try {
//            //同步网络请求
//            Response<RespBean> response = call.execute();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void btn(View view) {

        ReqBean<PhoneVerifyCodeVo> reqBean = new ReqBean<>();
        PhoneVerifyCodeVo codeVo = new PhoneVerifyCodeVo();
        codeVo.setPhone("15091288100");
        codeVo.setVerifyCode("000000");
        reqBean.setData(codeVo);
        String jsonStr = gson.toJson(reqBean);
        RequestBody body = httpRetrofit.getRequestBody(jsonStr);
//        -------------方法一
//        Call<ResponseBody> call = httpRetrofit.hApi.loginYzm(body);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String result = response.body().string();
//                    Logger.t("------result = " + result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Logger.t("--onFailure------"+t.toString());
//            }
//        });

//        -------------方法二
//        Call<RespBean<LoginInfo>> call = httpRetrofit.hApi.loginYzm2(body);
//        call.enqueue(new Callback<RespBean<LoginInfo>>() {
//            @Override
//            public void onResponse(Call<RespBean<LoginInfo>> call, Response<RespBean<LoginInfo>> response) {
//                RespBean<LoginInfo> respBean = response.body();
//                Logger.t("------result = " + respBean.getData().toString());
//            }
//
//            @Override
//            public void onFailure(Call<RespBean<LoginInfo>> call, Throwable t) {
//                Logger.t("--onFailure------" + t.toString());
//            }
//        });
        //        -------------方法三
        Call<RespBean<LoginInfo>> call = httpRetrofit.hApi.loginYzm2(body);
        call.enqueue(new TCallback<LoginInfo>(context) {
            @Override
            public void onTSuccess(RespBean<LoginInfo> respBean) {
                Logger.t("------result = " + respBean.getData().toString());
            }
        });

    }

    public void btn2(View view) {
        ReqBean<PhoneVerifyCodeVo> reqBean = new ReqBean<>();
        PhoneVerifyCodeVo codeVo = new PhoneVerifyCodeVo();
        codeVo.setPhone("15091288100");
        codeVo.setVerifyCode("000000");
        reqBean.setData(codeVo);
        String jsonStr = gson.toJson(reqBean);
        RequestBody body = httpRetrofitAndRxjava.getRequestBody(jsonStr);
        Observable<RespBean<LoginInfo>> observable = httpRetrofitAndRxjava.hApi.loginYzm2(body);
        observable.subscribeOn(Schedulers.io())// 在子线程中进行Http访问
                .observeOn(AndroidSchedulers.mainThread()) // UI线程处理返回接口
                .subscribe(new Observer<RespBean<LoginInfo>>() {//订阅
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(RespBean<LoginInfo> loginInfoRespBean) {
                        Logger.t("----result = " + gson.toJson(loginInfoRespBean));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
