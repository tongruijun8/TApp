package com.trj.tapp;

import android.os.Bundle;

import com.trj.tlib.activity.BaseTitleActivity;

public class MainActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("首页");
    }

}
