package com.trj.tlib.modules;

import com.trj.tlib.javabean.RespJB;
import com.trj.tlib.javabean.TBaseJB;

/**
 * @author tong
 * @date 2018/4/28 10:05
 */
public interface TInitView{

    /**
     * 开始请求：做一些初始化的工作
     */
    void onTStart(String msg);

    /**
     * 请求失败
     * @param msg 失败信息
     * @param code 失败类型（用于区别）
     */
    void onTFail(String msg, int code);

    /**
     * 异常（错误）
     * @param msg
     */
    void onTError(String msg, int code);

    /**
     * 请求结束：做一些请求完成的事件处理
     */
    void onTFinish();

}
