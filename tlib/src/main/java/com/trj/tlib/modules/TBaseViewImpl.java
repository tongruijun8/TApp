package com.trj.tlib.modules;

import com.trj.tlib.javabean.RespJB;
import com.trj.tlib.javabean.TBaseJB;

/**
 * @author tong
 * @date 2018/4/27 17:50
 */
public abstract class TBaseViewImpl<T extends RespJB<TBaseJB>> implements TBaseView<T> {

    TInitView initView;

    public TBaseViewImpl(TInitView initView) {
        this.initView = initView;
    }

    @Override
    public void onTStart(String msg) {
        initView.onTStart(msg);
    }

    @Override
    public abstract void onTSuccess(T result);

    @Override
    public void onTFail(String msg, int code) {
        initView.onTFail(msg,code);
    }

    @Override
    public void onTError(String msg, int code) {
        initView.onTError(msg,code);
    }

    @Override
    public void onTFinish() {
        initView.onTFinish();
    }
}
