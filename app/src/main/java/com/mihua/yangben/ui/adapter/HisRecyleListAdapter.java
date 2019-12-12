package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.db.entity.HisRecord;

import java.util.List;

/**
 * 历史记录列表适配器
 */
public class HisRecyleListAdapter extends RecyclerView.Adapter<HisRecyleListAdapter.ViewHolder> {

    private List<HisRecord> hisRecordList;
    Context context;
    int flag;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert_dialog_hisitem
                , parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("查询熟练", "onBindViewHolder: " + hisRecordList.size());
        HisRecord hisRecord = hisRecordList.get(position);
        holder.tv_title_num.setText(hisRecord.getmSampleNum());
        holder.tv_title_info.setText(hisRecord.getmSampleInfo());
        holder.tv_title_sn.setText(hisRecord.getmSampleName());
        holder.tv_title_status.setText(hisRecord.getmSampleStatus());
    }

    @Override
    public int getItemCount() {
        return hisRecordList.size();
    }

    public HisRecyleListAdapter(Context contexts, List<HisRecord> hisRecords) {
        context = contexts;
        hisRecordList = hisRecords;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        //        public CheckBox cb;
        public TextView tv_title_num;
        public TextView tv_title_sn;
        public TextView tv_title_info;
        public TextView tv_title_status;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
//            cb = view.findViewById(R.id.cb);
            tv_title_num = view.findViewById(R.id.tv_title_num);
            tv_title_sn = view.findViewById(R.id.tv_title_sn);
            tv_title_info = view.findViewById(R.id.tv_title_info);
            tv_title_status = view.findViewById(R.id.tv_title_status);
        }
    }

    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnRclClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
