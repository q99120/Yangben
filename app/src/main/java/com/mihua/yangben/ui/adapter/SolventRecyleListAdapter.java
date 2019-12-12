package com.mihua.yangben.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.SolventBean;

import java.util.List;

/**
 * 历史记录列表适配器
 */
public class SolventRecyleListAdapter extends RecyclerView.Adapter<SolventRecyleListAdapter.ViewHolder> {

    private List<SolventBean> list;
    Context context;
    int flag;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solven
                , parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SolventBean solventBean = list.get(position);
        holder.tv_yangben_name.setText(solventBean.getSolven_name());
        holder.tv_yangben_num.setText(solventBean.getSolven_num() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public SolventRecyleListAdapter(Context contexts, List<SolventBean> arrays) {
        context = contexts;
        list = arrays;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        //        public CheckBox cb;
        public TextView tv_yangben_name;
        public TextView tv_yangben_num;


        public ViewHolder(View view) {
            super(view);
            fruitView = view;
//            cb = view.findViewById(R.id.cb);
            tv_yangben_name = view.findViewById(R.id.tv_yangben_name);
            tv_yangben_num = view.findViewById(R.id.tv_yangben_num);
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
