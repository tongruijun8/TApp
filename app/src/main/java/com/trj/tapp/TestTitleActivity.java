package com.trj.tapp;

import android.os.Bundle;

import com.trj.tlib.activity.BaseTitleActivity;

public class TestTitleActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_title);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试标题页");
    }
}
