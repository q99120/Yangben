package com.mihua.yangben.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mihua.yangben.R;


/**
 * Created by zq on 16/4/12.
 */
public class CustomProgressDialog extends Dialog {
    private ImageView mLoadingImg;

    private TextView mMesssageTV;

    public CustomProgressDialog(Context context) {
        super(context, R.style.transparent_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_dialog);
        mLoadingImg = findViewById(R.id.loadingimg);
        mMesssageTV = findViewById(R.id.messagetv);
    }

    /**
     * 当没有消息时只展示菊花
     *
     * @param message
     */
    public void showMessage(String message) {
        try {
            super.show();
            if (!TextUtils.isEmpty(message)) {
                mMesssageTV.setText(message);
                mMesssageTV.setVisibility(View.VISIBLE);
            } else {
                mMesssageTV.setText("");
                mMesssageTV.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        mLoadingImg.startAnimation(animation);
    }
}
