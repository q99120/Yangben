package com.mihua.yangben.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class SaveBean {
    //不能用int
    @Id(autoincrement = true)
    private Long id;
    //姓名
    @NotNull
    private String createTime;
    //学号
    @NotNull
    private String equipmentNo;
    //性别
    @NotNull
    private String nodeFailure;
    //样本编号
    @NotNull
    private String recordNo;
    //状态
    private int status;
    //状态
    private int type;

    @Generated(hash = 1736721663)
    public SaveBean(Long id, @NotNull String createTime,
                    @NotNull String equipmentNo, @NotNull String nodeFailure,
                    @NotNull String recordNo, int status, int type) {
        this.id = id;
        this.createTime = createTime;
        this.equipmentNo = equipmentNo;
        this.nodeFailure = nodeFailure;
        this.recordNo = recordNo;
        this.status = status;
        this.type = type;
    }

    @Generated(hash = 127566870)
    public SaveBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEquipmentNo() {
        return this.equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getNodeFailure() {
        return this.nodeFailure;
    }

    public void setNodeFailure(String nodeFailure) {
        this.nodeFailure = nodeFailure;
    }

    public String getRecordNo() {
        return this.recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}