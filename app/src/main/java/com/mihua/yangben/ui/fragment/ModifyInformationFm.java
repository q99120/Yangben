package com.mihua.yangben.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.SPUtils;


/**
 * Created by lx on 2019/4/18.
 */

public class ModifyInformationFm extends Fragment implements View.OnClickListener {
    private View views;
    private EditText originalPassword;
    private EditText newPassword;
    private EditText againNewPassword;
    private Button save;
    private LoadingDialog mDialog;
    private String newPasswords;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_modify_information, container, false);
        initView();
        return views;
    }

    private void initView() {
        originalPassword = views.findViewById(R.id.originalPassword);
        newPassword = views.findViewById(R.id.newPassword);
        againNewPassword = views.findViewById(R.id.againNewPassword);
        save = views.findViewById(R.id.save);
        save.setOnClickListener(this);
        originalPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        againNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void onClick(View view) {
        saves();
    }

    private void saves() {
        String password = SPUtils.getString(getActivity(), "password", "");
        //  Logger.e("原始密码 ："+password);
        String userName = SPUtils.getString(getActivity(), "userName", "");
        String originalPasswords = originalPassword.getText().toString().trim();
        newPasswords = newPassword.getText().toString().trim();
        String againNewPasswords = againNewPassword.getText().toString().trim();
        if (TextUtils.isEmpty(originalPasswords)) {
            Toast.makeText(getActivity(), "原始密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newPasswords)) {
            Toast.makeText(getActivity(), "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(againNewPasswords)) {
            Toast.makeText(getActivity(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!originalPasswords.equals(password)) {
            Toast.makeText(getActivity(), "原始密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPasswords.equals(originalPasswords)) {
            Toast.makeText(getActivity(), "新密码和原始密码不能相同", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPasswords.equals(againNewPasswords)) {
            Toast.makeText(getActivity(), "新密码和确认密码不相同", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mDialog == null) {
            mDialog = new LoadingDialog(getActivity());
        }
        mDialog.setTvTip("正在提交数据");
        mDialog.show();
    }
}
