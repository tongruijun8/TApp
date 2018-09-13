package com.trj.tlib.modules;

import com.trj.tlib.javabean.RespJB;
import com.trj.tlib.javabean.TBaseJB;

/**
 * @author tong
 * @date 2018/5/6 18:33
 */
public interface TBaseView<T extends RespJB<TBaseJB>> extends TInitView  {

    /**
     * 成功
     */
    void onTSuccess(T result);


}
