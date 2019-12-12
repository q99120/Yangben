package com.mihua.yangben.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.DeletNOBean;
import com.mihua.yangben.bean.HistoryRe;
import com.mihua.yangben.bean.HistoryReBean;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.datepicker.CustomDatePicker;
import com.mihua.yangben.datepicker.DateFormatUtils;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.view.AlertDialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 李浩 on 2019/5/13.
 */

public class EmptyCacheFm extends Fragment implements View.OnClickListener {
    private View views;
    private TextView start;
    private TextView endtimes;
    private TextView tv_confirm_dialog;
    private CustomDatePicker mDatePicker;
    private CustomDatePicker mDatePickers;
    private String startTime = "";
    private String endTime = "";
    private String dateStr;
    private AlertDialogUtils utils;
    private ArrayList<HistoryReBean> historyRes;
    private ArrayList<HistoryRe> list;
    private ArrayList<DeletNOBean> deletNo;
    private LoadingDialog mDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_empty_cache, container, false);
        initView();
        return views;
    }

    private void initView() {
        RelativeLayout starttime = views.findViewById(R.id.starttime);
        RelativeLayout endtime = views.findViewById(R.id.endtime);
        start = views.findViewById(R.id.start);
        endtimes = views.findViewById(R.id.endtimes);
        tv_confirm_dialog = views.findViewById(R.id.tv_confirm_dialog);
        starttime.setOnClickListener(this);
        endtime.setOnClickListener(this);
        tv_confirm_dialog.setOnClickListener(this);
        initStarttime();
    }

    private void initStarttime() {
        long beginTimestamp = DateFormatUtils.str2Long("2019-01-01", false);
        long endTimestamp = System.currentTimeMillis();
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                start.setText(DateFormatUtils.long2Str(timestamp, false));
                startTime = start.getText().toString().trim() + " 00:00:00";
                endtimes.setText("");
                endTime = "";

            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        mDatePicker.setTitles("请选择开始时间");
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.starttime:
                mDatePicker.show("yyyy-MM-dd");
                break;
            case R.id.endtime:
                initStarttimes();
                break;
            case R.id.tv_confirm_dialog:
                cache();
                break;
        }
    }

    private void cache() {
        if (utils == null) {
            utils = AlertDialogUtils.getInstance();
        }
//        utils.showDialog(getActivity(), "请确认数据已更新或导出");//,sampleLists
//        //按钮点击监听
//        utils.setOnButtonClickListener(new AlertClickListener());

    }

    private void initStarttimes() {
        if (TextUtils.isEmpty(start.getText().toString().trim())) {
            Toast.makeText(getActivity(), "请先选择开始时间", Toast.LENGTH_SHORT).show();
            return;

        } else {
            dateStr = start.getText().toString().trim();
        }
        long beginTimestamp = DateFormatUtils.str2Long(dateStr, false);
        long endTimestamp = System.currentTimeMillis();
        // 通过时间戳初始化日期，毫秒级别
        mDatePickers = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                endtimes.setText(DateFormatUtils.long2Str(timestamp, false));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dt = new Date();
                String str_time = sdf.format(dt);
                if (start.getText().toString().trim().equals(endtimes.getText().toString().trim())) {
                    if (start.getText().toString().trim().equals(str_time)) {
                        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date dtss = new Date();
                        String str_times = sdfs.format(dtss);
                        String[] split = str_times.split(" ");
                        endTime = endtimes.getText().toString().trim() + " " + split[1];
                    } else {
                        endTime = endtimes.getText().toString().trim() + " 23:59:59";
                    }
                } else {
                    endTime = endtimes.getText().toString().trim() + " 23:59:59";
                }


            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePickers.setCancelable(false);
        mDatePickers.setTitles("请选择结束时间");
        // 不显示时和分
        mDatePickers.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePickers.setScrollLoop(false);
        // 不允许滚动动画
        mDatePickers.setCanShowAnim(false);
        mDatePickers.show("yyyy-MM-dd");
    }

//    private class AlertClickListener implements AlertDialogUtils.OnButtonClickListener {
//        @Override
//        public void onPositiveButtonClick(AlertDialog dialog, int nums, String name, String index, String number, String oldNumer) {
//            //    String hrecord = SPUtils.getString(getActivity(), "hrecord", "");
//            dialog.dismiss();
//            if (mDialog == null) {
//                mDialog = new LoadingDialog(getActivity());
//            }
//            mDialog.setTvTip("正在删除中");
//            mDialog.show();
//            Logger.e("我的开始时间:" + startTime + ",我的结束时间:" + endTime);
//            mhandler.sendEmptyMessageDelayed(1, 1000);
//
//        }
//
//        @Override
//        public void onNegativeButtonClick(AlertDialog dialog) {
//            dialog.dismiss();
//        }
//    }

    public static long date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            long l = sdf.parse(date).getTime() / 1000;
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
    public void nextPage(MyEventMessage message) {

    }


}
