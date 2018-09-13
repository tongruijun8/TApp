package com.trj.tlib.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author tong
 * @date 2018/1/19 18:13
 */

public class TLinearLayout extends LinearLayout {
    public TLinearLayout(Context context) {
        super(context);
    }

    public TLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private String TAG = "tongTLinearLayout";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: -------");
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            Log.i(TAG, "dispatchTouchEvent: ===============%%%%%%%%%%%%%%%");
            if(null != listenter){
                listenter.onTLLClick(this);
            }
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.i(TAG, "onInterceptTouchEvent: -------");
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.i(TAG, "onTouchEvent: -------");
        return false;
    }

    private TLLOnClickListenter listenter;

    public void setTLLOnClickListenter(TLLOnClickListenter listenter){
        this.listenter = listenter;
    }

    public interface TLLOnClickListenter{
        void onTLLClick(View view);
    }

}

