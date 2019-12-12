package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.VideoListBeans;

import java.util.ArrayList;

/**
 * Created by lx on 2019/4/16.
 */

public class VideoListAdapter extends BaseAdapter {
    private Context context;//声明适配器中引用的上下文
    private ArrayList<VideoListBeans.VideoListBean> data;

    //通过构造方法初始化上下文
    public VideoListAdapter(Context context, ArrayList<VideoListBeans.VideoListBean> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_video_list, null);
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textView = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(R.mipmap.videoimageview);
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // holder.img.setLayoutParams(new GridView.LayoutParams(300,200));
        holder.textView.setText(data.get(i).getName());//
        return convertView;

    }

    private class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

}
