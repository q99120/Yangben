package com.mihua.yangben.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.ui.activity.XLSFileListActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lx on 2019/4/18.
 */

public class SystemsFm extends Fragment {
    @BindView(R.id.cache)
    RelativeLayout cache;
    @BindView(R.id.filePath)
    TextView filePath;
    @BindView(R.id.select)
    RelativeLayout select;
    @BindView(R.id.btn_save)
    Button btnSave;
    private View views;
    //    private RelativeLayout select;
//    private TextView filePath;
//    private RelativeLayout cache;
    public static final int PAGE_COLLAPSING_FIVESS = 6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragement_system, container, false);
        ButterKnife.bind(this, views);
        return views;
    }


    //EventBus的注册，注销和事件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(MyEventMessage message) {
        if (message.getFileName() != null) {
            String[] split = message.getFileName().split(",");
            filePath.setText(split[0] + "");
        }
    }


    private void openActivity() {
        startActivity(new Intent(getActivity(), XLSFileListActivity.class));
    }

    @OnClick({R.id.cache, R.id.filePath, R.id.select, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cache:
                MyEventMessage message = new MyEventMessage();
                message.setModify(PAGE_COLLAPSING_FIVESS);
                EventBus.getDefault().post(message);
                break;
            case R.id.filePath:
                break;
            case R.id.select:
                openActivity();
                break;
            case R.id.btn_save:
                break;
        }
    }
}
