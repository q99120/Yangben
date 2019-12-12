package com.mihua.yangben.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mihua.yangben.R;
import com.mihua.yangben.app.Contacts;
import com.mihua.yangben.bean.SolventBean;
import com.mihua.yangben.ui.activity.HomeActivity;
import com.mihua.yangben.ui.adapter.SolventRecyleListAdapter;
import com.mihua.yangben.ui.adapter.TestArrayAdapter;
import com.mihua.yangben.utils.SPUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;


public class SolventSettingFm extends Fragment {
    String TAG = "SolventSetFm";
    @BindView(R.id.action_bar_spinner)
    Spinner spinner;
    //    @BindView(R.id.view_funan)
//    View view_funan;
//    @BindView(R.id.view_kqs)
//    View view_kqs;
//    @BindView(R.id.view_shourou)
//    View view_shourou;
    @BindView(R.id.rcl_set_solvent)
    RecyclerView rclSetSolvent;
    SolventRecyleListAdapter adapter;
    SolventBean solventBean;
    List<SolventBean> solventBeanList;
    @BindView(R.id.btn_save_curs)
    Button btnSaveCurs;
    @BindView(R.id.title_solve_choose)
    TextView title_solve_choose;
    //呋喃四项变量
    private String[] funan_array = new String[]{"盐酸", "DMSO", "孵育温度", "孵育时间", "搅拌转速", "搅拌时间", "氢氧化钠"
            , "PBS缓冲液", "二次搅拌转速", "二次搅拌时间", "甲醇", "活化正压", "活化时间", "活化水", "二次活化正压",
            "二次活化时间", "上样样本液", "上样正压", "上样时间", "上样次数", "净化水", "净化正压", "净化时间",
            "乙酸乙酯", "洗脱正压", "洗脱时间", "浓缩孵育温度", "浓缩吹气温度", "浓缩时间", ""};
    private String[] kq_array = new String[]{"乙腈", "搅拌转速", "搅拌时间", "活化正压", "活化时间", "上样样本液", "上样正压", "上样时间"
            , "上样次数", "浓缩孵育温度", "浓缩吹气温度", "浓缩时间", ""};
    List<String> list = new ArrayList<>();
    List<SolventBean> funan_json_list;
    public HomeActivity homeactivity;
    Gson gson;
    private String str_json_funanlist, str_json_kqlist, str_json_srlist;
    private boolean isSpinnerClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solvent_set, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView: " + "2222");
        initData();
        initClick();
        return view;
    }

    private void initData() {
        LinearLayoutManager ll = new LinearLayoutManager(homeactivity);
        rclSetSolvent.setLayoutManager(ll);
        String[] curs = new String[]{"呋喃四项", "孔雀石绿", "瘦肉精类"};
        title_solve_choose.setText("呋喃四项");
        TestArrayAdapter testArrayAdapter = new TestArrayAdapter(homeactivity, curs);

        spinner.setAdapter(testArrayAdapter);
        spinner.setSelection(0);
        gson = new Gson();

        str_json_funanlist = SPUtils.getString(homeactivity, "list_funan_json", Contacts.funan_json);
        funan_json_list = gson.fromJson(str_json_funanlist, new TypeToken<List<SolventBean>>() {
        }.getType());
        solventBeanList = new ArrayList<>();
        for (SolventBean solventBeans : funan_json_list) {
            solventBean = new SolventBean();
            solventBean.setSolven_name(solventBeans.getSolven_name());
            solventBean.setSolven_num(solventBeans.getSolven_num());
            solventBeanList.add(solventBean);
        }
        adapter = new SolventRecyleListAdapter(homeactivity, solventBeanList);
        rclSetSolvent.setAdapter(adapter);
        isSpinnerClick = false;
    }

    private void initClick() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int itemposition, long l) {
                if (itemposition == 0) {
                    title_solve_choose.setText("呋喃四项");
                    if (isSpinnerClick) {
                        if (solventBeanList.size() > 0) {
                            solventBeanList.clear();
                        }
                        str_json_funanlist = SPUtils.getString(homeactivity, "list_funan_json", Contacts.funan_json);
                        funan_json_list = gson.fromJson(str_json_funanlist, new TypeToken<List<SolventBean>>() {
                        }.getType());
                        solventBeanList.addAll(funan_json_list);
                        adapter.notifyDataSetChanged();
                    }
                } else if (itemposition == 1) {
                    title_solve_choose.setText("孔雀石绿");
                    isSpinnerClick = true;
                    if (solventBeanList.size() > 0) {
                        solventBeanList.clear();
                    }
                    str_json_kqlist = SPUtils.getString(homeactivity, "list_kq_json", Contacts.kq_json);
                    Log.e(TAG, "onItemSelected: " + str_json_kqlist);
                    List<SolventBean> kq_json_lists = gson.fromJson(str_json_kqlist, new TypeToken<List<SolventBean>>() {
                    }.getType());
                    solventBeanList.addAll(kq_json_lists);
                    adapter.notifyDataSetChanged();
                } else if (itemposition == 2) {
                    title_solve_choose.setText("瘦肉精类");
                    if (solventBeanList.size() > 0) {
                        solventBeanList.clear();
                    }
                    str_json_srlist = SPUtils.getString(homeactivity, "list_sr_json", Contacts.sr_json);
                    Log.e(TAG, "onItemSelected3: " + str_json_srlist);
                    List<SolventBean> sr_json_lists = gson.fromJson(str_json_srlist, new TypeToken<List<SolventBean>>() {
                    }.getType());
                    solventBeanList.addAll(sr_json_lists);
                    adapter.notifyDataSetChanged();
                }
            }

            //
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter.setOnRclClickListener(new SolventRecyleListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Log.e(TAG, "onClick: " + position);
                final AlertDialog alertDialog = new AlertDialog.Builder(homeactivity, R.style.dialog).create();
                alertDialog.setCancelable(false);
                View views = View.inflate(homeactivity, R.layout.view_alert_item_solvnum, null);
                TextView tv_cancel = views.findViewById(R.id.tv_cancel_dialog);
                TextView tv_confirm = views.findViewById(R.id.tv_confirm_dialog);
                EditText edit_sov_no = views.findViewById(R.id.edit_sov_no);
                TextView tv_sov_name = views.findViewById(R.id.tv_sov_name);
                alertDialog.show();
                // 以下两行代码是改变对话框的EditText点击后不能显示输入法的
                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().clearFlags(FLAG_NOT_FOCUSABLE | FLAG_ALT_FOCUSABLE_IM);
                    alertDialog.getWindow().setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
                }
                alertDialog.getWindow().setContentView(views);
                tv_sov_name.setText(solventBeanList.get(position).getSolven_name());
                edit_sov_no.setText(solventBeanList.get(position).getSolven_num() + "");
                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        solventBeanList.get(position).setSolven_num(Integer.parseInt(edit_sov_no.getText().toString().trim()));
                        adapter.notifyItemChanged(position);
                        alertDialog.dismiss();
                    }
                });

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: " + "2222");
        homeactivity = (HomeActivity) context;
        super.onAttach(context);
    }


    @Override
    public void onResume() {
        Log.e(TAG, "onResume: " + "1111");
        super.onResume();
    }

    @OnClick(R.id.btn_save_curs)
    public void onViewClicked() {
        if (spinner.getSelectedItemPosition() == 0) {
            Map<String, Integer> funan_map = new HashMap<>();
            for (SolventBean solventBean : solventBeanList) {
                funan_map.put(solventBean.getSolven_name(), solventBean.getSolven_num());
            }
            Log.e(TAG, "onViewClicked: " + funan_map.toString());
            Gson gson = new Gson();
            String map_funan_json = gson.toJson(funan_map);
            SPUtils.putString(homeactivity, "map_funan_json", map_funan_json);
            Log.e(TAG, "onViewClicked: " + map_funan_json);


            String list_json_funan = gson.toJson(solventBeanList);
            SPUtils.putString(homeactivity, "list_funan_json", list_json_funan);
            Toast.makeText(homeactivity, "保存呋喃四项数据成功", Toast.LENGTH_SHORT).show();
        } else if (spinner.getSelectedItemPosition() == 1) {
            Map<String, Integer> kq_map = new HashMap<>();
            for (SolventBean solventBean : solventBeanList) {
                kq_map.put(solventBean.getSolven_name(), solventBean.getSolven_num());
            }
            Gson gson = new Gson();
            String map_json_kq = gson.toJson(kq_map);
            Log.e(TAG, "onViewClicked11122: " + map_json_kq);
            SPUtils.putString(homeactivity, "map_kq_json", map_json_kq);

            String list_kq_json = gson.toJson(solventBeanList);
            SPUtils.putString(homeactivity, "list_kq_json", list_kq_json);
            Toast.makeText(homeactivity, "保存孔雀石绿数据成功", Toast.LENGTH_SHORT).show();
        } else if (spinner.getSelectedItemPosition() == 2) {
            Map<String, Integer> kq_map = new HashMap<>();
            for (SolventBean solventBean : solventBeanList) {
                kq_map.put(solventBean.getSolven_name(), solventBean.getSolven_num());
            }
            Gson gson = new Gson();
            String map_json_kq = gson.toJson(kq_map);
            Log.e(TAG, "onViewClicked11122: " + map_json_kq);
            SPUtils.putString(homeactivity, "map_sr_json", map_json_kq);

            String list_kq_json = gson.toJson(solventBeanList);
            SPUtils.putString(homeactivity, "list_sr_json", list_kq_json);
            Toast.makeText(homeactivity, "保存瘦肉数据成功", Toast.LENGTH_SHORT).show();
        }
    }
}
