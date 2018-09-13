package com.trj.tlib.t_modules;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tlib.R;
import com.trj.tlib.activity.InitActivity;
import com.trj.tlib.views.TListView;

import java.util.List;

/**
 * @author tong
 * @date 2018/3/18 18:28
 */

public class TitleModule {

    private InitActivity activity;

    private TitleListenter listenter;
    /** 标题 */
    private String titleStr = "";
    /** 左边标题 */
    private String titleLeftStr = "";
    /** 返回按钮 */
    private boolean showBack = false;

    public TitleModule(InitActivity activity) {
        this.activity = activity;
    }

    public void setListenter(TitleListenter listenter) {
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
        title_rl = activity.findViewById(R.id.title_rl);
        back =  activity.findViewById(R.id.title_back);
        lefttext =  activity.findViewById(R.id.title_lefttext);
        centertext =  activity.findViewById(R.id.title_centertext);
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

    public void setTitleBackground(@ColorRes int colorRes){
        title_rl.setBackgroundColor(activity.getResources().getColor(colorRes));
    }

    /**
     * 设置标题
     *
     * @param titleStr
     */
    public void setTitleText(String titleStr) {
        centertext.setText(titleStr);
    }

    /**
     * 设置标题颜色
     *
     * @param colorRes
     */
    public void setTitleTextColor(@ColorRes int colorRes) {
        centertext.setTextColor(activity.getResources().getColor(colorRes));
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
        this.menuStr = menuStr;
        initMenu();
    }

    /**
     * 初始化标题的菜单按钮
     */
    private void initMenu() {
        righttext =  activity.findViewById(R.id.title_righttext);
        menu =  activity.findViewById(R.id.title_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickMenu(v);
                }
            }
        });
        righttext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(null != listenter){
                    listenter.onClickRightText(v);
                }
            }
        });
        righttext.setText(menuStr);
        setMenuVisibility(menuType);
    }

    /**
     * 设置标题菜单的显示
     */
    public void setMenuVisibility(int menuType){
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
        righttext.setTextColor(activity.getResources().getColor(colorRes));
    }

    //设置标题菜单的图标

    /**
     * 设置标题菜单的图标
     * @param resId 图片资源id
     */
    public void setTitleMenuImage(@DrawableRes int resId){
        menu.setImageResource(resId);
    }

    private List<String> stringList;
    public void setMenuContent(List<String> stringList){
        this.stringList = stringList;
    }

    private PopupWindow popupWindow;
    public void showMenuItem(){
        if (null != popupWindow && popupWindow.isShowing()) {
            return;
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.title_menu, null);
        TListView tListview = view.findViewById(R.id.title_menu_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.item_menu, R.id.item_menu_textview, stringList);
        tListview.setAdapter(adapter);
        tListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onMenuItemClick(position);
            }
        });
        popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.showAsDropDown(menu,0,activity.getResources().getDimensionPixelOffset(R.dimen.ttitle_menu_top));

    }

    public void onMenuItemClick(int position){
        popupWindow.dismiss();
        if(null!=listenter){
            listenter.onMenuItemClick(position);
        }
    }


}
