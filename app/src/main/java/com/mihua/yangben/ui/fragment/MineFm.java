package com.mihua.yangben.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.view.AlertDialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * Created by lx on 2019/4/9.
 */

public class MineFm extends Fragment implements View.OnClickListener {
    private View views;
    private RelativeLayout userInfo;
    private RelativeLayout device;
    private RelativeLayout system;
    private RelativeLayout about;
    private RelativeLayout signOut;
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

    public static final int PAGE_COLLAPSING_FIVESS = 6;
    /**
     * 管理fragment
     */
    private HashMap<Integer, Fragment> fragments = new HashMap<>();
    //当前activity的fragment控件
    public int fragmentContentId = R.id.contentPanel;
    private FrameLayout contentPanel;
    /**
     * 设置默认的fragment
     */
    private int currentTab;
    private AlertDialogUtils utils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        initFrag();
        // 设置默认的Fragment
        defaultFragment();
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
        if (message.getModify() != 0) {

            com.orhanobut.logger.Logger.e("我要的数据" + message.getModify());
            if (message.getModify() == PAGE_COLLAPSING_TOOLSBAR) {
                changeTab(PAGE_COLLAPSING_TOOLSBAR);
            } else if (message.getModify() == PAGE_COLLAPSING_FIVES) {
                changeTab(PAGE_COLLAPSING_FIVES);
            } else if (message.getModify() == PAGE_TRANSLUCENT) {
                changeTab(PAGE_COMMON);
            } else if (message.getModify() == PAGE_COLLAPSING_FIVESS) {
                changeTab(PAGE_COLLAPSING_FIVESS);
            }
        }

    }

    private void defaultFragment() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(fragmentContentId, fragments.get(PAGE_COMMON));
        currentTab = PAGE_COMMON;
        ft.commit();
    }

    private void initFrag() {
        fragments.put(PAGE_COMMON, new UserInfoFm());
        fragments.put(PAGE_TRANSLUCENT, new DeviceFm());
        fragments.put(PAGE_COORDINATOR, new SystemsFm());
        fragments.put(PAGE_COLLAPSING_TOOLBAR, new AboutFm());
        fragments.put(PAGE_COLLAPSING_TOOLSBAR, new ModifyAvatarFm());
        fragments.put(PAGE_COLLAPSING_FIVES, new ModifyInformationFm());
        fragments.put(PAGE_COLLAPSING_FIVESS, new EmptyCacheFm());
    }

    private void initView() {
        userInfo = views.findViewById(R.id.userInfo);
        device = views.findViewById(R.id.device);
        system = views.findViewById(R.id.system);
        about = views.findViewById(R.id.about);
        signOut = views.findViewById(R.id.signOut);
        contentPanel = views.findViewById(R.id.contentPanel);

        userInfo.setOnClickListener(this);
        device.setOnClickListener(this);
        system.setOnClickListener(this);
        about.setOnClickListener(this);
        signOut.setOnClickListener(this);

   /*     DBManagerHisRe instance = DBManagerHisRe.getInstance(getActivity());
        DBManager instance1 = DBManager.getInstance(getActivity());
        List<Zoo> zoos = instance1.queryUserList();
        List<HistoryReBean> historyReBeans = instance.queryUserList();
        Gson   gson =  new Gson();
        for (int i = 0; i <historyReBeans.size() ; i++) {
           // com.orhanobut.logger.Logger.e("历史记录"+gson.toJson(historyReBeans.get(i).getBean())+",id="+historyReBeans.get(i).getId());
        }
        for (int i = 0; i <zoos.size() ; i++) {
           // com.orhanobut.logger.Logger.e("提交记录"+gson.toJson(zoos.get(i).getBean())+",id="+zoos.get(i).getId());
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userInfo:
                select(0);
                changeTab(PAGE_COMMON);
                break;
            case R.id.device:
                select(1);
                changeTab(PAGE_TRANSLUCENT);
                break;
            case R.id.system:
                select(2);
                changeTab(PAGE_COORDINATOR);
                break;
            case R.id.about:
                select(3);
                changeTab(PAGE_COLLAPSING_TOOLBAR);
                break;
            case R.id.signOut:
                //  select(4);
                getActivity().finish();
                //  signout();
                break;
        }

    }

//    private void signout() {
//        if (utils == null) {
//            utils = AlertDialogUtils.getInstance();
//        }
//        utils.showDialog(getActivity(), "确定要退出吗？");//,sampleLists
//        //按钮点击监听
//        utils.setOnButtonClickListener(new AlertClickListener());
//    }

    //点击改变背景颜色
    private void select(int select) {
        if (select == 0) {
            userInfo.setBackgroundColor(getResources().getColor(R.color.com_bgs));//getResources().getColor(R.color.cornflowerblue)
            device.setBackgroundColor(getResources().getColor(R.color.white));
            system.setBackgroundColor(getResources().getColor(R.color.white));
            about.setBackgroundColor(getResources().getColor(R.color.white));
            signOut.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (select == 1) {
            userInfo.setBackgroundColor(getResources().getColor(R.color.white));//getResources().getColor(R.color.cornflowerblue)
            device.setBackgroundColor(getResources().getColor(R.color.com_bgs));
            system.setBackgroundColor(getResources().getColor(R.color.white));
            about.setBackgroundColor(getResources().getColor(R.color.white));
            signOut.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (select == 2) {
            userInfo.setBackgroundColor(getResources().getColor(R.color.white));//getResources().getColor(R.color.cornflowerblue)
            device.setBackgroundColor(getResources().getColor(R.color.white));
            system.setBackgroundColor(getResources().getColor(R.color.com_bgs));
            about.setBackgroundColor(getResources().getColor(R.color.white));
            signOut.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (select == 3) {
            userInfo.setBackgroundColor(getResources().getColor(R.color.white));//getResources().getColor(R.color.cornflowerblue)
            device.setBackgroundColor(getResources().getColor(R.color.white));
            system.setBackgroundColor(getResources().getColor(R.color.white));
            about.setBackgroundColor(getResources().getColor(R.color.com_bgs));
            signOut.setBackgroundColor(getResources().getColor(R.color.white));
        } else if (select == 4) {
            userInfo.setBackgroundColor(getResources().getColor(R.color.white));//getResources().getColor(R.color.cornflowerblue)
            device.setBackgroundColor(getResources().getColor(R.color.white));
            system.setBackgroundColor(getResources().getColor(R.color.white));
            about.setBackgroundColor(getResources().getColor(R.color.white));
            signOut.setBackgroundColor(getResources().getColor(R.color.com_bgs));
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
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
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
        if (!getActivity().isFinishing()) {
            //允许状态丢失
            ft.commitAllowingStateLoss();
        }
    }

//    private class AlertClickListener implements AlertDialogUtils.OnButtonClickListener {
//        @Override
//        public void onPositiveButtonClick(AlertDialog dialog, int nums, String name, String index, String number, String oldNumer) {
//            dialog.dismiss();
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//            getActivity().finish();
//        }
//
//        @Override
//        public void onNegativeButtonClick(AlertDialog dialog) {
//            dialog.dismiss();
//        }
//    }
}
