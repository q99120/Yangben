package com.mihua.yangben.ui.fragment;

import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.ui.activity.HomeActivity;
import com.mihua.yangben.ui.adapter.TestArrayAdapter;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.usb.UsbDataUtils;
import com.mihua.yangben.usb.UsbSerialUtils;
import com.mihua.yangben.usb.driver.UsbSerialPort;
import com.mihua.yangben.utils.SPUtils;
import com.mihua.yangben.view.AlertDialogUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lx on 2019/4/9.
 */

public class SampleProcessingFm extends Fragment implements View.OnClickListener, TextWatcher {
    @BindView(R.id.tests)
    TextView tests;
    @BindView(R.id.determine)
    Button determine;
    @BindView(R.id.head)
    LinearLayout head;
    private View views;
    private UsbManager mUsbManager;
    private MyHandler mHandler;
    private static final int FAILURE_CONNECT = 0;
    private boolean screenIsEnable;
    public static final int QUERY_SENSOR_STATUS = 4;
    private UsbSerialUtils mUsbSerialUtils;
    private static final int USB_CONNECTING = 8;
    private UsbSerialPort sPort;
    public static final int SUCCESS_CONNECT = 1;
    private static final int SKIP_LAUNCHER = 11;
    public static final int CONTROLLER_DATA_SUCCESS = 2;
    public static final int SENSOR_DATA_SUCCESS = 3;
    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";
    private final static String ACTION = "android.hardware.usb.action.USB_STATE";
    // private String TAG = "SampleProcessing";
    /**
     * 从样本处理首页跳转到样本处理详情页面
     */
    public static final int PAGE_COLLAPSING_TOOLSBAR = 4;
    private AlertDialogUtils utils;
    private int INCREASE = 1;//新增的确定事件
    private int START = 2;//开始的确定事件
    private int STOP = 3;//暂停的确定事件
    private int SELECT = 4;//选择项目的确定事件
    private LoadingDialog mDialog;
    private int type;
    private ImageView btnDecrease;
    private ImageView btnIncrease;
    private EditText etAmount;
    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 12; //商品库存
    private String value;
    private Spinner spinner;
    private String[] curs;
    private boolean networkConnected;
    HomeActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_sample_process, container, false);
        initView();
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
        if (mDialog != null) {
            mDialog.dismiss();
        }

        super.onDestroy();

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void handleEvent(MyEventMessage message) {
        // Log.d(TAG,);
    }


    private void initView() {
        activity = (HomeActivity) getActivity();
        // TextView viewById = (TextView) views.findViewById(R.id.);

        btnDecrease = views.findViewById(R.id.btnDecrease);
        btnIncrease = views.findViewById(R.id.btnIncrease);
        etAmount = views.findViewById(R.id.etAmount);
        spinner = views.findViewById(R.id.spread_project);

        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        initSP();
    }

    private void initSP() {
        curs = new String[]{"呋喃四项", "孔雀石绿", "瘦肉精类"};
        TestArrayAdapter adapter = new TestArrayAdapter(getActivity(), curs);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);
//        networkConnected = NetStateUtils.isNetworkConnected(getActivity());
//        if (networkConnected) {
//            // plan(curs[0]);
//        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //showPrice(position);
                TextView tv = (TextView) view;
                tv.setTextColor(getActivity().getResources().getColor(R.color.black));    //设置颜色

                tv.setTextSize(16.0f);//设置大小
                if (networkConnected) {
                    // plan(curs[position]);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.(this);
//        mUsbSerialUtils.onPause();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

      /*  if (mUsbReceiver != null) {
           getActivity().unregisterReceiver(mUsbReceiver);
        }*/
    }

    @OnClick({R.id.tests, R.id.determine, R.id.head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tests:
                break;
            case R.id.determine://确定按钮
//                EventBus.getDefault().post(new SerialMessage("1111"));
                nextPage();
                break;
            case R.id.head:
//                break;
        }
    }

    private void nextPage() {
        String channelNum = etAmount.getText().toString().trim();
        String sample_name = spinner.getSelectedItem().toString();
        if (getActivity() != null) {
            SPUtils.putInt(getActivity(), "ChannelNum", Integer.parseInt(channelNum));
            SPUtils.putString(getActivity(), "SampleName", sample_name);
        }
//        MyEventMessage myEventMessage = new MyEventMessage();
//        myEventMessage.setProcessingDetails(PAGE_COLLAPSING_TOOLSBAR);
//        myEventMessage.setChannelNo(channelNum);
//        Log.e(TAG, "nextPage: "+channelNum );
        MyEventMessage myEventMessage = new MyEventMessage();
        myEventMessage.setProcessingDetails(PAGE_COLLAPSING_TOOLSBAR);
        myEventMessage.setChannelNo(channelNum);
        EventBus.getDefault().post(myEventMessage);
//
//        activity.goDataProFm(4);
//        EventBus.getDefault().post(new SerialMessage(channelNum));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

        /*if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }*/
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }

        etAmount.clearFocus();

      /*  if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }*/
    }

    private static class MyHandler extends Handler {
        WeakReference<SampleProcessingFm> mReference;

        public MyHandler(SampleProcessingFm activity) {
            mReference = new WeakReference<SampleProcessingFm>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final SampleProcessingFm usbActivity = mReference.get();
            if (usbActivity != null) {

                int what = msg.what;
                byte[] data = (byte[]) msg.obj;
                switch (what) {
                    case FAILURE_CONNECT:  // 连接失败

//                    mIvBluetooth.setImageResource(R.mipmap.lanya_normal);
                        usbActivity.screenIsEnable = false;

                        // 连接失败停止询问
                        usbActivity.mHandler.removeMessages(QUERY_SENSOR_STATUS);
                        Logger.d("Usb连接失败");
//                    ToastUtil.showToast(mContext, "Usb连接失败");

                        // usb 断开连接，停止线程
                        usbActivity.mUsbSerialUtils.onPause();

                        break;
                    case USB_CONNECTING:
                        UsbSerialPort usbSerialPort = usbActivity.mUsbSerialUtils.getUsbSerialPort();
                        if (usbSerialPort != null) {
                            usbActivity.sPort = usbSerialPort;
                            UsbDevice device = usbSerialPort.getDriver().getDevice();
                            if (usbActivity.mUsbManager.hasPermission(device)) {
                                boolean success = usbActivity.mUsbSerialUtils.setUsbPortParamsAndStart(9600, usbSerialPort);
                                // 如果读写线程连接成功则连接成功
                                if (success) {
                                    usbActivity.mHandler.sendEmptyMessage(SUCCESS_CONNECT);
                                } else {
                                    usbActivity.mHandler.sendEmptyMessage(FAILURE_CONNECT);
//                                ToastUtil.showToast(mContext, "Usb读写连接失败");
                                }
                            } else {
                                Logger.d("现在去申请usb权限");
                                PendingIntent mPermissionIntent = PendingIntent.getBroadcast(usbActivity.getContext(), 0, new Intent(ACTION_USB_PERMISSION), 0);
                                usbActivity.mUsbManager.requestPermission(device, mPermissionIntent);
                            }
                        } else {
                            Logger.d("未找到 usb 串口");
                            // 没有获取到端口说明连接失败
                            usbActivity.mHandler.sendEmptyMessage(FAILURE_CONNECT);
                        }
                        break;
                    case SUCCESS_CONNECT:  // Usb连接上了

//

                        Logger.d("usb连接成功");
//                  ToastUtil.showToast(mContext, "usb连接成功");

                        // usbActivity.mHandler.sendEmptyMessageDelayed(SKIP_LAUNCHER, 1000);


                        // 查询 按钮的状态信息
                        // usbActivity.mUsbSerialUtils.sendMessage(new byte[]{0x6B, (byte) 0xFE});
                        // 过两秒查询 传感器的状态信息
                        // usbActivity.mHandler.sendEmptyMessageDelayed(QUERY_SENSOR_STATUS, 1000);

//                        usbActivity.mUsbStatus.setSelected(true);
//                        usbActivity.mUsbStatus.setText("usb连接成功");


                        break;
                    case CONTROLLER_DATA_SUCCESS:
                        // 总开关为开的时候才去更新界面和询问

                        Logger.d("通过handler收到消息" + UsbDataUtils.printData(data));


                        // 跳转到主界面
//                        usbActivity.skipToLauncher();

                        break;
                    case QUERY_SENSOR_STATUS:
                        usbActivity.mHandler.removeMessages(QUERY_SENSOR_STATUS);
                        // 查询传感器状态
//                        Logger.d("查询传感器的状态");
                        usbActivity.mUsbSerialUtils.sendMessage(new byte[]{0x6C, (byte) 0xFE});

                        usbActivity.mHandler.sendEmptyMessageDelayed(QUERY_SENSOR_STATUS, 3000);
                        break;


                }
            }
        }

    }
}
