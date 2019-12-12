package com.mihua.yangben.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.bean.UserBeans;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by lx on 2019/4/18.
 */

public class ModifyAvatarFm extends Fragment implements View.OnClickListener {
    private View views;
    private TextView userName;
    private EditText name;
    private EditText policeNo;
    private EditText deptName;
    private EditText duties;
    private Button save;
    private LoadingDialog mDialog;
    private String names;
    private String policeNos;
    private String deptNames;
    private String dutiess;
    private ImageView back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_modify_avatar, container, false);
        initView();
        initData();
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

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void handleEvent(MyEventMessage message) {
        // Log.d(TAG,);
    }

    private void initData() {
        String userInfo = SPUtils.getString(getActivity(), "userInfo", "");
        //   Logger.e("ModifyAvatar"+userInfo);
        if (!TextUtils.isEmpty(userInfo)) {
            Gson mgson = new Gson();
            UserBeans userBeans = mgson.fromJson(userInfo, UserBeans.class);
            if (userBeans.getCode() == 0) {
                userName.setText(userBeans.getUser().getUserName());

                names = userBeans.getUser().getName();
                policeNos = userBeans.getUser().getPoliceNo();
                deptNames = userBeans.getUser().getDeptName();
                dutiess = userBeans.getUser().getDuties();


            }
        }
    }

    private void initView() {
        back = views.findViewById(R.id.back);
        userName = views.findViewById(R.id.userName);
        name = views.findViewById(R.id.name);
        policeNo = views.findViewById(R.id.policeNo);
        deptName = views.findViewById(R.id.deptName);
        duties = views.findViewById(R.id.duties);
        save = views.findViewById(R.id.save);
        save.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                saveData();
                break;
            case R.id.back:
                jump();
                //    Toast.makeText(getActivity(), "111", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void jump() {
        MyEventMessage myEventMessage = new MyEventMessage();
        myEventMessage.setModify(1);
        EventBus.getDefault().post(myEventMessage);
    }

    private void saveData() {
        String deptNamess = deptName.getText().toString().trim();
        String dutiesss = duties.getText().toString().trim();
        String namess = name.getText().toString().trim();
        String policeNoss = policeNo.getText().toString().trim();
        if (TextUtils.isEmpty(deptNamess) & TextUtils.isEmpty(dutiesss) & TextUtils.isEmpty(namess) & TextUtils.isEmpty(policeNoss)) {
            Toast.makeText(getActivity(), "修改信息不能全部为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(deptNamess)) {
            deptNamess = deptNames;
        }
        if (TextUtils.isEmpty(dutiesss)) {
            dutiesss = dutiess;
        }
        if (TextUtils.isEmpty(namess)) {
            namess = names;
        }
        if (TextUtils.isEmpty(policeNoss)) {
            policeNoss = policeNos;
        }
        if (mDialog == null) {
            mDialog = new LoadingDialog(getActivity());
        }
        mDialog.setTvTip("正在提交数据");
        mDialog.show();

    }
}
