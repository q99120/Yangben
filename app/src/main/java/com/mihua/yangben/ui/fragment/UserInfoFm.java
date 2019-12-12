package com.mihua.yangben.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.bean.UserBeans;
import com.mihua.yangben.network.AppConfig;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.NetStateUtils;
import com.mihua.yangben.utils.SPUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by lx on 2019/4/18.
 */

public class UserInfoFm extends Fragment implements View.OnClickListener {
    private View views;
    private TextView updataAvatar;
    private TextView information;

    private TextView userNames;
    private TextView deptNames;
    private TextView userName;
    private TextView name;
    private TextView policeNo;
    private TextView deptName;
    private TextView duties;

    /**
     * 从样本处理首页跳转到样本处理详情页面
     */
    public static final int PAGE_COLLAPSING_TOOLSBAR = 4;
    /**
     * 从首页跳转到视频列表页面
     */
    public static final int PAGE_COLLAPSING_FIVES = 5;
    private LoadingDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragement_user_info, container, false);

        initView();
        //     if (networkConnected){
        //   initData();
        //  }else {
        local();
        //    }

        return views;
    }

    private void local() {
        String userInfo = SPUtils.getString(getActivity(), "userInfo", "");
        Logger.e("userInfo::" + userInfo);
        if (!TextUtils.isEmpty(userInfo)) {
            Gson mgson = new Gson();
            UserBeans userBeans = mgson.fromJson(userInfo, UserBeans.class);
            if (userBeans.getCode() == 0) {
                userNames.setText(userBeans.getUser().getUserName());
                deptNames.setText(userBeans.getUser().getDeptName());
                userName.setText(userBeans.getUser().getUserName());
                name.setText(userBeans.getUser().getName());
                policeNo.setText(userBeans.getUser().getPoliceNo() + "");
                deptName.setText(userBeans.getUser().getDeptName());
                duties.setText(userBeans.getUser().getDuties());
            }
        } else {
            Toast.makeText(getActivity(), "请检测网络状态稍后再试", Toast.LENGTH_SHORT).show();
        }

    }

    private void initData() {
        if (mDialog == null) {
            mDialog = new LoadingDialog(getActivity());
        }
        mDialog.setTvTip("正在获取数据");
        mDialog.show();
        String url = AppConfig.BASE_URL + AppConfig.USERINFO;
        Gson mgson = new Gson();
    }

    private void saveUser(String result) {
        SPUtils.putString(getActivity(), "userInfo", result);
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void handleEvent(MyEventMessage message) {
        if (message.getUpdata() != 0) {
            initData();
        }
    }

    private void initView() {
        updataAvatar = views.findViewById(R.id.updataAvatar);
        information = views.findViewById(R.id.information);


        userNames = views.findViewById(R.id.userNames);
        deptNames = views.findViewById(R.id.deptNames);
        userName = views.findViewById(R.id.userName);
        name = views.findViewById(R.id.name);
        policeNo = views.findViewById(R.id.policeNo);
        deptName = views.findViewById(R.id.deptName);
        duties = views.findViewById(R.id.duties);

        updataAvatar.setOnClickListener(this);
        information.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updataAvatar:
                boolean networkConnected = NetStateUtils.isNetworkConnected(getActivity());
                if (networkConnected) {
                    MyEventMessage myEventMessage = new MyEventMessage();
                    myEventMessage.setModify(PAGE_COLLAPSING_TOOLSBAR);
                    EventBus.getDefault().post(myEventMessage);
                } else {
                    Toast.makeText(getActivity(), "请检测网络状态稍后再试", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.information:
                boolean networkConnecteds = NetStateUtils.isNetworkConnected(getActivity());
                if (networkConnecteds) {
                    MyEventMessage myEventMessages = new MyEventMessage();
                    myEventMessages.setModify(PAGE_COLLAPSING_FIVES);
                    EventBus.getDefault().post(myEventMessages);
                } else {
                    Toast.makeText(getActivity(), "请检测网络状态稍后再试", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
