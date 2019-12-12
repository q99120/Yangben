package com.mihua.yangben.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mihua.yangben.R;
import com.mihua.yangben.db.entity.HisRecord;
import com.mihua.yangben.ui.adapter.HisRecyleListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;

/**
 * Created by lx on 2019/4/9.
 */

public class AlertDialogUtils {
    @BindView(R.id.edit_sample_no)
    EditText editSampleNo;
    @BindView(R.id.edit_sample_name)
    EditText editSampleName;
    @BindView(R.id.tv_message_dialog)
    TextView tvMessageDialog;
    @BindView(R.id.tv_confirm_dialog)
    TextView tvConfirmDialog;
    @BindView(R.id.tv_cancel_dialog)
    TextView tvCancelDialog;
    @BindView(R.id.tv_channel_no)
    TextView tvChannelNo;
    private View view;

    public static AlertDialogUtils getInstance() {
        return new AlertDialogUtils();
    }

    private static OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        AlertDialogUtils.onButtonClickListener = onButtonClickListener;
    }

    /**
     * 按钮点击回调接口
     */
    public interface OnButtonClickListener {
        /**
         * 确定按钮点击回调方法
         *
         * @param dialog 当前 AlertDialog，传入它是为了在调用的地方对 dialog 做操作，比如 dismiss()
         *               也可以在该工具类中直接  dismiss() 掉，就不用将 AlertDialog 对象传出去了
         */
        void onPositiveButtonClick(AlertDialog dialog, String name, int index, String sampleNumber, String oldSampleNumer);

        /**
         * 取消按钮点击回调方法
         *
         * @param dialog 当前AlertDialog
         */
        void onNegativeButtonClick(AlertDialog dialog);
    }


    /**
     * 带有确认取消按钮的自定义dialog
     *
     * @param context 上下文
     * @param message 显示的信息
     */

    private Context context;


    //新增样本的时候添加对话框
    public void showAddDialogs(final Activity context, int dialog_flag, int channel, String new_num, String old_num) {
        this.context = context;
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog).create();
        alertDialog.setCancelable(false);
        view = View.inflate(context, R.layout.view_alert_dialog_confirm, null);
        ButterKnife.bind(this, view);
        alertDialog.show();
        // 以下两行代码是改变对话框的EditText点击后不能显示输入法的
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().clearFlags(FLAG_NOT_FOCUSABLE | FLAG_ALT_FOCUSABLE_IM);
            alertDialog.getWindow().setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
        }

        if (dialog_flag == 1) {
            tvChannelNo.setText("新增通道" + channel + 1);
        } else if (dialog_flag == 2) {
            tvChannelNo.setText("修改通道" + channel + 1);
            editSampleNo.setText(old_num);
        }

        tvCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setDialogBack(alertDialog);
//                dialogClickListener.
            }
        });
        tvConfirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog_flag == 1) {
                    if (editSampleNo.getText().toString().equals("")) {
                        Toast.makeText(context, "样品编号不能为空", Toast.LENGTH_SHORT).show();
                    } else if (editSampleName.getText().toString().equals("")) {
                        Toast.makeText(context, "样品名称不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        onButtonClickListener.onPositiveButtonClick(alertDialog, editSampleName.getText().toString().trim(), channel, editSampleNo.getText().toString().trim(), "");
                    }
                } else if (dialog_flag == 2) {
                    onButtonClickListener.onPositiveButtonClick(alertDialog, editSampleName.getText().toString().trim(), channel, editSampleNo.getText().toString().trim(), "");
                }
            }
        });

        alertDialog.getWindow().setContentView(view);
    }

    /**
     * 点击开始后弹出的对话框
     */
    /**
     * 弹出自定义样式的AlertDialog
     *
     * @param context 上下文
     * @param title   AlertDialog的标题
     * @param tv      点击弹出框选择条目后，要改变文字的TextView
     * @param args    作为弹出框中item显示的字符串数组
     */
    public void showStartDialog(Context context) {
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialog);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        View view = LayoutInflater.from(context).inflate(R.layout.view_alert_dialog_text, null);
        TextView tvCancel = view.findViewById(R.id.tv_cancel_dialog);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm_dialog);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onNegativeButtonClick(dialog);
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         /*       mDialogs = new LoadingDialog(contexts);

                mDialogs.setTvTip("正在设置中");
                mDialogs.show();*/
                onButtonClickListener.onPositiveButtonClick(dialog, "", 1, "", "");
            }
        });

        dialog.getWindow().setContentView(view);
    }

    /**
     * 点击历史记录中的item弹出的对话框
     */
    public void showHisItemDialog(Context context, List<HisRecord> hisRecords) {
        this.context = context;
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog).create();
        alertDialog.setCancelable(false);
        view = View.inflate(context, R.layout.view_alert_dialog_hisitem, null);
        alertDialog.show();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setContentView(view);
        }
        for (HisRecord hisRecord : hisRecords) {
            Log.e("查询对话框事件", "查询对话框事件: " + hisRecord.getmSampleName());
        }
        HisRecyleListAdapter adapter;
        RecyclerView recyclerView = view.findViewById(R.id.rcl_click_item);
        TextView tv_his_close = view.findViewById(R.id.tv_his_close);
        TextView tv_record_pro = view.findViewById(R.id.tv_record_pro);
        tv_record_pro.setText("具体处理记录如下(" + hisRecords.size() + ")");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HisRecyleListAdapter(context, hisRecords);
        recyclerView.setAdapter(adapter);

        tv_his_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
//        Objects.requireNonNull(dialog.getWindow()).setContentView(view);
    }


}
