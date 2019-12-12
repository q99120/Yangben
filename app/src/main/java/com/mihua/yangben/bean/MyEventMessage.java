package com.mihua.yangben.bean;

/**
 * Created by lx on 2019/4/15.
 */

//项目的事件类
public class MyEventMessage {
    public int modify;    //从个人中心的用户信息页面跳转到二级页面的事件
    public int processingDetails;    //从样本处理首页点击确定进入样本处理详情页面的事件
    public int updata;//刷新个人信息
    public String fileName;//传递excel文件名和文件路径
    public String channelNo;
    public String updatas;

    public String getUpdatas() {
        return updatas;
    }

    public void setUpdatas(String updatas) {
        this.updatas = updatas;
    }


    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public int getUpdata() {
        return updata;
    }

    public void setUpdata(int updata) {
        this.updata = updata;
    }


    public int getModify() {
        return modify;
    }

    public void setModify(int modify) {
        this.modify = modify;
    }


    public int getProcessingDetails() {
        return processingDetails;
    }

    public void setProcessingDetails(int processingDetails) {
        this.processingDetails = processingDetails;
    }


}
