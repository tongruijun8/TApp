package com.trj.tlib.modules;

import com.trj.tlib.javabean.LoginInfoJB;

/**
 * @author tong
 * @date 2018/3/18 19:00
 */

public interface TLoginView{

    /**
     * 验证码登录成功
     */
    void yzmTSuccess(LoginInfoJB jb);

    /**
     * 密码登录成功
     */
    void pswTSuccess(LoginInfoJB jb);
    /**
     * 微信登录成功
     */
    void wxTSuccess(LoginInfoJB jb);

    /**
     * 微博登录成功
     */
    void wbTSuccess(LoginInfoJB jb);
    /**
     * QQ登录成功
     */
    void qqTSuccess(LoginInfoJB jb);

}
