package com.trj.tlib.t_modules;

import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tlib.R;
import com.trj.tlib.assist.Constants;
import com.trj.tlib.views.XListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author tong
 * @date 2018/5/3 12:13
 * XListView 模块化
 * M 代表返回值的类型
 */
public class TXListviewModule implements XListView.IXListViewListener {

    private ProgressBar mProgressBar;
    private XListView xListView;
    private Handler mHandler;
    private int page = 1;

    /**
     * 当前页面状态：0.正常；1.默认页面；2.异常页面
     */
    private int state = 0;

    private boolean openRefresh, openLoad;

    //默认布局的控件
    private RelativeLayout defRl;
    private ImageView defImg;
    private TextView defText;

    private String defTextStr = "";
    /**
     * 设置默认布局的图片
     * @param resDrawable
     */
    public void setDefImg(@DrawableRes int resDrawable) {
        defImg.setImageResource(resDrawable);
    }
    /**
     * 设置默认布局的文本
     * @param defTextStr
     */
    public void setDefText(String defTextStr) {
        this.defTextStr = defTextStr;
    }

    public TXListviewModule(View rootView) {
        this(rootView,true,true);
    }

    public TXListviewModule(View rootView, boolean openRefresh, boolean openLoad) {
        initView(rootView,openRefresh,openLoad);
        setPage(1);
        isShowListView(false);
        setPage(1);
    }

    public void initView(View rootView, boolean openRefresh, boolean openLoad) {
        this.openRefresh = openRefresh;
        this.openLoad = openLoad;
        xListView = rootView.findViewById(R.id.xlistview);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        defRl = rootView.findViewById(R.id.layout_default_all);
        defImg = rootView.findViewById(R.id.layout_default_img);
        defText = rootView.findViewById(R.id.layout_default_text);
        if(!defTextStr.equals("")){
            defText.setText(defTextStr);
        }
        initSet(xListView);
        isShowListView(false);
        defRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defRl.setVisibility(View.GONE);
                isShowListView(false);
                if (state == 2){//点击异常页面
                    state = 0;
                    if (null != listenter) {
                        listenter.exceptionPageClickEvent();
                    }
                }else if(state == 1){
                    state = 0;
                    if (null != listenter) {
                        listenter.getData(page);
                    }
                }
            }
        });
    }

    private TXListViewListenter listenter;

    public void setTXListViewListenter(TXListViewListenter listenter) {
        this.listenter = listenter;
    }

    /**
     * 获取XListView 控件
     *
     * @return
     */
    public XListView getxListView() {
        return xListView;
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private void initSet(XListView xListView) {
        xListView.setPullRefreshEnable(openRefresh);
        xListView.setPullLoadEnable(openLoad);
        xListView.setAutoLoadEnable(true);
        xListView.setXListViewListener(this);
        xListView.setRefreshTime(getTime());
        mHandler = new Handler();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                if (null != listenter) {
                    listenter.getData(page);
                }
            }
        }, Constants.CInt.TIME_LR);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                if (null != listenter) {
                    listenter.getData(page);
                }
            }
        }, Constants.CInt.TIME_LR);
    }

    public void onLoad(int pageCount) {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime(getTime());
        if (pageCount > page) {
            xListView.setPullRefreshEnable(openRefresh);
            xListView.setPullLoadEnable(openLoad);
            xListView.setAutoLoadEnable(true);
        } else {
            xListView.setPullLoadEnable(false);
            xListView.setAutoLoadEnable(false);
        }
    }

    /**
     * 是否显示XListView
     *
     * @param isShow
     */
    public void isShowListView(boolean isShow) {
        if (isShow) {
            mProgressBar.setVisibility(View.GONE);
            xListView.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            xListView.setVisibility(View.GONE);
        }
    }

    public void isShowDefLayout(boolean isShow){
        if(page != 1){
            return;
        }
        if(isShow){
            state = 1;
            defRl.setVisibility(View.VISIBLE);
        }else{
            defRl.setVisibility(View.GONE);
        }
    }

    public String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * 请求异常方法
     */
    public void error(){
        if(page == 1){
            state = 2;
            setDefImg(R.mipmap.qsygg_shuaxing);
            isShowDefLayout(true);
        }else{
            if(page >1){
                page--;
                onLoad(1000);
            }
        }
    }


}
