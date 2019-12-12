package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.db.entity.HisRecord;

import java.util.List;

/**
 * 历史记录列表适配器
 */
public class HisRclClickAdapter extends RecyclerView.Adapter<HisRclClickAdapter.ViewHolder> {

    private List<HisRecord> hisRecordList;
    Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item
                , parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("查询熟练", "onBindViewHolder: " + hisRecordList.size());
        HisRecord hisRecord = hisRecordList.get(position);
        holder.recordNo.setText(hisRecord.getmProNode());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("适配器点击", "onClick:适配器点击");
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hisRecordList.size();
    }

    public HisRclClickAdapter(Context contexts, List<HisRecord> hisRecords) {
        context = contexts;
        hisRecordList = hisRecords;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        public CheckBox cb;
        public TextView recordNo;
        public TextView status;
        public TextView nodeFailure;
        public TextView times;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            cb = view.findViewById(R.id.cb);
            recordNo = view.findViewById(R.id.recordNo);
            status = view.findViewById(R.id.status);
            nodeFailure = view.findViewById(R.id.nodeFailure);
            times = view.findViewById(R.id.times);
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
