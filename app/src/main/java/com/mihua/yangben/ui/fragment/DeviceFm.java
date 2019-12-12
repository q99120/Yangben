package com.mihua.yangben.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;


/**
 * Created by lx on 2019/4/18.
 */

public class DeviceFm extends Fragment {
    private View views;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragement_device, container, false);
        initView();
        return views;
    }

    private void initView() {

    }
}
