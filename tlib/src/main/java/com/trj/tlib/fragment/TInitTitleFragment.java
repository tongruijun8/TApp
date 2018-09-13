package com.trj.tlib.fragment;

import android.view.View;

import com.trj.tlib.modules.TInitView;
import com.trj.tlib.t_modules.TitleFragmentModule;
import com.trj.tlib.t_modules.TitleListenter;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;


/**
 * Created by Administrator on 2017/10/20.
 */

public class TInitTitleFragment extends TInitFragment implements TitleListenter,TInitView {

    protected TitleFragmentModule titleModule;

    @Override
    protected void initFragmentView(View view) {
        super.initFragmentView(view);
        titleModule = new TitleFragmentModule(view,this);
    }

    @Override
    public void onClickBack(View view) {

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
