package com.trj.tapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.trj.tlib.activity.NavBottomActivity;
import com.trj.tlib.fragment.BlankFragment;
import com.trj.tlib.fragment.TInitFragment;
import com.trj.tlib.uils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class TestNavBottomActivity extends NavBottomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initFragmentData() {
        fragmentList.clear();
        fragmentList.add(BlankFragment.newInstance("首页", ""));
        fragmentList.add(BlankFragment.newInstance("订单", ""));
        fragmentList.add(BlankFragment.newInstance("消息", ""));
        fragmentList.add(BlankFragment.newInstance("我的", ""));
        super.initFragmentData();
    }

    @Override
    protected void initTabData() {
        mBottomNavView.inflateMenu(R.menu.navigation_bottom_test);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuItem = item;
        int itemId = item.getItemId();
        if (itemId == R.id.nav_bottom_test_item1) {
            fragmentList.get(0).initData();
            mViewpager.setCurrentItem(0, false);
            return true;
        } else if (itemId == R.id.nav_bottom_test_item2) {
            fragmentList.get(1).initData();
            mViewpager.setCurrentItem(1, false);
            return true;
        } else if (itemId == R.id.nav_bottom_test_item3) {
            fragmentList.get(2).initData();
            mViewpager.setCurrentItem(2, false);
            return true;
        } else if (itemId == R.id.nav_bottom_test_item4) {
            fragmentList.get(3).initData();
            mViewpager.setCurrentItem(3, false);
            return true;
        }
        return false;
    }
}
