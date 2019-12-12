package com.mihua.yangben.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 呋喃四项耗材记录表
 */

@Entity(tableName = "consumables_kq_table")
public class ConsumablesKQ {
    @PrimaryKey
    public int id;

    public int ypg_num;
    public int yijing_num;
    public int spe_num;
    public int xitou_num;
    public int nsg_num;

    public int ypg_total_num;
    public int yijing_totalnum;
    public int spe_total_num;
    public int xitou_total_num;
    public int nsg_total_num;
    public String KQTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYpg_num() {
        return ypg_num;
    }

    public void setYpg_num(int ypg_num) {
        this.ypg_num = ypg_num;
    }

    public int getYijing_num() {
        return yijing_num;
    }

    public void setYijing_num(int yijing_num) {
        this.yijing_num = yijing_num;
    }

    public int getSpe_num() {
        return spe_num;
    }

    public void setSpe_num(int spe_num) {
        this.spe_num = spe_num;
    }

    public int getXitou_num() {
        return xitou_num;
    }

    public void setXitou_num(int xitou_num) {
        this.xitou_num = xitou_num;
    }

    public int getNsg_num() {
        return nsg_num;
    }

    public void setNsg_num(int nsg_num) {
        this.nsg_num = nsg_num;
    }

    public String getKQTime() {
        return KQTime;
    }

    public void setKQTime(String KQTime) {
        this.KQTime = KQTime;
    }

    public int getYpg_total_num() {
        return ypg_total_num;
    }

    public void setYpg_total_num(int ypg_total_num) {
        this.ypg_total_num = ypg_total_num;
    }

    public int getYijing_totalnum() {
        return yijing_totalnum;
    }

    public void setYijing_totalnum(int yijing_totalnum) {
        this.yijing_totalnum = yijing_totalnum;
    }

    public int getSpe_total_num() {
        return spe_total_num;
    }

    public void setSpe_total_num(int spe_total_num) {
        this.spe_total_num = spe_total_num;
    }

    public int getXitou_total_num() {
        return xitou_total_num;
    }

    public void setXitou_total_num(int xitou_total_num) {
        this.xitou_total_num = xitou_total_num;
    }

    public int getNsg_total_num() {
        return nsg_total_num;
    }

    public void setNsg_total_num(int nsg_total_num) {
        this.nsg_total_num = nsg_total_num;
    }
}
