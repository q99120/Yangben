package com.mihua.yangben.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mihua.yangben.R;
import com.mihua.yangben.bean.VideoListBeans;
import com.mihua.yangben.network.AppConfig;
import com.mihua.yangben.ui.adapter.VideoListAdapter;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.NetStateUtils;
import com.mihua.yangben.utils.SPUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoListActivity extends AppCompatActivity {

    @BindView(R.id.option)
    GridView option;
    @BindView(R.id.back)
    LinearLayout back;
    //初始化图片资源
    private int[] draw = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    //初始化字符串
    private String[] texts = {"呋喃四项操作视频", "呋喃四项操作视频", "呋喃四项操作视频", "呋喃四项操作视频"};
    //GridView数据初始化集合
    private ArrayList<VideoListBeans.VideoListBean> homeGridViews;
    private VideoListAdapter homeGridAdapter;
    private LoadingDialog mDialog;

    private int post;
    public ProgressDialog dialog;
    private Handler mhander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(VideoListActivity.this, VideoActivity.class);
            intent.putExtra("url", AppConfig.BASE_URL + homeGridViews.get(post).getUrl());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        initView();
        boolean networkConnected = NetStateUtils.isNetworkConnected(this);

        //if (networkConnected){
        //      initData();
        // }else {
        local();
        // }


    }

    private void local() {
        String videolist = SPUtils.getString(VideoListActivity.this, "videolist", "");
        Logger.e("videolist::" + videolist);
        if (!TextUtils.isEmpty(videolist)) {
            requestJson(videolist);
        }
    }

    private void initView() {
        option.setOnItemClickListener(new ItemListener());
    }


    private void requestJson(String result) {
        Gson mgson = new Gson();
        VideoListBeans videoListBeans = mgson.fromJson(result, VideoListBeans.class);
        SPUtils.putString(VideoListActivity.this, "videolist", result);
        if (videoListBeans.getCode() == 0) {
            List<List<VideoListBeans.VideoListBean>> videoList = videoListBeans.getVideoList();
            if (videoList.size() == 0) {
                Toast.makeText(VideoListActivity.this, "暂无视频数据", Toast.LENGTH_SHORT).show();
            } else {

                homeGridViews = new ArrayList<>();
                for (int i = 0; i < videoList.size(); i++) {
                    for (int j = 0; j < videoList.get(i).size(); j++) {
                        List<VideoListBeans.VideoListBean> videoListBeans1 = videoList.get(i);
                        homeGridViews.add(videoListBeans1.get(j));
                    }
                }
                homeGridAdapter = new VideoListAdapter(VideoListActivity.this, homeGridViews);
                option.setAdapter(homeGridAdapter);
            }
        } else {
            Toast.makeText(VideoListActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        //返回按钮的点击事件
        finish();
    }

    private class ItemListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (mDialog != null) {
                mDialog.show();
            } else {
                mDialog = new LoadingDialog(VideoListActivity.this);
                mDialog.setTvTip("正在获取数据");
                mDialog.show();
            }
            post = i;
            mhander.sendEmptyMessageDelayed(1, 1000);


        }
    }

    @Override
    protected void onPause() {
        if (mDialog != null) {
            mDialog.hide();
        }

        if (dialog != null) {
            dialog.hide();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        super.onDestroy();
    }
}
