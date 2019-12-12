package com.mihua.yangben.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.mihua.yangben.db.conventer.RecordBeanTypeConverter;

import java.util.List;

/**
 * 历史记录
 */

@Entity(tableName = "his_table")
public class HisRecord {
    @PrimaryKey(autoGenerate = true)
    public int hid;

    public String mRecordNum;
    public int mRecordStatus;
    public String mProNode;
    public String mProTime;
    //加个只存日期的字段
    public long mProDay;
    //项目类型
    public int mProjectType;

    public String mSampleName;
    public String mSampleNum;
    public String mSampleInfo;
    public String mSampleStatus;

    public boolean ck_visible;
    public boolean ck_check;

    @TypeConverters(RecordBeanTypeConverter.class)
    public List<HisRecord> list;


    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getmRecordNum() {
        return mRecordNum;
    }

    public void setmRecordNum(String mRecordNum) {
        this.mRecordNum = mRecordNum;
    }

    public int getmRecordStatus() {
        return mRecordStatus;
    }

    public void setmRecordStatus(int mRecordStatus) {
        this.mRecordStatus = mRecordStatus;
    }

    public String getmProNode() {
        return mProNode;
    }

    public void setmProNode(String mProNode) {
        this.mProNode = mProNode;
    }

    public String getmProTime() {
        return mProTime;
    }

    public void setmProTime(String mProTime) {
        this.mProTime = mProTime;
    }

    public String getmSampleName() {
        return mSampleName;
    }

    public void setmSampleName(String mSampleName) {
        this.mSampleName = mSampleName;
    }

    public String getmSampleNum() {
        return mSampleNum;
    }

    public void setmSampleNum(String mSampleNum) {
        this.mSampleNum = mSampleNum;
    }

    public String getmSampleInfo() {
        return mSampleInfo;
    }

    public void setmSampleInfo(String mSampleInfo) {
        this.mSampleInfo = mSampleInfo;
    }

    public String getmSampleStatus() {
        return mSampleStatus;
    }

    public void setmSampleStatus(String mSampleStatus) {
        this.mSampleStatus = mSampleStatus;
    }

    public List<HisRecord> getList() {
        return list;
    }

    public void setList(List<HisRecord> list) {
        this.list = list;
    }

    public boolean isCk_visible() {
        return ck_visible;
    }

    public void setCk_visible(boolean ck_visible) {
        this.ck_visible = ck_visible;
    }

    public boolean isCk_check() {
        return ck_check;
    }

    public void setCk_check(boolean ck_check) {
        this.ck_check = ck_check;
    }

    public long getmProDay() {
        return mProDay;
    }

    public void setmProDay(long mProDay) {
        this.mProDay = mProDay;
    }

    public int getmProjectType() {
        return mProjectType;
    }

    public void setmProjectType(int mProjectType) {
        this.mProjectType = mProjectType;
    }
}
