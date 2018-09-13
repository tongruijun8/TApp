package com.trj.tlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trj.tlib.R;

import java.util.List;

/**
 * @author tong
 * @date 2018/2/9 14:27
 */

public class SelectSskAdapter extends TBaseAdapter<String> {

    private int index;

    public SelectSskAdapter(Context context, List<String> strings, int index) {
        super(context, strings);
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(tList.get(position));
        if(index == position){
            viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.color_zhuse));
        }else{
            viewHolder.textView.setBackgroundColor(context.getResources().getColor(R.color.color_show));
        }

        return convertView;
    }

    class ViewHolder {

        TextView textView;

        public ViewHolder(View convertView) {
            textView = convertView.findViewById(android.R.id.text1);
        }
    }


}
