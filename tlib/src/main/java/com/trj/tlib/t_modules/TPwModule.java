package com.trj.tlib.t_modules;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.trj.tlib.R;

/**
 * @author tong
 * @date 2018/3/28 18:02
 */
public class TPwModule implements View.OnClickListener{

    private Activity activity;

    private TPwClickListenter listenter;

    public TPwModule(Activity activity) {
        this(activity, "");
    }

    public TPwModule(Activity activity, String title) {
        this.activity = activity;
        contentView = LayoutInflater.from(activity).inflate(R.layout.layout_qd, null);
        viewHolder = new ViewHolder(contentView);
        if(null!= title && !title.equals("")){
            viewHolder.mQdTitle.setText(title);
        }
        viewHolder.mQdLl1.setOnClickListener(this);
        viewHolder.mQdLl2.setOnClickListener(this);
        viewHolder.mQdLl3.setOnClickListener(this);
        viewHolder.mQdLl4.setOnClickListener(this);
        viewHolder.mQdLl5.setOnClickListener(this);
        viewHolder.mQdLl6.setOnClickListener(this);
        viewHolder.mQdLl7.setOnClickListener(this);
        viewHolder.mQdClose.setOnClickListener(this);
    }

    private View contentView;
    private ViewHolder viewHolder;
    private PopupWindow popupWindow;
    private int dayNumber;

    public void show(TPwClickListenter listenter){
        this.show(listenter,0);
    }
    public void show(TPwClickListenter listenter,int dayNumber){
        this.listenter = listenter;
        this.dayNumber = dayNumber;
        setDayNumber(dayNumber);
        this.show();
    }

    public void setDayNumber(int dayNumber) {
        switch (dayNumber){
            case 7:
                viewHolder.mQdLl7.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl7Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl7Text2.setTextColor(Color.parseColor("#FEEA8F"));

            case 6:
                viewHolder.mQdLl6.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl6Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl6Text2.setTextColor(Color.parseColor("#FEEA8F"));
            case 5:
                viewHolder.mQdLl5.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl5Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl5Text2.setTextColor(Color.parseColor("#FEEA8F"));
            case 4:
                viewHolder.mQdLl4.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl4Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl4Text2.setTextColor(Color.parseColor("#FEEA8F"));
            case 3:
                viewHolder.mQdLl3.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl3Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl3Text2.setTextColor(Color.parseColor("#FEEA8F"));

            case 2:
                viewHolder.mQdLl2.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl2Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl2Text2.setTextColor(Color.parseColor("#FEEA8F"));

            case 1:
                viewHolder.mQdLl1.setBackgroundResource(R.mipmap.qd_bg);
                viewHolder.mQdLl1Text1.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.mQdLl1Text2.setTextColor(Color.parseColor("#FEEA8F"));
                break;

        }
    }
    public void setSign(int dayNumber,boolean isSignIn) {

        switch (dayNumber){
            case 1:
                viewHolder.mQdLl1Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl1Text3.setText("已签到");
                }
                break;
            case 2:
                viewHolder.mQdLl2Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl2Text3.setText("已签到");
                }
                break;
            case 3:
                viewHolder.mQdLl3Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl3Text3.setText("已签到");
                }
                break;
            case 4:
                viewHolder.mQdLl4Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl4Text3.setText("已签到");
                }
                break;
            case 5:
                viewHolder.mQdLl5Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl5Text3.setText("已签到");
                }
                break;
            case 6:
                viewHolder.mQdLl6Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl6Text3.setText("已签到");
                }
                break;
            case 7:
                viewHolder.mQdLl7Text3.setVisibility(View.VISIBLE);
                if(isSignIn) {
                    viewHolder.mQdLl7Text3.setText("已签到");
                }
                break;

        }

    }

    private void show(){
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 200);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if(ids == R.id.qd_ll1){
            if(null!=listenter){
                if(listenter.pwClick(1)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_ll2){
            if(null!=listenter){
                if(listenter.pwClick(2)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_ll3){
            if(null!=listenter){
                if(listenter.pwClick(3)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_ll4){
            if(null!=listenter){
                if(listenter.pwClick(4)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_ll5){
            if(null!=listenter){
                if(listenter.pwClick(5)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_ll6){
            if(null!=listenter){
                if(listenter.pwClick(6)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_ll7){
            if(null!=listenter){
                if(listenter.pwClick(7)){
                    popupWindow.dismiss();
                }
            }
        }else if(ids == R.id.qd_close){
            if(null!=listenter){
                popupWindow.dismiss();
            }
        }
    }


    public interface TPwClickListenter{

        void pwClickClose();
        boolean pwClick(int days);
//        void pwClickOne(int days);
//        void pwClickTwo(int days);
//        void pwClickThree(int days);
//        void pwClickFour(int days);
//        void pwClickFive(int days);
//        void pwClickSix(int days);
//        void pwClickSeven(int days);
//        void pwClickOne(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);
//        void pwClickTwo(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);
//        void pwClickThree(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);
//        void pwClickFour(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);
//        void pwClickFive(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);
//        void pwClickSix(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);
//        void pwClickSeven(LinearLayout linearLayout,TextView textView1,TextView textView2,TextView textView3);

    }


    class ViewHolder {
        View view;
        TextView mQdTitle;
        TextView mQdLl1Text1;
        TextView mQdLl1Text2;
        TextView mQdLl1Text3;
        LinearLayout mQdLl1;
        TextView mQdLl2Text1;
        TextView mQdLl2Text2;
        TextView mQdLl2Text3;
        LinearLayout mQdLl2;
        TextView mQdLl3Text1;
        TextView mQdLl3Text2;
        TextView mQdLl3Text3;
        LinearLayout mQdLl3;
        TextView mQdLl4Text1;
        TextView mQdLl4Text2;
        TextView mQdLl4Text3;
        LinearLayout mQdLl4;
        TextView mQdLl5Text1;
        TextView mQdLl5Text2;
        TextView mQdLl5Text3;
        LinearLayout mQdLl5;
        TextView mQdLl6Text1;
        TextView mQdLl6Text2;
        TextView mQdLl6Text3;
        LinearLayout mQdLl6;
        TextView mQdLl7Text1;
        TextView mQdLl7Text2;
        TextView mQdLl7Text3;
        LinearLayout mQdLl7;
        ImageView mQdClose;

        ViewHolder(View view) {
            this.view = view;
            this.mQdTitle = (TextView) view.findViewById(R.id.qd_title);
            this.mQdLl1Text1 = (TextView) view.findViewById(R.id.qd_ll1_text1);
            this.mQdLl1Text2 = (TextView) view.findViewById(R.id.qd_ll1_text2);
            this.mQdLl1Text3 = (TextView) view.findViewById(R.id.qd_ll1_text3);
            this.mQdLl1 = (LinearLayout) view.findViewById(R.id.qd_ll1);
            this.mQdLl2Text1 = (TextView) view.findViewById(R.id.qd_ll2_text1);
            this.mQdLl2Text2 = (TextView) view.findViewById(R.id.qd_ll2_text2);
            this.mQdLl2Text3 = (TextView) view.findViewById(R.id.qd_ll2_text3);
            this.mQdLl2 = (LinearLayout) view.findViewById(R.id.qd_ll2);
            this.mQdLl3Text1 = (TextView) view.findViewById(R.id.qd_ll3_text1);
            this.mQdLl3Text2 = (TextView) view.findViewById(R.id.qd_ll3_text2);
            this.mQdLl3Text3 = (TextView) view.findViewById(R.id.qd_ll3_text3);
            this.mQdLl3 = (LinearLayout) view.findViewById(R.id.qd_ll3);
            this.mQdLl4Text1 = (TextView) view.findViewById(R.id.qd_ll4_text1);
            this.mQdLl4Text2 = (TextView) view.findViewById(R.id.qd_ll4_text2);
            this.mQdLl4Text3 = (TextView) view.findViewById(R.id.qd_ll4_text3);
            this.mQdLl4 = (LinearLayout) view.findViewById(R.id.qd_ll4);
            this.mQdLl5Text1 = (TextView) view.findViewById(R.id.qd_ll5_text1);
            this.mQdLl5Text2 = (TextView) view.findViewById(R.id.qd_ll5_text2);
            this.mQdLl5Text3 = (TextView) view.findViewById(R.id.qd_ll5_text3);
            this.mQdLl5 = (LinearLayout) view.findViewById(R.id.qd_ll5);
            this.mQdLl6Text1 = (TextView) view.findViewById(R.id.qd_ll6_text1);
            this.mQdLl6Text2 = (TextView) view.findViewById(R.id.qd_ll6_text2);
            this.mQdLl6Text3 = (TextView) view.findViewById(R.id.qd_ll6_text3);
            this.mQdLl6 = (LinearLayout) view.findViewById(R.id.qd_ll6);
            this.mQdLl7Text1 = (TextView) view.findViewById(R.id.qd_ll7_text1);
            this.mQdLl7Text2 = (TextView) view.findViewById(R.id.qd_ll7_text2);
            this.mQdLl7Text3 = (TextView) view.findViewById(R.id.qd_ll7_text3);
            this.mQdLl7 = (LinearLayout) view.findViewById(R.id.qd_ll7);
            this.mQdClose = (ImageView) view.findViewById(R.id.qd_close);
        }
    }

}
