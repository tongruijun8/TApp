package com.trj.tlib.presenter;

import com.trj.tlib.activity.InitActivity;
import com.trj.tlib.assist.Constants;
import com.trj.tlib.javabean.LoginInfoJB;
import com.trj.tlib.modules.TLoginView;
import com.trj.tlib.uils.MD5Utils;
import com.trj.tlib.uils.ToastUtil;
import com.trj.tlib.xutils.TXutilsManager;

import java.util.HashMap;

/**
 * @author tong
 * @date 2018/4/25 14:35
 */
public class TLoginPresenterImpl extends TXutilsManager implements TLoginPresenter {


    private TLoginView loginView;

    public TLoginPresenterImpl(InitActivity activity) {
        super(activity);
        loginView = (TLoginView) activity;
    }

    @Override
    public void loginPsw(String phoneNum, String password) {
        if (phoneNum.isEmpty()) {
            ToastUtil.showToast(initActivity.context, "手机号不能为空");
        } else if (phoneNum.length() != 11) {
            ToastUtil.showToast(initActivity.context, "手机号有误");
        } else if (password.isEmpty()) {
            ToastUtil.showToast(initActivity.context, "密码不能为空");
        } else {
            onTStart(Constants.CString.DIALOG_MSG_LOAD);
            String pswStr = MD5Utils.getMD5(password);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("phoneNum", phoneNum);
            hashMap.put("password", pswStr);
            String path = "";
            post(hashMap, path, new TCommonCallback<LoginInfoJB>(1) {
                @Override
                public void onTSuccess(LoginInfoJB loginInfoJB) {

                }
            });
        }

    }

}
