package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.SampleList;
import com.mihua.yangben.interfaces.UpData;

import java.util.ArrayList;

/**
 * Created by lx on 2019/4/16.
 */

public class SampleListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;//声明适配器中引用的上下文
    private ArrayList<SampleList> data;
    private UpData upData;
    public int index;

    //通过构造方法初始化上下文
    public SampleListAdapter(Context context, ArrayList<SampleList> data, UpData upData) {
        this.context = context;
        this.data = data;
        this.upData = upData;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_sample_list, null);
            holder.ids = convertView.findViewById(R.id.ids);
            holder.sampleName = convertView.findViewById(R.id.sampleName);
            holder.information = convertView.findViewById(R.id.information);
            holder.delete = convertView.findViewById(R.id.delete);
            holder.updata = convertView.findViewById(R.id.updata);
            holder.sampleNum = convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        index = i;

        holder.ids.setText(data.get(i).getId() + "");
        holder.sampleName.setText(data.get(i).getSampleName());//
        holder.information.setText(data.get(i).getInformation());
        holder.sampleNum.setText(data.get(i).getSampleNumber());
      /*  holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.orhanobut.logger.Logger.e("我点击了"+index);
                upData.delete(index);
            }
        });
        holder.updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.orhanobut.logger.Logger.e("我点击了"+index);
                upData.updata(index);
            }
        });*/
        holder.delete.setOnClickListener(this);
        holder.updata.setOnClickListener(this);
        holder.delete.setTag(i);
        holder.updata.setTag(i);
        return convertView;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete:
                upData.delete(view);
                break;
            case R.id.updata:
                upData.updata(view);
                break;
        }

    }

    private class ViewHolder {
        public TextView ids;
        public TextView sampleName;
        public TextView information;
        public LinearLayout updata;
        public LinearLayout delete;
        public TextView sampleNum;

    }
}
