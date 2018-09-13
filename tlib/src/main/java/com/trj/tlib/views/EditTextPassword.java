package com.trj.tlib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.trj.tlib.R;

/**
 * Created by cmj on 2016/4/25.
 */
public class EditTextPassword extends AppCompatEditText {

    private boolean mIsShow = false;
//    public interface DrawableRightListener {
//        void onDrawableRightClick();
//    }

//    private DrawableRightListener mRightListener;

//    public void setDrawableRightListener(DrawableRightListener listener) {
//        this.mRightListener = listener;
//    }

    private Drawable drawableRight;

    final int DRAWABLE_RIGHT = 2;

    public EditTextPassword(Context context) {
        super(context);
        init();
    }

    public EditTextPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawableRight != null  && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            //判断点击是否落在rightDrawable中
            if (x > getWidth() - getTotalPaddingRight() && x < getWidth() && y > 0 && y < getHeight()) {
                //获取点击之前光标的位置
                int pos = getSelectionStart();
                //设置回调
//                mRightListener.onDrawableRightClick();
                if (mIsShow) {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.dl_yingchang, 0) ;
                    setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.dl_xianshi, 0) ;
                    setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                mIsShow = !mIsShow ;

                //恢复点击之前光标的位置
                this.setSelection(pos);
            }
        }
        return super.onTouchEvent(event);
    }
}
