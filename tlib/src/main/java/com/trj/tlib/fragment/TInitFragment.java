package com.trj.tlib.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trj.tlib.activity.InitActivity;
import com.trj.tlib.modules.TInitView;
import com.trj.tlib.t_modules.TXListViewListenter;
import com.trj.tlib.uils.ToastUtil;


/**
 * Created by Administrator on 2017/10/20.
 */

public class TInitFragment extends Fragment implements TXListViewListenter,TInitView {

    protected InitActivity activity;

    public TInitFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (InitActivity) getActivity();
    }

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutRes, container, false);
        initFragmentView(rootView);
        return rootView;
    }

    private int layoutRes = -1;

    protected void initlayout(@LayoutRes int resource){
        layoutRes = resource;
    }

    protected void initFragmentView(View view) {

    }

    /**
     * 初始化数据
     */
    public void initData(){

    }


    @Override
    public void exceptionPageClickEvent() {

    }

    /**
     * 获取XListView列表数据
     * @param page 页码
     */
    @Override
    public void getData(int page) {

    }

    @Override
    public void onTStart(String msg) {
        activity.activityAssist.showDialog(msg);
    }

    @Override
    public void onTFail(String msg, int code) {
        if (msg != null && !msg.equals("服务器异常")) {
            ToastUtil.showToast(activity, msg);
        }
    }

    @Override
    public void onTError(String msg, int code) {
        if (msg.contains("401") || msg.contains("403")) {
            return;
        }
        if(msg.contains("404")||msg.contains("500")||msg.contains("java.lang.NullPointerException")){
            return;
        }
        if (null != msg && !msg.equals("")) {
            if (msg.contains("java.net.SocketTimeoutException")){
                ToastUtil.showToast(activity,"请求超时");
            }else if(msg.contains("java.net.ConnectException")){
                ToastUtil.showToast(activity,"网络异常");
            }else{
                ToastUtil.showToast(activity,"请求异常");
            }
        }
    }

    @Override
    public void onTFinish() {
        activity.activityAssist.hideDialog();
    }

}
