package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.db.entity.HisRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 历史记录列表适配器
 */
public class HisRecyleAdapter extends RecyclerView.Adapter<HisRecyleAdapter.ViewHolder> {


    /**
     * checkBox的HashMap集合
     */
    //是否显示单选框,默认false
    private boolean isshowBox = false;
    // 存储勾选框状态的map集合
    private Map<Integer, Boolean> map = new HashMap<>();
    private List<HisRecord> hisRecordList;
    Context context;
    int flag;


    public HisRecyleAdapter(Context contexts, List<HisRecord> hisRecords) {
        context = contexts;
        hisRecordList = hisRecords;
        initMap();
    }

    //初始化map集合,默认为不选中
    private void initMap() {
        for (int i = 0; i < hisRecordList.size(); i++) {
            map.put(i, false);
        }
    }


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
        if (hisRecord.ck_visible) {
            holder.cb.setVisibility(View.VISIBLE);
        } else {
            holder.cb.setVisibility(View.INVISIBLE);
        }
        if (hisRecord.ck_check) {
            holder.cb.setImageResource(R.mipmap.has_check);
        } else {
            holder.cb.setImageResource(R.mipmap.not_check);
        }
        holder.recordNo.setText(hisRecord.getmRecordNum());
        holder.times.setText("处理时间:" + hisRecord.getmProTime());
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


    static class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        public ImageView cb;
        public TextView recordNo;
        public TextView status;
        public TextView nodeFailure;
        public TextView times;

        public ViewHolder(View view) {
            super(view);
            root = view;
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
