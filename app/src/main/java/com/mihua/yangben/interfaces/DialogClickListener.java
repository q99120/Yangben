package com.mihua.yangben.interfaces;

import android.app.AlertDialog;

public interface DialogClickListener {

    /**
     * 确定按钮点击回调方法
     *
     * @param dialog 当前 AlertDialog，传入它是为了在调用的地方对 dialog 做操作，比如 dismiss()
     *               也可以在该工具类中直接  dismiss() 掉，就不用将 AlertDialog 对象传出去了
     *               传入样本编号，样本名称,通道编号
     */
    void onPositiveClick(AlertDialog dialog, String name, int new_num, int old_num);

    /**
     * 取消按钮点击回调方法
     *
     * @param dialog 当前AlertDialog
     */
    void onNegativeClick(AlertDialog dialog);
}
