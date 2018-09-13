package com.trj.tlib.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewStub;

import com.google.gson.Gson;
import com.trj.tlib.InitApplication;
import com.trj.tlib.R;
import com.trj.tlib.assist.TDialogUtils;
import com.trj.tlib.manage.ActivityManager;
import com.trj.tlib.manage.NetWorkManage;


/**
 * @author tong
 * @date 2018/3/16 15:52
 */

public class InitActivity extends AppCompatActivity{

    public Context context;
    public InitApplication application;
    public ActivityManager activityManager;
    public NetWorkManage netWorkManage;
    public SharedPreferences sharedPreferences;
    public Gson gson;
    public TDialogUtils tDialogUtils;

    public ActivityAssist activityAssist;

    private ViewStub rootTitleViewStub;
    private ViewStub rootContentViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_root);
        rootTitleViewStub = findViewById(R.id.root_title_viewstub);
//        rootTitle = findViewById(R.id.root_title);
        rootContentViewStub = findViewById(R.id.root_content_viewstub);
//        rootContent = findViewById(R.id.root_content);
//        初始化工作
        initWork();
//        添加内容View
        initContentView(layoutResID);
        //初始化View
        initView();
    }

    /**
     * 初始化标题布局
     */
    protected void initTitleView(){
        rootTitleViewStub.setLayoutResource(R.layout.ttitle);
        rootTitleViewStub.inflate();
    }

    /**
     * 初始化内容布局
     * @param layoutResID
     */
    private void initContentView(int layoutResID){
        rootContentViewStub.setLayoutResource(layoutResID);
        rootContentViewStub.inflate();
    }

    /**
     * Activity的初始化方法
     */
    private void initWork() {
        this.context = this;
        application = (InitApplication) getApplication();
        sharedPreferences = getSharedPreferences("trj", MODE_PRIVATE);
        netWorkManage = NetWorkManage.getInstance();
        activityManager = ActivityManager.getInstance();
        activityManager.addActivity(this);
        gson = new Gson();
        tDialogUtils = new TDialogUtils(this);
        activityAssist = new ActivityAssist(this);
    }

    protected void initView() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(backBefore()){
                finish();
            }
        }
        return true;
    }

    /**
     * 返回之前(默认返回上一层)
     */
    protected boolean backBefore(){
        return true;
    }

    @Override
    protected void onStop() {
        if(null != activityAssist.dialog){
            activityAssist.dialog.cancel();
        }
        super.onStop();
    }

}
