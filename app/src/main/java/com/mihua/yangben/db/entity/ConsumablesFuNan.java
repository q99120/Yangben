package com.mihua.yangben.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 呋喃四项耗材记录表
 */

@Entity(tableName = "consumables_funan_table")
public class ConsumablesFuNan {
    @PrimaryKey
    public int id;

    public int ypg_num;
    public int spe_num;
    public int hcl_num;
    public int dmso_num;
    public int qyhn_num;
    public int pbs_num;
    public int methanol_num;
    public int waterpu_num;
    public int suction_num;
    public int ethyl_acetate_num;
    public String fuNanTime;

    public int ypg_total_num;
    public int spe_total_num;
    public int hcl_total_num;
    public int dmso_total_num;
    public int qyhn_total_num;
    public int pbs_total_num;
    public int methanol_total_num;
    public int waterpu_total_num;
    public int suction_total_num;
    public int ethyl_acetate_total_num;

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

    public int getSpe_num() {
        return spe_num;
    }

    public void setSpe_num(int spe_num) {
        this.spe_num = spe_num;
    }

    public int getHcl_num() {
        return hcl_num;
    }

    public void setHcl_num(int hcl_num) {
        this.hcl_num = hcl_num;
    }

    public int getDmso_num() {
        return dmso_num;
    }

    public void setDmso_num(int dmso_num) {
        this.dmso_num = dmso_num;
    }

    public int getQyhn_num() {
        return qyhn_num;
    }

    public void setQyhn_num(int qyhn_num) {
        this.qyhn_num = qyhn_num;
    }

    public int getPbs_num() {
        return pbs_num;
    }

    public void setPbs_num(int pbs_num) {
        this.pbs_num = pbs_num;
    }

    public int getMethanol_num() {
        return methanol_num;
    }

    public void setMethanol_num(int methanol_num) {
        this.methanol_num = methanol_num;
    }

    public int getWaterpu_num() {
        return waterpu_num;
    }

    public void setWaterpu_num(int waterpu_num) {
        this.waterpu_num = waterpu_num;
    }

    public int getSuction_num() {
        return suction_num;
    }

    public void setSuction_num(int suction_num) {
        this.suction_num = suction_num;
    }

    public int getEthyl_acetate_num() {
        return ethyl_acetate_num;
    }

    public void setEthyl_acetate_num(int ethyl_acetate_num) {
        this.ethyl_acetate_num = ethyl_acetate_num;
    }

    public String getFuNanTime() {
        return fuNanTime;
    }

    public void setFuNanTime(String fuNanTime) {
        this.fuNanTime = fuNanTime;
    }

    public int getYpg_total_num() {
        return ypg_total_num;
    }

    public void setYpg_total_num(int ypg_total_num) {
        this.ypg_total_num = ypg_total_num;
    }

    public int getSpe_total_num() {
        return spe_total_num;
    }

    public void setSpe_total_num(int spe_total_num) {
        this.spe_total_num = spe_total_num;
    }

    public int getHcl_total_num() {
        return hcl_total_num;
    }

    public void setHcl_total_num(int hcl_total_num) {
        this.hcl_total_num = hcl_total_num;
    }

    public int getDmso_total_num() {
        return dmso_total_num;
    }

    public void setDmso_total_num(int dmso_total_num) {
        this.dmso_total_num = dmso_total_num;
    }

    public int getQyhn_total_num() {
        return qyhn_total_num;
    }

    public void setQyhn_total_num(int qyhn_total_num) {
        this.qyhn_total_num = qyhn_total_num;
    }

    public int getPbs_total_num() {
        return pbs_total_num;
    }

    public void setPbs_total_num(int pbs_total_num) {
        this.pbs_total_num = pbs_total_num;
    }

    public int getMethanol_total_num() {
        return methanol_total_num;
    }

    public void setMethanol_total_num(int methanol_total_num) {
        this.methanol_total_num = methanol_total_num;
    }

    public int getWaterpu_total_num() {
        return waterpu_total_num;
    }

    public void setWaterpu_total_num(int waterpu_total_num) {
        this.waterpu_total_num = waterpu_total_num;
    }

    public int getSuction_total_num() {
        return suction_total_num;
    }

    public void setSuction_total_num(int suction_total_num) {
        this.suction_total_num = suction_total_num;
    }

    public int getEthyl_acetate_total_num() {
        return ethyl_acetate_total_num;
    }

    public void setEthyl_acetate_total_num(int ethyl_acetate_total_num) {
        this.ethyl_acetate_total_num = ethyl_acetate_total_num;
    }
}
