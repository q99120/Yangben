package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.HistoryConsumables;

import java.util.ArrayList;

/**
 * Created by 李浩 on 2019/5/8.
 */

public class HistoryAdapters extends BaseAdapter {
    private Context context;//声明适配器中引用的上下文
    private ArrayList<HistoryConsumables> data;

    //通过构造方法初始化上下文
    public HistoryAdapters(Context context, ArrayList<HistoryConsumables> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.history_record_list, null);
            holder.name = convertView.findViewById(R.id.name);
            holder.number = convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(data.get(i).name + "");
        holder.number.setText(data.get(i).number + data.get(i).unit);//

        return convertView;

    }

    private class ViewHolder {
        public TextView name;
        public TextView number;


    }
}
