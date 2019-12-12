package com.mihua.yangben.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.bean.MyEventMessage;
import com.mihua.yangben.datepicker.CustomDatePicker;
import com.mihua.yangben.datepicker.DateFormatUtils;
import com.mihua.yangben.db.database.RecordDatabase;
import com.mihua.yangben.db.entity.ConsumablesFuNan;
import com.mihua.yangben.db.entity.ConsumablesKQ;
import com.mihua.yangben.db.entity.HisRecord;
import com.mihua.yangben.ui.adapter.HisRecyleAdapter;
import com.mihua.yangben.ui.adapter.TestArrayAdapter;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.SimpleUtils;
import com.mihua.yangben.view.AlertDialogUtils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lx on 2019/4/9.
 */

public class HistoryRecordFm extends Fragment {
    String TAG = "HistoryRecordFm";
    @BindView(R.id.rcl_historylist)
    RecyclerView rclHistorylist;
    HisRecyleAdapter hisRecyleAdapter;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_cons_num)
    TextView tvConsNum;
    @BindView(R.id.tv_spe_num)
    TextView tvSpeNum;
    @BindView(R.id.tv_hcl_num)
    TextView tvHclNum;
    @BindView(R.id.tv_dmso_num)
    TextView tvDmsoNum;
    @BindView(R.id.tv_qyhn_num)
    TextView tvQyhnNum;
    @BindView(R.id.tv_pbs_num)
    TextView tvPbsNum;
    @BindView(R.id.tv_methanol_num)
    TextView tvMethanolNum;
    @BindView(R.id.tv_waterpu_num)
    TextView tvWaterpuNum;
    @BindView(R.id.tv_suction_num)
    TextView tvSuctionNum;
    @BindView(R.id.tv_ethyl_acetate_num)
    TextView tvEthylAcetateNum;
    @BindView(R.id.tv_kqypg_num)
    TextView tvKqypgNum;
    @BindView(R.id.tv_kqyj_num)
    TextView tvKqyjNum;
    @BindView(R.id.tv_kqspe_num)
    TextView tvKqspeNum;
    @BindView(R.id.tv_kqxt_num)
    TextView tvKqxtNum;
    @BindView(R.id.tv_kqnsg_num)
    TextView tvKqnsgNum;
    @BindView(R.id.inc_funan_list)
    View inc_funan_list;
    @BindView(R.id.inc_kq_list)
    View inc_kq_list;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.ll_all_delete)
    LinearLayout llAllDelete;
    @BindView(R.id.rl_delete)
    RelativeLayout rlDelete;
    @BindView(R.id.rl_starttime)
    RelativeLayout rlStarttime;
    @BindView(R.id.rl_endtime)
    RelativeLayout rlEndtime;
    @BindView(R.id.tv_startTimer)
    TextView tvStartTimer;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.spread_inside)
    Spinner spreadInside;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.exportsss)
    ImageView exportsss;
    @BindView(R.id.updata)
    RelativeLayout updata;
    @BindView(R.id.exports)
    ImageView exports;
    @BindView(R.id.exportss)
    RelativeLayout exportss;
    @BindView(R.id.export)
    ImageView export;
    @BindView(R.id.ck_record)
    ImageView ck_record;
    @BindView(R.id.tv_cons_name)
    TextView tvConsName;
    @BindView(R.id.view1)
    TextView view1;
    @BindView(R.id.tv_spe_name)
    TextView tvSpeName;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.tv_hcl_name)
    TextView tvHclName;
    @BindView(R.id.view3)
    TextView view3;
    @BindView(R.id.tv_dmso_name)
    TextView tvDmsoName;
    @BindView(R.id.view4)
    TextView view4;
    @BindView(R.id.tv_qyhn_name)
    TextView tvQyhnName;
    @BindView(R.id.view5)
    TextView view5;
    @BindView(R.id.tv_pbs_name)
    TextView tvPbsName;
    @BindView(R.id.view6)
    TextView view6;
    @BindView(R.id.tv_methanol_name)
    TextView tvMethanolName;
    @BindView(R.id.view7)
    TextView view7;
    @BindView(R.id.tv_waterpu_name)
    TextView tvWaterpuName;
    @BindView(R.id.view8)
    TextView view8;
    @BindView(R.id.tv_suction_name)
    TextView tvSuctionName;
    @BindView(R.id.view9)
    TextView view9;
    @BindView(R.id.tv_ethyl_acetate_name)
    TextView tvEthylAcetateName;
    @BindView(R.id.view10)
    TextView view10;
    @BindView(R.id.tv_kqypg_name)
    TextView tvKqypgName;
    @BindView(R.id.tv_title_record)
    TextView tvTitleRecord;

    private boolean choose_flag;
    private boolean isSelectAll;
    private boolean record_flag = false;

    private LoadingDialog mLoadDialog;

    private CustomDatePicker mStartDatePicker;
    private CustomDatePicker mEndDatePicker;


    private View views;
    private List<HisRecord> hisRecordList;
    List<ConsumablesFuNan> consumablesFuNanList;
    List<ConsumablesKQ> consumablesKQList;
    private int index = 0;
    private int project_type;

    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mLoadDialog.dismiss();
                    hisRecordList = RecordDatabase.getinstance(getActivity()).hisRecordDao().getAll();
                    hisRecyleAdapter = new HisRecyleAdapter(getActivity(), hisRecordList);
                    if (hisRecordList.size() > 0) {
                    } else {
                        Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                    }
                    rclHistorylist.setAdapter(hisRecyleAdapter);
                    hisRecyleAdapter.setOnRclClickListener(new HisRecyleAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            if (!choose_flag) {
                                List<HisRecord> rq_where_pronodes = RecordDatabase.getinstance(getActivity()).hisRecordDao()
                                        .loadAllHisByRecordNo(hisRecordList.get(position).getmRecordNum(), hisRecordList.get(position).getmProjectType()).get(0).getList();
                                if (rq_where_pronodes.size() > 0) {
                                    AlertDialogUtils.getInstance().showHisItemDialog(getActivity(), rq_where_pronodes);
                                } else {
                                    Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                boolean isSelect = hisRecordList.get(position).ck_check;
                                if (!isSelect) {
                                    hisRecordList.get(position).setCk_check(true);
                                    index++;
                                    isSelectAll = true;
                                    if (index == hisRecordList.size()) {
                                        all.setText("取消全选");
                                    }
                                } else {
                                    hisRecordList.get(position).setCk_check(false);
                                    index--;
                                    isSelectAll = false;
                                    all.setText("全选");
                                }
                                hisRecyleAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    initFuNan();
                    break;
                case 2:
                    //查询全部数据
                    hisRecordList.clear();
                    List<HisRecord> hisRecordList1;
                    hisRecordList1 = RecordDatabase.getinstance(getActivity()).hisRecordDao().getAll();
                    hisRecordList.addAll(hisRecordList1);
                    hisRecyleAdapter.notifyDataSetChanged();
                    if (hisRecordList.size() > 0) {
                    } else {
                        Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                    }
                    initFuNan();
                    break;
                case 4:
                    for (HisRecord hisRecor : hisRecordList) {
                        if (hisRecor.isCk_check()) {
                            RecordDatabase.getinstance(getActivity()).hisRecordDao().deleteHisRc(hisRecor);
                            index--;
                        }
                        initRefreshDB();
                    }
                    Log.e(TAG, "che状态: " + index);
                    hisRecyleAdapter.notifyDataSetChanged();
                    break;
                case 5:
                    consumablesFuNanList.clear();
                    Log.e(TAG, "根据时间查询: " + SimpleUtils.getCurretTimes());
                    consumablesFuNanList = RecordDatabase.getinstance(getActivity()).consumablesFuNanDao().getCurretTime(SimpleUtils.getCurretTimes());
                    Log.e(TAG, "根据时间查询111: " + consumablesFuNanList.size());
                    if (consumablesFuNanList.size() > 0) {
                        toFuNanCurrentDay();
                    } else {
                        Toast.makeText(getActivity(), "当日暂无数据", Toast.LENGTH_SHORT).show();
                        record_flag = false;
                        ck_record.setImageResource(R.mipmap.not_check);
                    }
                    break;
                case 6:
                    inc_kq_list.setVisibility(View.INVISIBLE);
                    inc_funan_list.setVisibility(View.VISIBLE);
                    initFuNan();
                    break;
                case 7:
                    //查询呋喃四项
                    List<HisRecord> time_hislist1 = RecordDatabase.getinstance(getActivity()).
                            hisRecordDao().loadTimeBetween(long_starttimer, long_endtimer, 0);
                    if (time_hislist1.size() > 0) {
                        if (hisRecordList != null) {
                            hisRecordList.clear();
                            hisRecordList.addAll(time_hislist1);
                        }
                        hisRecyleAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "未查询到该项目下的数据", Toast.LENGTH_SHORT).show();
                    }
                    mhandler.sendEmptyMessage(6);
                    break;
                case 8:
                    //查询孔雀石
                    List<HisRecord> time_hislist2 = RecordDatabase.getinstance(getActivity()).
                            hisRecordDao().loadTimeBetween(long_starttimer, long_endtimer, 1);
                    if (time_hislist2.size() > 0) {
                        if (hisRecordList != null) {
                            hisRecordList.clear();
                            hisRecordList.addAll(time_hislist2);
                        }
                        hisRecyleAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "未查询到该项目下的数据", Toast.LENGTH_SHORT).show();
                    }
                    mhandler.sendEmptyMessage(9);
                    break;
                case 9:
                    //查询所有孔雀石数据
                    inc_funan_list.setVisibility(View.INVISIBLE);
                    inc_kq_list.setVisibility(View.VISIBLE);
                    if (consumablesFuNanList != null) {
                        consumablesFuNanList.clear();
                    }
                    queryAllKQ();
                    break;
                case 10:
                    if (consumablesFuNanList != null) {
                        consumablesFuNanList.clear();
                    }
                    //查询当日孔雀石数据
                    queryCurretKQ();
                case 11:
                    //查询全部数据时间限制内
                    if (hisRecordList != null) {
                        hisRecordList.clear();
                    }
                    List<HisRecord> hisRecordList2;
                    hisRecordList2 = RecordDatabase.getinstance(getActivity()).hisRecordDao().getAllByDay(long_starttimer, long_endtimer);
                    if (hisRecordList.size() > 0) {
                        hisRecordList.addAll(hisRecordList2);
                        hisRecyleAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @SuppressLint("SetTextI18n")
    private void toFuNanCurrentDay() {
        for (ConsumablesFuNan consumablesFuNan : consumablesFuNanList) {
            Log.e(TAG, "toFuNan: " + consumablesFuNan.getFuNanTime());
            tvConsNum.setText(consumablesFuNan.getYpg_num() + "个");
            tvDmsoNum.setText(consumablesFuNan.getDmso_num() + "ml");
            tvEthylAcetateNum.setText(consumablesFuNan.getEthyl_acetate_num() + "ml");
            tvHclNum.setText(consumablesFuNan.getHcl_num() + "ml");
            tvMethanolNum.setText(consumablesFuNan.getMethanol_num() + "ml");
            tvPbsNum.setText(consumablesFuNan.getPbs_num() + "ml");
            tvQyhnNum.setText(consumablesFuNan.getQyhn_num() + "ml");
            tvSpeNum.setText(consumablesFuNan.getSpe_num() + "个");
            tvSuctionNum.setText(consumablesFuNan.getSuction_num() + "个");
            tvWaterpuNum.setText(consumablesFuNan.getWaterpu_num() + "ml");
        }
    }


    private long long_starttimer;
    private long long_endtimer;

    private void initFuNan() {
        if (consumablesFuNanList != null) {
            consumablesFuNanList.clear();
        }
        consumablesFuNanList = RecordDatabase.getinstance(getActivity()).consumablesFuNanDao().getAllFuNan();
        toFuNanAll();
    }

    @SuppressLint("SetTextI18n")
    private void toFuNanAll() {
        for (ConsumablesFuNan consumablesFuNan : consumablesFuNanList) {
            Log.e(TAG, "toFuNan: " + consumablesFuNan.getFuNanTime());
            tvConsNum.setText(consumablesFuNan.getYpg_total_num() + "个");
            tvDmsoNum.setText(consumablesFuNan.getDmso_total_num() + "ml");
            tvEthylAcetateNum.setText(consumablesFuNan.getEthyl_acetate_total_num() + "ml");
            tvHclNum.setText(consumablesFuNan.getHcl_total_num() + "ml");
            tvMethanolNum.setText(consumablesFuNan.getMethanol_total_num() + "ml");
            tvPbsNum.setText(consumablesFuNan.getPbs_total_num() + "ml");
            tvQyhnNum.setText(consumablesFuNan.getQyhn_total_num() + "ml");
            tvSpeNum.setText(consumablesFuNan.getSpe_total_num() + "个");
            tvSuctionNum.setText(consumablesFuNan.getSuction_total_num() + "个");
            tvWaterpuNum.setText(consumablesFuNan.getWaterpu_total_num() + "ml");
        }
    }

    private void queryAllKQ() {
        consumablesKQList = RecordDatabase.getinstance(getActivity()).consumablesKQDao().getAllKQ();
        Log.e(TAG, "queryAllKQ: " + consumablesKQList.size());
        toKQ();

    }


    @SuppressLint("SetTextI18n")
    private void queryCurretKQ() {
        if (consumablesFuNanList != null) {
            consumablesFuNanList.clear();
        }
        consumablesKQList = RecordDatabase.getinstance(getActivity()).consumablesKQDao().getCurretTime(SimpleUtils.getCurretTimes());
        if (consumablesKQList.size() > 0) {
            for (ConsumablesKQ consumablesKQ : consumablesKQList) {
                tvKqypgNum.setText(consumablesKQ.getYpg_num() + "个");
                tvKqspeNum.setText(consumablesKQ.getSpe_num() + "个");
                tvKqnsgNum.setText(consumablesKQ.getNsg_num() + "个");
                tvKqxtNum.setText(consumablesKQ.getXitou_num() + "个");
                tvKqyjNum.setText(consumablesKQ.getYijing_totalnum() + "ml");
            }
        } else {
            Toast.makeText(getActivity(), "当日暂无数据", Toast.LENGTH_SHORT
            ).show();
        }
    }


    @SuppressLint("SetTextI18n")
    private void toKQ() {
        for (ConsumablesKQ consumablesKQ : consumablesKQList) {
            tvKqypgNum.setText(consumablesKQ.getYpg_total_num() + "个");
            tvKqspeNum.setText(consumablesKQ.getSpe_total_num() + "个");
            tvKqnsgNum.setText(consumablesKQ.getNsg_total_num() + "个");
            tvKqxtNum.setText(consumablesKQ.getXitou_total_num() + "个");
            tvKqyjNum.setText(consumablesKQ.getYijing_totalnum() + "ml");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = inflater.inflate(R.layout.fragment_history_record, container, false);
        ButterKnife.bind(this, views);
        init();
        return views;
    }

    private void init() {
        if (mLoadDialog == null) {
            mLoadDialog = new LoadingDialog(getActivity());
        }
        String[] curs = {"全部样本", "呋喃四项", "孔雀石绿"};
        TestArrayAdapter adapter = new TestArrayAdapter(getActivity(), curs);
        spreadInside.setAdapter(adapter);
        spreadInside.setSelection(0);
        spreadInside.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setTextColor(getActivity().getResources().getColor(R.color.black));    //设置颜色
                tv.setTextSize(16.0f);    //设置大小
                //   Logger.e("我点击了"+position);
                project_type = position;
                Log.e(TAG, "onItemSelected: " + project_type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initDataPicker();
        initData();
    }


    private void initData() {
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        rclHistorylist.setLayoutManager(lm);
        initDB();
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            initRefreshDB();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            initRefreshDB();
        });


    }

    private void initDB() {
        mLoadDialog.setTvTip("数据加载中");
        mLoadDialog.show();
        tvTitleRecord.setText("历史记录(全部样本)");
        mhandler.sendEmptyMessageDelayed(1, 1000);
    }

    private void initRefreshDB() {
        if (project_type == 1) {
            tvTitleRecord.setText("历史记录(呋喃四项)");
            mhandler.sendEmptyMessage(7);
        } else if (project_type == 2) {
            tvTitleRecord.setText("历史记录(孔雀石绿)");
            mhandler.sendEmptyMessage(8);
            //则查询孔雀石的表
        } else if (project_type == 0) {
            tvTitleRecord.setText("历史记录(全部样本)");
            mhandler.sendEmptyMessageDelayed(2, 500);
        }
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

        if (message.getUpdatas() != null) {
          /*  page=1;
            localInitData(startTime,endTime,type,false);
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);*/
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {

    }


    @OnClick({R.id.tv_delete, R.id.back, R.id.all, R.id.ll_all_delete, R.id.rl_delete, R.id.rl_starttime, R.id.rl_endtime
            , R.id.iv_search, R.id.ck_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                choose_flag = true;
                llAllDelete.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.GONE);
                for (HisRecord hisRecord : hisRecordList) {
                    hisRecord.setCk_visible(true);
                    hisRecord.setCk_check(false);
                }
                hisRecyleAdapter.notifyDataSetChanged();
                break;
            case R.id.back:
                choose_flag = false;
                llAllDelete.setVisibility(View.GONE);
                tvDelete.setVisibility(View.VISIBLE);
                for (HisRecord hisRecord : hisRecordList) {
                    hisRecord.setCk_visible(false);
                    hisRecord.setCk_check(false);
                }
                hisRecyleAdapter.notifyDataSetChanged();
                break;
            case R.id.all:
                if (choose_flag) {
                    if (!isSelectAll) {
                        for (HisRecord hisRecord : hisRecordList) {
                            hisRecord.setCk_check(true);
                        }
                        index = hisRecordList.size();
                        all.setText("取消全选");
                        isSelectAll = true;
                    } else {
                        for (HisRecord hisRecord : hisRecordList) {
                            hisRecord.setCk_check(false);
                        }
                        index = 0;
                        all.setText("全选");
                        isSelectAll = false;
                    }
                    hisRecyleAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "请先进入编辑模式", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_delete:
                if (choose_flag) {
                    mhandler.sendEmptyMessage(4);
                } else {
                    Toast.makeText(getActivity(), "请先进入编辑模式", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_starttime:
                Log.e(TAG, "onViewClicked: " + "点击开始");
                mStartDatePicker.show("yyyy-MM-dd");
                break;
            case R.id.rl_endtime:
                Log.e(TAG, "onViewClicked: " + "点击关闭");
                mEndDatePicker.show("yyyy-MM-dd");
                break;
            case R.id.iv_search:
                ck_record.setImageResource(R.mipmap.not_check);
                record_flag = false;
                long_starttimer = DateFormatUtils.str2Long(tvStartTimer.getText().toString(), false);
                long_endtimer = DateFormatUtils.str2Long(tvEndTime.getText().toString(), false);
                if (TextUtils.isEmpty(tvStartTimer.getText()) || (TextUtils.isEmpty(tvEndTime.getText()))) {
                    Toast.makeText(getActivity(), "未选择开始时间或结束时间", Toast.LENGTH_SHORT).show();
                } else {
                    if (project_type == 1) {
                        tvTitleRecord.setText("历史记录(呋喃四项)");
                        mhandler.sendEmptyMessage(7);
                    } else if (project_type == 2) {
                        tvTitleRecord.setText("历史记录(孔雀石绿)");
                        mhandler.sendEmptyMessage(8);
                        //则查询孔雀石的表
                    } else if (project_type == 0) {
                        tvTitleRecord.setText("历史记录(全部样本)");
                        mhandler.sendEmptyMessage(11);
                    }
                }
                break;
            case R.id.ck_record:
                if (project_type == 0) {
                    Log.e(TAG, "onViewClicked: " + "222222");
                    if (!record_flag) {
                        mhandler.sendEmptyMessage(5);
                        record_flag = true;
                        ck_record.setImageResource(R.mipmap.has_check);
                    } else {
                        mhandler.sendEmptyMessage(6);
                        record_flag = false;
                        ck_record.setImageResource(R.mipmap.not_check);
                    }
                } else {
                    if (!record_flag) {
                        mhandler.sendEmptyMessage(10);
                        record_flag = true;
                        ck_record.setImageResource(R.mipmap.has_check);
                    } else {
                        mhandler.sendEmptyMessage(9);
                        record_flag = false;
                        ck_record.setImageResource(R.mipmap.not_check);
                    }
                }
                break;
        }
    }

    private void initDataPicker() {
        tvStartTimer.setText("2019-01-01");
        tvEndTime.setText(SimpleUtils.getCurretTimes());
        long beginTimestamp = DateFormatUtils.str2Long("2019-01-01", false);
        long endTimestamp = System.currentTimeMillis();
        mStartDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                Log.e(TAG, "onTimeSelected: " + "时间戳" + timestamp);
                Log.e(TAG, "onTimeSelected: " + "时间" + DateFormatUtils.long2Str(timestamp, false));
                tvStartTimer.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
        mStartDatePicker.setCancelable(false);
        mStartDatePicker.setTitles("请选择开始时间");
        // 不显示时和分
        mStartDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mStartDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mStartDatePicker.setCanShowAnim(false);

        mEndDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvEndTime.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);

        mEndDatePicker.setCancelable(false);
        mEndDatePicker.setTitles("请选择结束时间");
        // 不显示时和分
        mEndDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mEndDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mEndDatePicker.setCanShowAnim(false);
    }


    @OnClick(R.id.reset)
    public void onViewClicked() {
        tvStartTimer.setText("2019-01-01");
        tvEndTime.setText(SimpleUtils.getCurretTimes());
        project_type = 0;
        spreadInside.setSelection(0);
        inc_funan_list.setVisibility(View.VISIBLE);
        inc_kq_list.setVisibility(View.INVISIBLE);
//        spreadInside.notify();
        initRefreshDB();
    }

}