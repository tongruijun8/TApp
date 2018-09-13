package com.trj.thttp.http;

import android.content.Context;
import android.content.Intent;

import com.trj.thttp.MainActivity;
import com.trj.thttp.bean.resp.RespBean;
import com.trj.tlib.uils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class TCallback<T> implements Callback<RespBean<T>> {

    private Context context;

    public TCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<RespBean<T>> call, Response<RespBean<T>> response) {
        RespBean<T> respBean = response.body();
        if (respBean.getCode() == 200) {
            onTSuccess(respBean);
        }else if(respBean.getCode() == 201){
            // token 失效
            context.startActivity(new Intent(context, MainActivity.class));
        }else{
            onTFail(respBean.getCode(), respBean.getMsg());
        }
        onTFinish();
    }

    @Override
    public void onFailure(Call<RespBean<T>> call, Throwable t) {
        onTError(t.getMessage());
        onTFinish();
    }

    public abstract void onTSuccess(RespBean<T> respBean);

    public void onTFail(int code ,String msg){

    }

    public void onTError(String msg){
        Logger.t(msg);
    }
    public void onTFinish(){

    }

}
