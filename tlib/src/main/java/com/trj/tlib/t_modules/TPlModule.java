package com.trj.tlib.t_modules;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tlib.R;
import com.trj.tlib.uils.TUtils;

/**
 * @author tong
 * @date 2018/3/28 18:02
 * 评论弹框
 */
public class TPlModule implements View.OnClickListener {

    private Activity activity;

    private TPlClickListenter listenter;

    public TPlModule(final Activity activity) {
        this.activity = activity;
        contentView = LayoutInflater.from(activity).inflate(R.layout.item_pinglun, null);
        viewHolder = new ViewHolder(contentView);
        viewHolder.mCommentsSubmit.setOnClickListener(this);
        viewHolder.relativeLayout.setOnClickListener(this);
        viewHolder.mCommentsEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TUtils.showSystemKeyboard(activity, viewHolder.mCommentsEdittext);
            }
        });
    }

    public void setHintText(String hintText){
        viewHolder.mCommentsEdittext.setHint(hintText);
    }


    private View contentView;
    private ViewHolder viewHolder;
    private PopupWindow popupWindow;
    private int dayNumber;

    public void show(TPlClickListenter listenter) {
        this.show(listenter, 0);
    }

    public void show(TPlClickListenter listenter, int dayNumber) {
        this.listenter = listenter;
        this.dayNumber = dayNumber;
        this.show();
    }

    private void show() {
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.comments_submit) {
            if (null != listenter) {
                String string = viewHolder.mCommentsEdittext.getText().toString().trim();
                listenter.fbClick(string);
            }
        }
        popupWindow.dismiss();
    }


    public interface TPlClickListenter {

        /**
         * 发表按钮
         * @param fbContent 发表内容
         */
        void fbClick(String fbContent);

    }

    class ViewHolder {
        View view;
        RelativeLayout relativeLayout;
        EditText mCommentsEdittext;
        TextView mCommentsSubmit;

        ViewHolder(View view) {
            this.view = view;
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.comments_rl);
            this.mCommentsEdittext = (EditText) view.findViewById(R.id.comments_edittext);
            this.mCommentsSubmit = (TextView) view.findViewById(R.id.comments_submit);
        }
    }
}
