package com.trj.tlib;

import android.app.Application;

import org.xutils.x;

public class InitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
