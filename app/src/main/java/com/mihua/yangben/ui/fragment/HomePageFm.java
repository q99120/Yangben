package com.mihua.yangben.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.HomeGridBean;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.datepicker.DateFormatUtils;
import com.mihua.yangben.db.database.RecordDatabase;
import com.mihua.yangben.db.entity.HisRecord;
import com.mihua.yangben.ui.activity.HomeActivity;
import com.mihua.yangben.ui.adapter.HisRecyleAdapter;
import com.mihua.yangben.ui.adapter.HomeGridAdapter;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.SimpleUtils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lx on 2019/4/9.
 */

public class HomePageFm extends Fragment {

    String TAG = "HomePageFm";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.rcl_historylist)
    RecyclerView rclHistorylist;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.grid)
    GridView grid;
    HisRecyleAdapter hisRecyleAdapter;
    private View views;
    public HomeActivity homeactivity;
    //GridView数据初始化集合
    private ArrayList<HomeGridBean> homeGridViews;
    //初始化图片资源HomeGridAdapter
    private int[] draw = {R.mipmap.video, R.mipmap.manual, R.mipmap.solvent, R.mipmap.correction};
    //初始化字符串
    private String[] texts = {"视频资料", "系统说明书", "溶剂设置", "仪器校准"};
    private HomeGridAdapter homeGridAdapter;
    /**
     * 从首页跳转到视频列表页面
     */
    public static final int PAGE_COLLAPSING_FIVES = 5;
    private LoadingDialog mDialog;
    private String str_time1;
    private boolean hide = false;
    HomeActivity activity;

    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mDialog.setTvTip("数据加载中");
                    mDialog.show();
                    mhandler.sendEmptyMessageDelayed(2, 1000);
                    break;
                case 2:
                    localInitData();
                    break;
                case 3:
                    mDialog.setTvTip("数据加载中");
                    mDialog.show();
                    mhandler.sendEmptyMessageDelayed(4, 1000);
                    break;
                case 4:
                    break;
                case 5:
                    hisRecordList.clear();
                    List<HisRecord> hisRecordList1;
                    hisRecordList1 = RecordDatabase.getinstance(homeactivity).hisRecordDao().loadAllHisByCurrentDay
                            (DateFormatUtils.str2Long(SimpleUtils.getCurretTimes(), false));
                    hisRecordList.addAll(hisRecordList1);
                    num.setText("当前样本处理记录(" + hisRecordList1.size() + ")");
                    hisRecyleAdapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, views);
        if (mDialog == null) {
            mDialog = new LoadingDialog(homeactivity);
        }
        initView();
        initData();
        onItemClicked();
        return views;
    }


    private void initView() {
        activity = homeactivity;
        LinearLayoutManager ll = new LinearLayoutManager(homeactivity);
        rclHistorylist.setLayoutManager(ll);
    }

    private void initData() {

        // setVideoInfo();
        //初始化数据集合
        homeGridViews = new ArrayList<>();
        for (int i = 0; i < draw.length; i++) {
            HomeGridBean homeGridView = new HomeGridBean();
            homeGridView.id = draw[i];
            homeGridView.content = texts[i];
            homeGridViews.add(homeGridView);
        }

        //初始化GridView的adapter
        homeGridAdapter = new HomeGridAdapter(homeactivity, homeGridViews);
        grid.setAdapter(homeGridAdapter);
        mhandler.sendEmptyMessageDelayed(1, 1000);
        // reQestImage();

    }

    private List<HisRecord> hisRecordList;

    private void localInitData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        str_time1 = sdf.format(dt);
        Log.e(TAG, "localInitData: " + str_time1);
        hisRecordList = RecordDatabase.getinstance(homeactivity).hisRecordDao().loadAllHisByCurrentDay(DateFormatUtils.str2Long(SimpleUtils.getCurretTimes(), false));
        hisRecyleAdapter = new HisRecyleAdapter(homeactivity, hisRecordList);
        if (hisRecordList.size() > 0) {
            Log.e(TAG, "localInitData2: " + hisRecordList.get(0).getmProDay());
        } else {
            Toast.makeText(homeactivity, "没有数据", Toast.LENGTH_SHORT).show();
        }
        num.setText("当前样本处理记录(" + hisRecordList.size() + ")");
        rclHistorylist.setAdapter(hisRecyleAdapter);
        if (mDialog != null) {
            mDialog.dismiss();
        }

        if (homeactivity == null) {
            Log.e(TAG, "ACTIVITY是空的");
        } else {

        }
        refreshLayout.setRefreshHeader(new ClassicsHeader(homeactivity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(homeactivity));
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            initRefreshDB();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            initRefreshDB();
        });
    }

    private void initRefreshDB() {
        mhandler.sendEmptyMessageDelayed(5, 500);
    }

    private void onItemClicked() {
        grid.setOnItemClickListener(new GridItemClick());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife(this);
    }


    private class GridItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0: //视频资料

                    if (activity != null) {
                        Log.e("activity", "onItemClick: " + "video");
                        activity.nextVideo();
                    }
                    break;
                case 1://系统说明书
                    break;
                case 2://溶剂设置
                    activity.SolveSet();
                    break;
                case 3://仪器校准
                    break;
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {

//隐藏时所作的事情


        } else {
            if (hide) {
                //  localInitData(false);
                mhandler.sendEmptyMessageDelayed(1, 1000);
                hide = false;
            }


        }
    }

    //EventBus的注册，注销和事件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onViewCreated(view, savedInstanceState);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(MyEventMessage message) {

        if (message.getUpdatas() != null) {
            //   localInitData(false);
            hide = true;
        }
    }

    private void nextPage() {
        MyEventMessage myEventMessage = new MyEventMessage();
        myEventMessage.setProcessingDetails(PAGE_COLLAPSING_FIVES);
        EventBus.getDefault().post(myEventMessage);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        homeactivity = (HomeActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        //结束轮播
        banner.stopAutoPlay();
        super.onStop();
    }


    @Override
    public void onDestroy() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
