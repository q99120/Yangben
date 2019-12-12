package com.mihua.yangben.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mihua.yangben.R;


/**
 * Created by 李浩 on 2019/4/24.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {

        this(context, R.style.loading_dialog);
        //  tvTip = (TextView)this.findViewById(R.id.tvTip);
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);

    }

    private String mTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        TextView tvTip = this.findViewById(R.id.tvTip);
        tvTip.setText(mTip == null ? "请稍后..." : mTip);
        LinearLayout linearLayout = this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }

    public void setTvTip(String tipStr) {
        mTip = tipStr;
    }
}
