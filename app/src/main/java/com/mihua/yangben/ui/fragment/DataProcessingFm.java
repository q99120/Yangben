package com.mihua.yangben.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;
import com.mihua.yangben.app.Contacts;
import com.mihua.yangben.bean.SampleList;
import com.mihua.yangben.bean.SerialMessage;
import com.mihua.yangben.datepicker.DateFormatUtils;
import com.mihua.yangben.db.database.RecordDatabase;
import com.mihua.yangben.db.entity.ConsumablesFuNan;
import com.mihua.yangben.db.entity.ConsumablesKQ;
import com.mihua.yangben.db.entity.HisRecord;
import com.mihua.yangben.interfaces.UpData;
import com.mihua.yangben.ui.adapter.SampleListAdapter;
import com.mihua.yangben.ui.adapter.TestArrayAdapter;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.usb.SerialUtils;
import com.mihua.yangben.utils.SPUtils;
import com.mihua.yangben.utils.SimpleUtils;
import com.mihua.yangben.view.AlertDialogUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lx on 2019/4/15.
 */

public class DataProcessingFm extends Fragment implements UpData {
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.ones)
    RelativeLayout ones;
    @BindView(R.id.twos)
    RelativeLayout twos;
    @BindView(R.id.threes)
    RelativeLayout threes;
    @BindView(R.id.fours)
    RelativeLayout fours;
    @BindView(R.id.fivess)
    RelativeLayout fivess;
    @BindView(R.id.sixs)
    RelativeLayout sixs;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.sevens)
    RelativeLayout sevens;
    @BindView(R.id.eights)
    RelativeLayout eights;
    @BindView(R.id.nines)
    RelativeLayout nines;
    @BindView(R.id.tens)
    RelativeLayout tens;
    @BindView(R.id.elevens)
    RelativeLayout elevens;
    @BindView(R.id.twelves)
    RelativeLayout twelves;
    @BindView(R.id.heads)
    LinearLayout heads;
    @BindView(R.id.headss)
    LinearLayout headss;
    @BindView(R.id.sampleList)
    ListView sampleList;
    @BindView(R.id.start)
    RelativeLayout start;
    @BindView(R.id.stop)
    RelativeLayout stop;
    @BindView(R.id.btu)
    LinearLayout btu;
    @BindView(R.id.prompt)
    TextView prompt;
    @BindView(R.id.notStarted)
    RelativeLayout notStarted;
    @BindView(R.id.warming_operationpg)
    ImageView warmingOperationpg;
    @BindView(R.id.activation_operationpg)
    ImageView activationOperationpg;
    @BindView(R.id.pipetting_operationpg)
    ImageView pipettingOperationpg;
    @BindView(R.id.purification_operationpg)
    ImageView purificationOperationpg;
    @BindView(R.id.elution_operationpg)
    ImageView elutionOperationpg;
    @BindView(R.id.hot_airpg)
    ImageView hotAirpg;
    @BindView(R.id.warminguptv)
    TextView warminguptv;
    @BindView(R.id.extracttv)
    TextView extracttv;
    @BindView(R.id.stirtv)
    TextView stirtv;
    @BindView(R.id.incubationtv)
    TextView incubationtv;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.activationtv)
    TextView activationtv;
    @BindView(R.id.activationpztv)
    TextView activationpztv;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.pipettingtv)
    TextView pipettingtv;
    @BindView(R.id.pipettingpztv)
    TextView pipettingpztv;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.purificationtv)
    TextView purificationtv;
    @BindView(R.id.purificationpztv)
    TextView purificationpztv;
    @BindView(R.id.four)
    LinearLayout four;
    @BindView(R.id.elutiontv)
    TextView elutiontv;
    @BindView(R.id.elutionpztv)
    TextView elutionpztv;
    @BindView(R.id.hotairtv)
    TextView hotairtv;
    @BindView(R.id.started)
    RelativeLayout started;
    @BindView(R.id.btnDecrease)
    ImageView btnDecrease;
    @BindView(R.id.btnIncrease)
    ImageView btnIncrease;
    @BindView(R.id.determine)
    Button determine;
    @BindView(R.id.include_samplepro)
    View include_samplepro;
    @BindView(R.id.include_datapro)
    View include_datapro;
    @BindView(R.id.spread_project)
    Spinner spreadProject;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.tv_yangben_info)
    TextView tvYangbenInfo;
    private View views;
    //样本列表的初始化数据
    private ArrayList<SampleList> sampleLists;
    private AlertDialogUtils utils;
    boolean start_flag;
    public static String TAG = "DataProcessingFm";
    private LoadingDialog mDialog;
    private int index = 0;
    private SampleListAdapter sampleListAdapter;
    //通道编号
    private int channelNum;
    //选择的样本名称
    private String sampleName;
    //呋喃四项json
    private String four_funan_json;
    //孔雀石绿
    private String malachite_green_json;
    //瘦肉精类
    private String sr_json;
    //对话框的flag
    private int dialog_flag;
    int receive_flag;
    List<HisRecord> sampleListBeans;

//    private ArrayList<String> number = new ArrayList<>();


    private ArrayList<View> mlist = new ArrayList<>();
    private AlertDialog mdialog;
    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Contacts.SEND:
                    saveRecord();
                    break;
                case 1:
                    byte[] result = new byte[]{0x6c, 0x1a, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x01, 0x01, 0x05, 0x01, 0x05};
                    SerialUtils.getInstance().WriteByte(result);
                    break;
                case 2:
                    mdialog.dismiss();
                    mDialogs.dismiss();
                    notStarted.setVisibility(View.GONE);
                    started.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                    start_flag = true;
                    break;
                case 3:
                    mdialog.dismiss();
                    mDialogs.dismiss();
                    Toast.makeText(getActivity(), "设置超时", Toast.LENGTH_SHORT).show();
                    mhandler.sendEmptyMessageAtTime(15, 500);
//                    start_flag = false;
                    break;
                case 10:
                    mdialog.dismiss();
                    notStarted.setVisibility(View.GONE);
                    started.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                    mhandler.sendEmptyMessageDelayed(1, 200);
                    break;
                case 11:
                    split = (String[]) msg.obj;
                    int i = Integer.parseInt(split[2].replace("0x", ""), 16);
                    warminguptv.setText("升温操作           " + i + "℃");
                    extracttv.setText("提取操作            " + sampleLists.size());
                    activationtv.setText("活化操作            " + sampleLists.size());
                    purificationtv.setText("净化操作            " + sampleLists.size());
                    elutiontv.setText("洗脱操作            " + sampleLists.size());
                    pipettingtv.setText("移液操作            " + sampleLists.size());
                    if (split[3].equals("0x0")) {
                        warminguptv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        extracttv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        stirtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        incubationtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else if (split[3].equals("0x1")) {
                        warminguptv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        extracttv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        stirtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        incubationtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    } else {
                        warminguptv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        extracttv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        stirtv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        incubationtv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                    }
                    if (Integer.parseInt(split[7].replace("0x", ""), 16) == 0) {
                        activationpztv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        activationtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else if (split[7].equals("0x1")) {
                        activationpztv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        activationtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    } else {
                        activationpztv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        activationtv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                    }
                    if (Integer.parseInt(split[9].replace("0x", ""), 16) == 0) {
                        pipettingtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        pipettingpztv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else if (split[9].equals("0x1")) {
                        pipettingpztv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        pipettingtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    } else {
                        pipettingpztv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        pipettingtv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                    }
                    if (Integer.parseInt(split[11].replace("0x", ""), 16) == 0) {
                        purificationtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        purificationpztv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else if (split[11].equals("0x1")) {
                        purificationpztv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        purificationtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    } else {
                        purificationpztv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        purificationtv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                    }
                    if (Integer.parseInt(split[13].replace("0x", ""), 16) == 0) {
                        elutiontv.setTextColor(getActivity().getResources().getColor(R.color.green));
                        elutionpztv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else if (split[13].equals("0x1")) {
                        elutionpztv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                        elutiontv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    } else {
                        elutionpztv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                        elutiontv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                    }
                    if (split[15].equals("0x0")) {
                        hotairtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else if (split[15].equals("0x1")) {
                        hotairtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    } else {
                        hotairtv.setTextColor(getActivity().getResources().getColor(R.color.blue));
                    }
                    if (split[4].equals("0x0")) {
                        extracttv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else {
                        extracttv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    }
                    stirtv.setText("搅拌操作          剩余" + Integer.parseInt(split[5].replace("0x", ""), 16) + "s");
                    if (Integer.parseInt(split[5].replace("0x", ""), 16) == 0) {
                        stirtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else {
                        stirtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    }
                    incubationtv.setText("孵育时间          剩余" + Integer.parseInt(split[6].replace("0x", ""), 16) + "s");
                    if (Integer.parseInt(split[6].replace("0x", ""), 16) == 0) {
                        incubationtv.setTextColor(getActivity().getResources().getColor(R.color.green));
                    } else {
                        incubationtv.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    }
                    activationpztv.setText("加压操作          剩余" + Integer.parseInt(split[8].replace("0x", ""), 16) + "s");
                    pipettingpztv.setText("加压操作          剩余" + Integer.parseInt(split[10].replace("0x", ""), 16) + "s");
                    purificationpztv.setText("加压操作          剩余" + Integer.parseInt(split[12].replace("0x", ""), 16) + "s");
                    elutionpztv.setText("加压操作          剩余" + Integer.parseInt(split[14].replace("0x", ""), 16) + "s");
                    hotairtv.setText("热风/孵育操作   剩余" + Integer.parseInt(split[16].replace("0x", ""), 16) + "s");
                    break;
                case 12:
                    if (mdialog != null) {
                        mdialog.dismiss();
                        Toast.makeText(getActivity(), "停止成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 13:
                    if (mdialog != null) {
                        mdialog.dismiss();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "重启成功", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    break;
                case 15:
//                    if (start_flag) {
                    //查询温度度数
//                        String tmp_str = msg.obj.toString();
                    byte[] tmp_b = new byte[]{0x6c, 0x00};
                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    }
                    break;
                case 16:
                    //查询升温操作
                    String tmp_str = msg.obj.toString();
                    byte[] shengwen_b = new byte[]{0x6c, 0x01};
                    SerialUtils.getInstance().WriteByte(shengwen_b);
                    break;
                case 17:
                    //查询提取操作
                    byte[] tiqu_b = new byte[]{0x6c, 0x02};
                    SerialUtils.getInstance().WriteByte(tiqu_b);
                    break;
//                case 18:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x03};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 19:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 20:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 21:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 22:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 23:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 24:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
//                case 26:
//                    //查询温度度数
//                    byte[] tmp_b = new byte[]{0x6c, 0x01};
//                    SerialUtils.getInstance().WriteByte(tmp_b);
//                    break;
            }
        }
    };

    private String[] split;
    private LoadingDialog mDialogs;
    private int amount = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_datapro, container, false);
        ButterKnife.bind(this, views);
        EventBus.getDefault().register(this);
        initSpinner();
        initView();
        //initListener();
        return views;
    }

    private void initSpinner() {
        String[] curs = new String[]{"呋喃四项", "孔雀石绿", "瘦肉精类"};
        TestArrayAdapter adapter = new TestArrayAdapter(getActivity(), curs);
        spreadProject.setAdapter(adapter);
        spreadProject.setSelection(0);
        spreadProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //showPrice(position);
                TextView tv = (TextView) view;
                tv.setTextColor(getActivity().getResources().getColor(R.color.black));    //设置颜色
                tv.setTextSize(16.0f);//设置大小

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    //EventBus的注册，注销和事件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mDialog != null) {
            mDialog.dismiss();
        }
        super.onDestroy();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void SerialEvent(SerialMessage msg) {
        String receive_str = msg.getSerialMsg();
        prompt.setText(receive_str);
        if (receive_str.substring(2, 4).equals("6d")) {
                if (mdialog != null) {
                    if (receive_str.substring(6, 8).equals("01")) {
                        Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                    }
            }
        } else if (receive_str.substring(2, 4).equals("6f")) {
//            hisData();
            mhandler.sendEmptyMessageDelayed(2, 3000);
        } else if (split[1].equals("0x6c") & split.length == 17) {
            Logger.e("查询成功");
            Message msg1 = Message.obtain();
            msg1.obj = split;
            msg1.what = 11;
            // Logger.e("查询温度"+Integer.parseInt("1a",16));
            mhandler.sendMessage(msg1);
        } else if (split[1].equals("0x1a")) {
            mhandler.sendEmptyMessage(11);
        } else if (split[1].equals("0x1b")) {
            mhandler.sendEmptyMessage(12);
        } else if (split[1].equals("0x6c")) {
            if (start_flag) {
                if (receive_flag < 27) {
                    receive_flag += 1;
                } else {
                    receive_flag = 15;
                }
                Message message = Message.obtain();
                message.obj = split;
                message.what = receive_flag;
                if (mhandler != null) {
                    mhandler.sendMessageDelayed(message, 500);
                }
            }
        } else {
            if (mdialog != null)
                mdialog.dismiss();
            if (mDialogs != null)
                mDialogs.dismiss();
            Toast.makeText(getActivity(), "设置失败", Toast.LENGTH_SHORT).show();
        }
        Log.e("SerialMessage", "SerialEvent: " + msg.getSerialMsg());
    }

    private void initData() {
        Log.e("初始化", "初始化数据1111");
        tvYangbenInfo.setText("样本信息" + "(" + sampleName + ")");
        mlist.clear();
        mlist.add(ones);
        mlist.add(twos);
        mlist.add(threes);
        mlist.add(fours);

        mlist.add(fivess);
        mlist.add(sixs);
        mlist.add(sevens);
        mlist.add(eights);

        mlist.add(nines);
        mlist.add(tens);
        mlist.add(elevens);
        mlist.add(twelves);
        for (int i = 0; i < mlist.size(); i++) {
            if (i < channelNum) {
                mlist.get(i).setVisibility(View.VISIBLE);
                mlist.get(i).setEnabled(true);
                mlist.get(i).setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                mlist.get(i).setVisibility(View.INVISIBLE);
            }
        }
        sampleLists = new ArrayList<>();
        sampleListAdapter = new SampleListAdapter(getActivity(), sampleLists, this);
        sampleList.setAdapter(sampleListAdapter);

    }

    private void initView() {
        if (utils == null) {
            utils = AlertDialogUtils.getInstance();
            utils.setOnButtonClickListener(new AlertClickListener());
        }
    }


    // 样本信息列表修改事件回调
    @Override
    public void updata(View update_view) {
        dialog_flag = 2;
        int updata_tag = (int) update_view.getTag();
        int id = sampleLists.get(updata_tag).getId();
        String old_num = sampleLists.get(updata_tag).getSampleNumber();
        Log.e("这个里", "updata: " + id);

        utils.showAddDialogs(getActivity(), 2, id, "", old_num);
//        number.remove(number.get(updata_tag));
        sampleLists.remove(sampleLists.get(updata_tag));
        sampleListAdapter.notifyDataSetChanged();
    }


    //样本信息列表删除事件回调
    @Override
    public void delete(View delete_view) {
        dialog_flag = 3;
        int delete_tag = (int) delete_view.getTag();
        int id = sampleLists.get(delete_tag).getId();
        Log.e("通道编号", "delete: " + id);
        mlist.get(id - 1).setEnabled(true);
        mlist.get(id - 1).setBackgroundColor(getResources().getColor(R.color.white));
        sampleLists.remove(sampleLists.get(delete_tag));
        sampleListAdapter.notifyDataSetChanged();
//        alertDialog(delete_tag, sampleLists.get(delete_tag).sampleName, sampleLists.get(delete_tag).number);
    }


    @OnClick({R.id.login, R.id.ones, R.id.twos, R.id.threes, R.id.fours, R.id.fivess,
            R.id.sixs, R.id.sevens, R.id.eights, R.id.nines, R.id.tens, R.id.elevens,
            R.id.twelves, R.id.start, R.id.stop, R.id.btnDecrease, R.id.btnIncrease, R.id.determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.determine:
                channelNum = Integer.parseInt(etAmount.getText().toString().trim());
                sampleName = spreadProject.getSelectedItem().toString();
                if (getActivity() != null) {
                    four_funan_json = SPUtils.getString(getActivity(), "map_funan_json", "");
                    malachite_green_json = SPUtils.getString(getActivity(), "map_kq_json", "");
                    sr_json = SPUtils.getString(getActivity(), "map_sr_json", "");
                }
                if (sampleName.equals("呋喃四项")) {
                    if (four_funan_json.equals("")) {
                        Toast.makeText(getActivity(), "还未设置呋喃四项容量值，请前往设置", Toast.LENGTH_SHORT).show();
                    } else {
                        include_samplepro.setVisibility(View.INVISIBLE);
                        include_datapro.setVisibility(View.VISIBLE);
                    }
                } else if (sampleName.equals("孔雀石绿")) {
                    if (malachite_green_json.equals("")) {
                        Toast.makeText(getActivity(), "还未设置孔雀石绿容量值，请前往设置", Toast.LENGTH_SHORT).show();
                    } else {
                        include_samplepro.setVisibility(View.INVISIBLE);
                        include_datapro.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (sr_json.equals("")) {
                        Toast.makeText(getActivity(), "还未设置瘦肉精类容量值，请前往设置", Toast.LENGTH_SHORT).show();
                    } else {
                        include_samplepro.setVisibility(View.INVISIBLE);
                        include_datapro.setVisibility(View.VISIBLE);
                    }
                }
                initData();
                break;
            case R.id.btnDecrease:
                if (amount > 1) {
                    amount--;
                    etAmount.setText(amount + "");
                }
                break;
            case R.id.btnIncrease:
                if (amount < 12) {
                    amount++;
                    etAmount.setText(amount + "");
                }
                break;
            case R.id.ones:
                addDialog(0);
                break;
            case R.id.tests:

                break;
            case R.id.start:
//                byte [] ss = new byte[]{1,2};
//                SerialUtils.getInstance().WriteByte(ss);
                Log.e(TAG, "查询名字传值: " + sampleName);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HisRecord hisRecord = new HisRecord();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
                        Date dt = new Date();
                        String str_time = sdf.format(dt);
                        String recordNo = str_time.replaceAll(" ", "");
                        final String recordNo1 = "NO" + recordNo.substring(2).replaceAll("-", "").replaceAll(":", "");
                        hisRecord.setmRecordNum(recordNo1);
                        final String recordTime = str_time.substring(0, str_time.length() - 4);
                        hisRecord.setmProTime(recordTime);
                        hisRecord.setmRecordStatus(0);
                        hisRecord.setmProDay(DateFormatUtils.str2Long(SimpleUtils.getCurretTimes(), false));
                        hisRecord.setmProNode("无");
                        if (sampleName.equals("呋喃四项")) {
                            hisRecord.setmProjectType(0);
                        } else {
                            hisRecord.setmProjectType(1);
                        }
                        sampleListBeans = new ArrayList<>();
                        for (int i = 0; i < sampleLists.size(); i++) {
                            HisRecord hisRecord1 = new HisRecord();
                            hisRecord1.setmSampleNum(sampleLists.get(i).getSampleNumber());
                            hisRecord1.setmSampleInfo(sampleLists.get(i).getInformation());
                            hisRecord1.setmSampleName(sampleLists.get(i).getSampleName());
                            sampleListBeans.add(hisRecord1);
                        }
                        Log.e(TAG, "查询添加了多少个数组: " + sampleListBeans.size());

                        hisRecord.setList(sampleListBeans);

                        RecordDatabase.getinstance(getActivity()).hisRecordDao().insertHisRc(hisRecord);
                        if (sampleName.equals("呋喃四项")) {
                            initFourFuNanCursor();
                        } else if (sampleName.equals("孔雀石绿")) {
                            //开一个方法来存孔雀石
                            initKqCursor();
                        }
                    }
                }).start();

                if (sampleLists.size() == 0) {
                    Toast.makeText(getActivity(), "处理数据条数不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dialog_flag = 4;
                    dialog_start();
                }
                break;
            case R.id.threes:
                addDialog(2);
                break;
            case R.id.twos:
                addDialog(1);
                break;
            case R.id.fours:
                addDialog(3);
                break;
            case R.id.fivess:
                addDialog(4);
                break;
            case R.id.sixs:
                addDialog(5);
                break;
            case R.id.sevens:
                addDialog(6);
                break;
            case R.id.eights:
                addDialog(7);
                break;
            case R.id.nines:
                addDialog(8);
                break;
            case R.id.tens:
                addDialog(9);
                break;
            case R.id.elevens:
                addDialog(10);
                break;
            case R.id.twelves:
                addDialog(11);
                break;
            case R.id.stop:
                dialog_flag = 5;

//                alertDialogss();
                break;
            case R.id.login:
                reset();
                break;
        }
    }


    private void reset() {
        for (int i = 0; i < mlist.size(); i++) {
            if (i < channelNum) {
                mlist.get(i).setVisibility(View.VISIBLE);
                mlist.get(i).setEnabled(true);
                mlist.get(i).setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                mlist.get(i).setVisibility(View.INVISIBLE);

            }
        }
        sampleLists.clear();
        sampleListAdapter.notifyDataSetChanged();
        include_samplepro.setVisibility(View.VISIBLE);
        include_datapro.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), "重置成功", Toast.LENGTH_SHORT).show();

    }


    private void dialog_start() {
        utils.showStartDialog(getActivity());

        //按钮点击监听
    }


    //新增对话框事件,输入通道
    private void addDialog(int channel) {
        dialog_flag = 1;
        utils.showAddDialogs(getActivity(), dialog_flag, channel, "", "");
        //按钮点击监听
    }

//
//    private void alertDialog(int index, String sampleName, String numer) {
//        utils.showConfirmDialog(getActivity(), "" + index, sampleName, numer);
//        //按钮点击监听
//    }


    private void saveRecord() {
        //Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
        if (mdialog != null)
            mdialog.dismiss();
        mDialogs = new LoadingDialog(getActivity());

        mDialogs.setTvTip("正在设置中");
        mDialogs.show();
        mhandler.sendEmptyMessageDelayed(3, 6000);

        if (sampleName.equals("呋喃四项")) {
            SerialUtils.getInstance().ParseData(1, four_funan_json);
        } else if (sampleName.equals("孔雀石绿")) {
            SerialUtils.getInstance().ParseData(2, malachite_green_json);
        } else {
            SerialUtils.getInstance().ParseData(3, sr_json);
        }

    }


    private class AlertClickListener implements AlertDialogUtils.OnButtonClickListener {
        @Override
        public void onPositiveButtonClick(AlertDialog dialog, String name, int index, String sampleNumber, String oldSampleNumer) {
            mdialog = dialog;
            if (dialog_flag == 4) {
                mhandler.sendEmptyMessage(Contacts.SEND);
            } else if (dialog_flag == 5) {
//           if (isstop) {
//               byte[] result = new byte[]{0x1b};
//               SerialUtils.getInstance().WriteByte(result);
//           } else {
                byte[] result = new byte[]{0x1a};
                SerialUtils.getInstance().WriteByte(result);
//           }
            } else if (dialog_flag == 1) {
                Log.e("拿到回调事件", "onPositiveClick: " + name + sampleNumber + oldSampleNumer);
                SampleList sampleList = new SampleList();
                sampleList.setId(index + 1);
                sampleList.setSampleName(name);
                sampleList.setInformation(sampleName);
                sampleList.setSampleNumber(sampleNumber);
                sampleLists.add(sampleList);
                mlist.get(index).setEnabled(false);
                mlist.get(index).setBackgroundColor(getResources().getColor(R.color.blu));
                sampleListAdapter.notifyDataSetChanged();
                dialog.dismiss();
            } else if (dialog_flag == 2) {
                Log.e("拿到回调事件", "修改" + index);
                SampleList sampleList = new SampleList();
                sampleList.setId(index);
                sampleList.setSampleName(name);
                sampleList.setSampleNumber(sampleNumber);
                sampleList.setInformation(sampleName);
                sampleLists.add(sampleList);
                mlist.get(index).setEnabled(false);
                mlist.get(index).setBackgroundColor(getResources().getColor(R.color.blu));
                sampleListAdapter.notifyDataSetChanged();
                dialog.dismiss();
            } else if (dialog_flag == 3) {
                //删除
                Log.e("拿到回调事件", "删除");
            }
        }

        @Override
        public void onNegativeButtonClick(AlertDialog dialog) {
            dialog.dismiss();
        }
    }


    private void initKqCursor() {
        List<ConsumablesKQ> consumablesKQS = RecordDatabase.getinstance(getActivity()).consumablesKQDao().getAllKQ();
        Log.e(TAG, "孔雀石绿run: " + consumablesKQS.size());
        int i = 0, i1 = 0;
        if (consumablesKQS.size() > 0) {
            for (ConsumablesKQ consumablesKQ : consumablesKQS) {
                ConsumablesKQ consumablesKQBean = new ConsumablesKQ();
                if (SimpleUtils.getCurretTimes().equals(consumablesKQ.getKQTime())) {
                    i = i + 1;
                    i1 = i1 + 2;
                    consumablesKQBean.setSpe_num(i);
                    consumablesKQBean.setNsg_num(i1);
                    consumablesKQBean.setKQTime(SimpleUtils.getCurretTimes());
                } else {
                    consumablesKQBean.setSpe_num(1);
                    consumablesKQBean.setNsg_num(2);
                    consumablesKQBean.setKQTime(SimpleUtils.getCurretTimes());
                }
                consumablesKQBean.setSpe_total_num(consumablesKQ.getSpe_total_num() + 1);
                consumablesKQBean.setNsg_total_num(consumablesKQ.getNsg_total_num() + 1);
                consumablesKQBean.setXitou_total_num(consumablesKQ.getXitou_total_num() + 1);
                consumablesKQBean.setYijing_totalnum(consumablesKQ.getYijing_totalnum() + 1);
                consumablesKQBean.setYpg_total_num(consumablesKQ.getYpg_total_num() + 1);
                RecordDatabase.getinstance(getActivity()).consumablesKQDao().insertKQ(consumablesKQBean);
                Log.e(TAG, "run: " + "添加孔雀石绿成功");
            }
        } else {
            ConsumablesKQ consumablesKQBean = new ConsumablesKQ();
            consumablesKQBean.setYpg_total_num(1);
            consumablesKQBean.setYijing_totalnum(1);
            consumablesKQBean.setXitou_total_num(1);
            consumablesKQBean.setNsg_total_num(1);
            consumablesKQBean.setSpe_total_num(1);
            consumablesKQBean.setKQTime(SimpleUtils.getCurretTimes());
            consumablesKQBean.setXitou_num(1);
            consumablesKQBean.setNsg_num(1);
            consumablesKQBean.setSpe_num(1);
            consumablesKQBean.setYijing_num(1);
            consumablesKQBean.setYpg_num(1);
            RecordDatabase.getinstance(getActivity()).consumablesKQDao().insertKQ(consumablesKQBean);
        }
    }


    private void initFourFuNanCursor() {
        List<ConsumablesFuNan> consumablesFuNans = RecordDatabase.getinstance(getActivity()).consumablesFuNanDao().getAllFuNan();
        if (consumablesFuNans.size() > 0) {
            for (ConsumablesFuNan consumablesFuNan : consumablesFuNans) {
                ConsumablesFuNan consumablesFuNanBean = new ConsumablesFuNan();
                if (SimpleUtils.getCurretTimes().equals(consumablesFuNan.getFuNanTime())) {
//                    dmso_num = dmso_num + 1;
//                    acetate_num = acetate_num + 1;
                    consumablesFuNanBean.setDmso_num(consumablesFuNan.getDmso_num() + 1);
                    consumablesFuNanBean.setYpg_num(consumablesFuNan.getYpg_num() + 1);
                    consumablesFuNanBean.setEthyl_acetate_num(consumablesFuNan.getEthyl_acetate_num() + 1);
                    consumablesFuNanBean.setFuNanTime(SimpleUtils.getCurretTimes());
                } else {
                    consumablesFuNanBean.setDmso_num(1);
                    consumablesFuNanBean.setFuNanTime(SimpleUtils.getCurretTimes());
                    consumablesFuNanBean.setEthyl_acetate_num(1);
                }
                consumablesFuNanBean.setDmso_total_num(consumablesFuNan.getDmso_total_num() + 1);
                Log.e(TAG, "initFourFuNanCursor: " + consumablesFuNan.getDmso_total_num());
                RecordDatabase.getinstance(getActivity()).consumablesFuNanDao().insertFuNan(consumablesFuNanBean);
            }
        } else {
//   //默认初始化的值
            ConsumablesFuNan consumablesFuNan = new ConsumablesFuNan();
            consumablesFuNan.setFuNanTime(SimpleUtils.getCurretTimes());
            consumablesFuNan.setDmso_num(0);
            consumablesFuNan.setEthyl_acetate_num(0);
            consumablesFuNan.setEthyl_acetate_total_num(1);
            consumablesFuNan.setHcl_num(1);
            consumablesFuNan.setHcl_total_num(1);
            consumablesFuNan.setMethanol_num(1);
            consumablesFuNan.setMethanol_total_num(1);
            consumablesFuNan.setPbs_num(1);
            consumablesFuNan.setPbs_total_num(1);
            consumablesFuNan.setQyhn_num(1);
            consumablesFuNan.setQyhn_total_num(1);
            consumablesFuNan.setSpe_num(1);
            consumablesFuNan.setSpe_total_num(1);
            consumablesFuNan.setSuction_num(1);
            consumablesFuNan.setSuction_total_num(1);
            consumablesFuNan.setWaterpu_num(1);
            consumablesFuNan.setWaterpu_total_num(1);
            consumablesFuNan.setYpg_num(0);
            consumablesFuNan.setYpg_total_num(0);
            RecordDatabase.getinstance(getActivity()).consumablesFuNanDao().insertFuNan(consumablesFuNan);
        }
    }
}
