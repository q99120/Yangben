package com.mihua.yangben.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 李浩 on 2019/5/14.
 */
@Entity
public class SaveBeans {
    //不能用int
    @Id(autoincrement = true)
    private Long id;
    //状态
    private int ids;
    private String channelNo;
    private String parameter;
    private int recordId;
    private String sampleName;
    private String sampleNo;
    private int status;

    @Generated(hash = 1440143332)
    public SaveBeans(Long id, int ids, String channelNo, String parameter,
                     int recordId, String sampleName, String sampleNo, int status) {
        this.id = id;
        this.ids = ids;
        this.channelNo = channelNo;
        this.parameter = parameter;
        this.recordId = recordId;
        this.sampleName = sampleName;
        this.sampleNo = sampleNo;
        this.status = status;
    }

    @Generated(hash = 63205557)
    public SaveBeans() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIds() {
        return this.ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getChannelNo() {
        return this.channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getSampleName() {
        return this.sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
