package com.trj.tlib.t_modules;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tlib.R;

/**
 * @author tong
 * @date 2018/3/18 18:28
 */

public class TitleFragmentModule {

    private View view;

    private TitleListenter listenter;
    /** 标题 */
    private String titleStr = "";
    /** 左边标题 */
    private String titleLeftStr = "";
    /** 返回按钮 */
    private boolean showBack = false;

    public TitleFragmentModule(View view) {
        this.view = view;
    }

    public TitleFragmentModule(View view, TitleListenter listenter) {
        this.view = view;
        this.listenter = listenter;
    }

    private RelativeLayout title_rl;
    private TextView lefttext;
    private TextView centertext;
    private TextView righttext;
    private ImageView back,menu;

    /**
     * 初始化标题栏
     * @param titleStr 标题名称
     */
    public void initTitle(String titleStr){
        initTitle(titleStr, false);
    }

    /**
     * 初始化标题栏
     * @param titleStr 标题名称
     * @param showBack 是否显示返回按钮
     */
    public void initTitle(String titleStr, boolean showBack){
        this.titleStr = titleStr;
        this.showBack = showBack;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        title_rl = view.findViewById(R.id.title_rl);
        back =  view.findViewById(R.id.title_back);
        lefttext =  view.findViewById(R.id.title_lefttext);
        centertext =  view.findViewById(R.id.title_centertext);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickBack(v);
                }
            }
        });
        lefttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickLeftText(v);
                }
            }
        });
        //设置标题
        setTitleText(titleStr);
        //显示返回按钮
        setShowBack(showBack);
    }

    /**
     * 设置标题的层级
     */
    public void setTitleElevation(float translationZ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            title_rl.setTranslationZ(translationZ);
        }
    }

    /**
     * 设置标题(默认显示中间)
     *
     * @param titleStr
     */
    public void setTitleText(String titleStr) {
        centertext.setText(titleStr);
    }

    /**
     * 设置左边标题的显示
     */
    public void setTitleLeftText(String titleLeftStr){
        lefttext.setText(titleLeftStr);
    }

    /**
     * 设置是否显示返回按钮：默认不显示
     * @param showBack
     */
    private void setShowBack(boolean showBack) {
        if(showBack){
            back.setVisibility(View.VISIBLE);
        }else{
            back.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题返回按钮的图标
     * @param resId
     */
    public void setBackImage(@DrawableRes int resId){
        back.setImageResource(resId);
    }


    /** 菜单类型 */
    private int menuType = MENU_NONE;
    /** 菜单文字 */
    private String menuStr = "";

    /*不显示菜单文本和按钮(默认)*/
    public static final int MENU_NONE = 0;
    /*显示菜单按钮*/
    public static final int MENU_IMAGE = 1;
    /*显示菜单文本*/
    public static final int MENU_TEXT = 2;
    /*显示菜单文本和按钮*/
    public static final int MENU_ALL = 3;


    /**
     * 初始化标题菜单
     * @param menuType 菜单类型
     */
    public void initTitleMenu(int menuType){
        this.initTitleMenu(menuType,"");
    }

    /**
     * 初始化标题菜单
     * @param menuType 菜单类型
     * @param menuStr 菜单显示文本
     */
    public void initTitleMenu(int menuType,String menuStr){
        this.menuType = menuType;
        initMenu();
    }

    /**
     * 初始化标题的菜单按钮
     */
    private void initMenu() {
        righttext =  view.findViewById(R.id.title_righttext);
        menu =  view.findViewById(R.id.title_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickRightText(v);
                }
            }
        });
        righttext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickMenu(v);
                }
            }
        });
        righttext.setText(menuStr);
        setMenuVisibility();
    }

    /**
     * 设置标题菜单的显示
     */
    private void setMenuVisibility(){
        righttext.setVisibility(View.GONE);
        menu.setVisibility(View.GONE);
        if(menuType == MENU_IMAGE){
            menu.setVisibility(View.VISIBLE);
        }else if(menuType == MENU_TEXT){
            righttext.setVisibility(View.VISIBLE);
        }else if(menuType == MENU_ALL){
            righttext.setVisibility(View.VISIBLE);
            menu.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置菜单文本
     * @param titleStr
     */
    public void setMenuText(String titleStr) {
        righttext.setText(titleStr);
    }

    /* 设置菜单文本的颜色 */
    public void setMenuTextColor(@ColorRes int colorRes){
        righttext.setTextColor(view.getResources().getColor(colorRes));
    }

    //设置标题菜单的图标

    /**
     * 设置标题菜单的图标
     * @param resId 图片资源id
     */
    public void setTitleMenuImage(@DrawableRes int resId){
        menu.setImageResource(resId);
    }

}
