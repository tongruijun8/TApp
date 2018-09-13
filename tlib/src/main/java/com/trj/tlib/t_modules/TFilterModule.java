package com.trj.tlib.t_modules;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trj.tlib.R;
import com.trj.tlib.adapter.SelectSskAdapter;
import com.trj.tlib.views.TLinearLayout;

import java.util.List;

/**
 * @author tong
 * @date 2018/7/18 17:28
 */
public class TFilterModule implements TLinearLayout.TLLOnClickListenter {

    private Context context;
    private ViewHolder viewHolder;

    //搜索框显示状态
    private boolean isShowSsk = false;

    public TFilterModule(Context context, View view) {
        this.context = context;
        initLayoutView(view);
    }

    private void initLayoutView(View view) {
        viewHolder = new ViewHolder(view);
        viewHolder.mLayoutFilterLl1.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterLl2.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterLl3.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterLl4.setTLLOnClickListenter(this);
        viewHolder.mLayoutFilterScrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSSK();
                hideSsk();
            }
        });
        viewHolder.mLayoutFilterListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != listener) {
                    listener.onFilterItem(index, position);
                }
                hideSsk();
            }
        });
    }

    public void setFilterTitles(String filterTitle1, String filterTitle2, String filterTitle3, String filterTitle4){
        if (null != filterTitle1) {
            viewHolder.mLayoutFilterName1.setText(filterTitle1);
        }
        if (null != filterTitle2) {
            viewHolder.mLayoutFilterName2.setText(filterTitle2);
        }
        if (null != filterTitle3) {
            viewHolder.mLayoutFilterName3.setText(filterTitle3);
        }
        if (null != filterTitle4) {
            viewHolder.mLayoutFilterName4.setText(filterTitle4);
        }
    }


    private boolean oneShow,twoShow,threeShow, fourShow;
    private List<String> list1;
    private List<String> list2 ;
    private List<String> list3 ;
    private List<String> list4;

    public void setSskDataList(List<String> list1, List<String> list2, List<String> list3, List<String> list4){
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        isShowList();
    }

    private void isShowList(){
        if(null != list1){
            oneShow = true;
        }else{
            viewHolder.mLayoutFilterImg1.setVisibility(View.GONE);
        }
        if(null != list2){
            twoShow = true;
        }else{
            viewHolder.mLayoutFilterImg2.setVisibility(View.GONE);
        }
        if(null != list3){
            threeShow = true;
        }else{
            viewHolder.mLayoutFilterImg3.setVisibility(View.GONE);
        }
        if(null != list4){
            fourShow = true;
        }else{
            viewHolder.mLayoutFilterImg4.setVisibility(View.GONE);
        }
    }


    @Override
    public void onTLLClick(View v) {
        initSSK();
        if (v == viewHolder.mLayoutFilterLl1) {
            initSSKState(1,oneShow);
        } else if (v == viewHolder.mLayoutFilterLl2) {
            initSSKState(2,twoShow);
        } else if (v == viewHolder.mLayoutFilterLl3) {
            initSSKState(3,threeShow);
        } else if (v == viewHolder.mLayoutFilterLl4) {
            initSSKState(4,fourShow);
        }
    }

    //隐藏搜索框
    public void hideSsk(){
        viewHolder.mLayoutFilterScrollview.setVisibility(View.GONE);
        isShowSsk = false;
    }
    //隐藏搜索框
    private void showSsk(){
        viewHolder.mLayoutFilterScrollview.setVisibility(View.VISIBLE);
        isShowSsk = true;
    }

    private boolean toggleSsk(){
        if (viewHolder.mLayoutFilterScrollview.getVisibility() == View.VISIBLE) {
            hideSsk();
            return false;
        }else{
            showSsk();
            return true;
        }
    }

    private void bindListData(int index){
        if (index == 1) {
            if(null == list1){
                return;
            }
            SelectSskAdapter adapter = new SelectSskAdapter(context, list1, -1);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
        }else if(index == 2){
            if(null == list2){
                return;
            }
            SelectSskAdapter adapter = new SelectSskAdapter(context, list2, -1);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
        }else if(index == 3){
            if(null == list3){
                return;
            }
            SelectSskAdapter adapter = new SelectSskAdapter(context, list3, -1);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
        }else if(index == 4){
            if(null == list4){
                return;
            }
            SelectSskAdapter adapter = new SelectSskAdapter(context, list4, -1);
            viewHolder.mLayoutFilterListview.setAdapter(adapter);
        }
    }

    private int index = 0;

    /**
     * 初始化搜素框标题栏
     */
    private void initSSK() {
        viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_name));
        viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_name));
        viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_name));
        viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_name));
        viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_xiala);
        viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_xiala);
        viewHolder.mLayoutFilterImg3.setImageResource(R.mipmap.icon_xiala);
        viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_xiala);

    }

    /**
     * 初始化搜素框
     * @param position 位置
     * @param showSsk 是否需要显示搜索框
     */
    private void initSSKState(int position,boolean showSsk){
        if (position == 1) {
            if(!showSsk){
                viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_shouqi);
                hideSsk();
                if(null!=listener){
                    listener.onFilterItem(1,0);
                }
            }else{
                if (index == 1) {
                    if(toggleSsk()){
                        viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                        viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_shouqi);
                    }
                } else {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.ssk_height1));
                    viewHolder.mLayoutFilterListview.setLayoutParams(params);
                    viewHolder.mLayoutFilterName1.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                    viewHolder.mLayoutFilterImg1.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if(!isShowSsk){
                        showSsk();
                    }
                }
            }
            index = 1;

        } else if (position == 2) {
            if(!showSsk){
                viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                hideSsk();
                if(null!=listener){
                    listener.onFilterItem(2,0);
                }
            }else{
                if (index == 2) {
                    if(toggleSsk()){
                        viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                        viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                    }
                } else {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.ssk_height2));
                    viewHolder.mLayoutFilterListview.setLayoutParams(params);
                    viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                    viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if(!isShowSsk){
                        showSsk();
                    }
                }
            }
            index = 2;

        } else if (position == 3) {
            if(!showSsk){
                viewHolder.mLayoutFilterName2.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                viewHolder.mLayoutFilterImg2.setImageResource(R.mipmap.icon_shouqi);
                hideSsk();
                if(null!=listener){
                    listener.onFilterItem(3,0);
                }
            }else{
                if (index == 3) {
                    if(toggleSsk()){
                        viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                        viewHolder.mLayoutFilterImg3.setImageResource(R.mipmap.icon_shouqi);
                    }
                } else {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.ssk_height3));
                    viewHolder.mLayoutFilterListview.setLayoutParams(params);
                    viewHolder.mLayoutFilterName3.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                    viewHolder.mLayoutFilterImg3.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if(!isShowSsk){
                        showSsk();
                    }
                }
            }
            index = 3;
        } else if (position == 4) {
            if(!showSsk){
                viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_shouqi);
                hideSsk();
                if(null!=listener){
                    listener.onFilterItem(4,0);
                }
            }else{
                if (index == 4) {
                    if(toggleSsk()){
                        viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                        viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_shouqi);
                    }
                } else {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelOffset(R.dimen.ssk_height4));
                    viewHolder.mLayoutFilterListview.setLayoutParams(params);
                    viewHolder.mLayoutFilterName4.setTextColor(context.getResources().getColor(R.color.color_zhuse));
                    viewHolder.mLayoutFilterImg4.setImageResource(R.mipmap.icon_shouqi);
                    bindListData(position);
                    if(!isShowSsk){
                        showSsk();
                    }
                }
            }

            index = 4;
        }
    }


    private OnTFilterModuleListener listener;
    public void setOnTFilterModuleListener(OnTFilterModuleListener listener){
        this.listener = listener;
    }

    class ViewHolder {
        View view;
        TextView mLayoutFilterName1;
        ImageView mLayoutFilterImg1;
        TLinearLayout mLayoutFilterLl1;
        TextView mLayoutFilterName2;
        ImageView mLayoutFilterImg2;
        TLinearLayout mLayoutFilterLl2;
        TextView mLayoutFilterName3;
        ImageView mLayoutFilterImg3;
        TLinearLayout mLayoutFilterLl3;
        TextView mLayoutFilterName4;
        ImageView mLayoutFilterImg4;
        TLinearLayout mLayoutFilterLl4;
        LinearLayout mLayoutFilterLl;
        ListView mLayoutFilterListview;
        RelativeLayout mLayoutFilterScrollview;

        ViewHolder(View view) {
            this.view = view;
            this.mLayoutFilterName1 = (TextView) view.findViewById(R.id.layout_filter_name1);
            this.mLayoutFilterImg1 = (ImageView) view.findViewById(R.id.layout_filter_img1);
            this.mLayoutFilterLl1 = (TLinearLayout) view.findViewById(R.id.layout_filter_ll1);
            this.mLayoutFilterName2 = (TextView) view.findViewById(R.id.layout_filter_name2);
            this.mLayoutFilterImg2 = (ImageView) view.findViewById(R.id.layout_filter_img2);
            this.mLayoutFilterLl2 = (TLinearLayout) view.findViewById(R.id.layout_filter_ll2);
            this.mLayoutFilterName3 = (TextView) view.findViewById(R.id.layout_filter_name3);
            this.mLayoutFilterImg3 = (ImageView) view.findViewById(R.id.layout_filter_img3);
            this.mLayoutFilterLl3 = (TLinearLayout) view.findViewById(R.id.layout_filter_ll3);
            this.mLayoutFilterName4 = (TextView) view.findViewById(R.id.layout_filter_name4);
            this.mLayoutFilterImg4 = (ImageView) view.findViewById(R.id.layout_filter_img4);
            this.mLayoutFilterLl4 = (TLinearLayout) view.findViewById(R.id.layout_filter_ll4);
            this.mLayoutFilterLl = (LinearLayout) view.findViewById(R.id.layout_filter_ll);
            this.mLayoutFilterListview = (ListView) view.findViewById(R.id.layout_filter_listview);
            this.mLayoutFilterScrollview =  view.findViewById(R.id.layout_filter_scrollview);
        }
    }
}
