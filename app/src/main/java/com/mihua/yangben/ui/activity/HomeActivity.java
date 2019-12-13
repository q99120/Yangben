package com.mihua.yangben.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.ui.fragment.DataProcessingFm;
import com.mihua.yangben.ui.fragment.HistoryRecordFm;
import com.mihua.yangben.ui.fragment.HomePageFm;
import com.mihua.yangben.ui.fragment.MineFm;
import com.mihua.yangben.ui.fragment.SolventSettingFm;
import com.mihua.yangben.usb.SerialUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.today_tab)
    RelativeLayout todayTab;
    @BindView(R.id.record_tab)
    RelativeLayout recordTab;
    @BindView(R.id.contact_tab)
    RelativeLayout contactTab;
    @BindView(R.id.settings_tab)
    RelativeLayout settingsTab;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.contentPanel)
    FrameLayout contentPanel;
    /**
     * 第一个fragment
     */
    public static final int PAGE_COMMON = 0;
    /**
     * 第二个fragment
     */
    public static final int PAGE_TRANSLUCENT = 1;
    /**
     * 第三个fragment
     */
    public static final int PAGE_COORDINATOR = 2;
    /**
     * 第四个fragment
     */
    public static final int PAGE_COLLAPSING_TOOLBAR = 3;
    /**
     * 从样本处理首页跳转到样本处理详情页面
     */
    public static final int PAGE_COLLAPSING_TOOLSBAR = 4;
    /**
     * 从首页跳转到视频列表页面
     */
    public static final int PAGE_COLLAPSING_FIVES = 5;
    /**
     * 管理fragment
     */
    private HashMap<Integer, Fragment> fragments;
    //当前activity的fragment控件
    public int fragmentContentId = R.id.contentPanel;
    /**
     * 设置默认的fragment
     */
    private int currentTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //初始化fragment的集合
        initFrag();
//        CrashReport.testJavaCrash();

        // 设置默认的Fragment
        defaultFragment();

    }

    @Override
    protected void onResume() {
        Log.e("onResume1", "onResume: " + SerialUtils.serialPortStatus);
        if (!SerialUtils.serialPortStatus) {
            SerialUtils.getInstance().OpenSerial();
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        Log.e("onResume2", "onResume: " + SerialUtils.serialPortStatus);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.e("onDestroy1", "onResume: " + SerialUtils.serialPortStatus);
        if (SerialUtils.serialPortStatus) {
            SerialUtils.getInstance().closeSerialPort();
        }
        Log.e("onDestroy2", "onResume: " + SerialUtils.serialPortStatus);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    public void nextVideo() {
        startActivity(new Intent(HomeActivity.this, VideoListActivity.class));
    }

    public void SolveSet() {
        changeTab(6);
    }


    private void defaultFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentContentId, fragments.get(PAGE_COMMON));
        currentTab = PAGE_COMMON;
        ft.commit();


    }

    private void initFrag() {
        fragments = new HashMap<>();
        fragments.put(PAGE_COMMON, new HomePageFm());
        fragments.put(PAGE_TRANSLUCENT, new DataProcessingFm());
        fragments.put(PAGE_COORDINATOR, new HistoryRecordFm());
        fragments.put(PAGE_COLLAPSING_TOOLBAR, new MineFm());
        fragments.put(6, new SolventSettingFm());

    }

    @OnClick({R.id.today_tab, R.id.record_tab, R.id.contact_tab, R.id.settings_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.today_tab:
                changeTab(PAGE_COMMON);
                break;
            case R.id.record_tab:
                changeTab(PAGE_TRANSLUCENT);
                break;
            case R.id.contact_tab:
                changeTab(PAGE_COORDINATOR);
                //  finish();
                break;
            case R.id.settings_tab:
                changeTab(PAGE_COLLAPSING_TOOLBAR);
                break;
        }
    }

    /**
     * 点击切换下部按钮
     *
     * @param page
     */
    private void changeTab(int page) {
        //默认的currentTab == 当前的页码，不做任何处理
        if (currentTab == page) {
            return;
        }

        //获取fragment的页码
        Fragment fragment = fragments.get(page);
        //fragment事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //如果该Fragment对象被添加到了它的Activity中，那么它返回true，否则返回false。
        //当前activity中添加的不是这个fragment
        if (!fragment.isAdded()) {
            //所以将他加进去
            ft.add(fragmentContentId, fragment);
        }
        //隐藏当前currentTab的
        ft.hide(fragments.get(currentTab));
        //显示现在page的
        ft.show(fragments.get(page));

        //当前显示的赋值给currentTab
        currentTab = page;

        //activity被销毁？  ！否
        if (!this.isFinishing()) {
            //允许状态丢失
            ft.commitAllowingStateLoss();
        }
    }

    /**
     * 跳转到样本处理详情页
     */
    public void goDataProFm(int frag) {
        if (frag != 0) {
            //从样本处理首页跳转到二级页面
            if (frag == PAGE_COLLAPSING_TOOLSBAR) {
                changeTab(PAGE_COLLAPSING_TOOLSBAR);
            } else if (frag == PAGE_COLLAPSING_FIVES) {
                //首页点击视频资料跳转到二级页面
                nextVideo();
            }
        }
    }
  /*  @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }*/

 /*   private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }*/

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
   /* private void hideKeyboard(IBind token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }*/

    /**
     * 采用eventbus传值的方式跳转fragment
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void nextPage(MyEventMessage message) {
        if (message.getProcessingDetails() != 0) {
            //从样本处理首页跳转到二级页面
            if (message.getProcessingDetails() == PAGE_COLLAPSING_TOOLSBAR) {
                changeTab(PAGE_COLLAPSING_TOOLSBAR);
            } else if (message.getProcessingDetails() == PAGE_COLLAPSING_FIVES) {
                //首页点击视频资料跳转到二级页面
                nextVideo();
            }
        }
    }


}
