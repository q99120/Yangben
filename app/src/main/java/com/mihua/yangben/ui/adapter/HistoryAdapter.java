package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.SampleList;

import java.util.ArrayList;

/**
 * Created by lx on 2019/4/17.
 */

public class HistoryAdapter extends BaseAdapter {
    private Context context;//声明适配器中引用的上下文
    private ArrayList<SampleList> data;

    //通过构造方法初始化上下文
    public HistoryAdapter(Context context, ArrayList<SampleList> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_history_list, null);
            holder.ids = convertView.findViewById(R.id.ids);
            holder.sampleName = convertView.findViewById(R.id.sampleName);
            holder.information = convertView.findViewById(R.id.information);
            holder.sampleNo = convertView.findViewById(R.id.sampleNo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ids.setText(data.get(i).getId() + "");
        holder.sampleName.setText(data.get(i).getSampleName());//
        holder.information.setText(data.get(i).getInformation());
        holder.sampleNo.setText(data.get(i).getSampleNumber());
        return convertView;

    }

    private class ViewHolder {
        public TextView ids;
        public TextView sampleName;
        public TextView information;
        public TextView sampleNo;

    }
}
