package com.trj.tlib.xutils;

import com.google.gson.Gson;
import com.trj.tlib.activity.InitActivity;
import com.trj.tlib.javabean.RespJB;
import com.trj.tlib.javabean.TBaseJB;
import com.trj.tlib.manage.NetWorkManage;
import com.trj.tlib.modules.TInitView;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * @author tong
 * @date 2018/1/16 9:31
 */

public class TXutilsManager implements TInitView {

    public Gson gson;

    /**
     * 页面大小
     */
    public static final int pageSize = 10;
    /**
     * 范围大小
     */
    public static double radius = 5;

    /**
     * 请求超时时间
     */
    private final int out_time = 10 * 1000;

    protected InitActivity initActivity;

    public TXutilsManager(InitActivity activity) {
        initActivity = activity;
        gson = activity.gson;
    }

    /**
     * 通用的参数
     *
     * @param infoString
     * @param url
     * @return
     */
    protected RequestParams getRequestParams(String infoString, String url) {
        RequestParams params = new RequestParams();
        params.addHeader("Authorization", initActivity.activityAssist.token);
        params.addHeader("sn", initActivity.activityAssist.getDeviceId());
        params.setAsJsonContent(true);
        if (!infoString.equals("")) {
            params.setBodyContent(infoString);
        }
        params.setUri(url);
        params.setConnectTimeout(out_time);
        return params;
    }


    /**
     * post请求：一般
     *
     * @param hashMap 请求参数集合
     */
    public <T extends TBaseJB> void post(Object hashMap, String url, TCommonCallback<T> callback) {
        if (!NetWorkManage.getInstance().isNetworkConnected(initActivity.context)) {
            onTError("java.net.ConnectException", 100);
            onTFinish();
            return;
        }
        String gsonStr = "";
        if (null != hashMap && !hashMap.equals("")) {
            gsonStr = gson.toJson(hashMap);
        }
        RequestParams params = getRequestParams(gsonStr, url);
        x.http().post(params, callback);
    }

    public abstract class TCommonCallback<T extends TBaseJB> implements Callback.CommonCallback<RespJB<T>> {

        /**
         * 请求类别
         */
        int code = 0;

        public TCommonCallback(int code) {
            this.code = code;
        }

        public abstract void onTSuccess(T t);

        @Override
        public void onSuccess(RespJB<T> result) {
            if (isSuccess(result.getOperationStatus())) {
                onTSuccess(result.getData());
            } else {
                onTFail(result.getOperationMessage(), code);
            }
        }


        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            if (ex instanceof NullPointerException) {
                Logger.t("onError : ex = " + ex.toString());
                return;
            }
            onTError(ex.toString(), code);
        }

        @Override
        public void onCancelled(CancelledException cex) {
            Logger.t("onCancelled--" + cex.toString());
        }

        @Override
        public void onFinished() {
            Logger.t("onFinished");
            onTFinish();
        }
    }

    public boolean isSuccess(String status) {
        if (null != status && !status.equals("") && status.toLowerCase().equals("success")) {
            return true;
        }
        return false;
    }


    @Override
    public void onTStart(String msg) {
        initActivity.activityAssist.showDialog(msg);
    }

    @Override
    public void onTFail(String msg, int code) {
        if (msg != null && !msg.equals("服务器异常")) {
            ToastUtil.showToast(initActivity, msg);
        }/*else{
            ToastUtil.showToast(context, "数据请求异常");
        }*/
    }

    @Override
    public void onTError(String msg, int code) {
        if (null != msg && !msg.equals("")) {
            if (msg.contains("401") || msg.contains("403")) {
                return;
            }
            if (msg.contains("404") || msg.contains("500") || msg.contains("java.lang.NullPointerException")) {
                return;
            }
            if (msg.contains("java.net.SocketTimeoutException")) {
                ToastUtil.showToast(initActivity, "请求超时");
            } else if (msg.contains("java.net.ConnectException")) {
                ToastUtil.showToast(initActivity, "网络异常");
            } else if (msg.contains("java.net.UnknownHostException")) {
                ToastUtil.showToast(initActivity, "网络异常");
            } else {
                ToastUtil.showToast(initActivity, "请求异常");
            }
        }
    }

    @Override
    public void onTFinish() {
        initActivity.activityAssist.hideDialog();
    }

}
