package com.trj.tlib.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.RelativeLayout;

import com.trj.tlib.R;
import com.trj.tlib.t_modules.TitleListenter;
import com.trj.tlib.t_modules.TitleModule;

public class BaseTitleActivity extends BaseActivity implements TitleListenter {

    protected TitleModule titleModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        initTitleView();
        titleModule = new TitleModule(this);
    }

    @Override
    public void onClickBack(View view) {
        finish();
    }

    @Override
    public void onClickLeftText(View view) {

    }

    @Override
    public void onClickRightText(View view) {

    }

    @Override
    public void onClickMenu(View view) {

    }

    @Override
    public void onMenuItemClick(int position) {

    }
}
