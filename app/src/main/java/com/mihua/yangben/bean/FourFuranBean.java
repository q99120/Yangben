package com.mihua.yangben.bean;

import java.util.List;

/**
 * Created by 李浩 on 2019/4/25.
 */

public class FourFuranBean {


    /**
     * createTime : 2019-05-15 17:19:12
     * equipmentNo : 77
     * nodeFailure : 1
     * recordList : [{"sampleNo":"8845","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","sampleName":"鱼肉","recordId":1,"channelNo":2,"status":0}]
     * recordNo : NO190515171912662
     * status : 0
     * type : 0
     * updata : 0
     */

    private String createTime;
    private String equipmentNo;
    private String nodeFailure;
    private String recordNo;
    private int status;
    private int type;
    private int updata;
    private List<RecordListBean> recordList;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getNodeFailure() {
        return nodeFailure;
    }

    public void setNodeFailure(String nodeFailure) {
        this.nodeFailure = nodeFailure;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUpdata() {
        return updata;
    }

    public void setUpdata(int updata) {
        this.updata = updata;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public static class RecordListBean {
        /**
         * sampleNo : 8845
         * parameter : {"hcl":"452","fywd":"20","fysj":"20","jbzs1":"23","jbsj1":"22","naoh":"54","pbshcy":"554","jbzs2":"22","jbsj2":"22","ch3oh":"45","hhzy1":"22","hhsj1":"22","hhh2o":"54","hhzy2":"22","hhsj2":"22","syyby":"22","syzy":"22","sysj":"22","sycs":"22","jhh2o":"45","jhzy":"22","jhsj":"22","c4h8o2":"54","xtzy":"22","xtsj":"22","nsyywd":"22","nscqwd":"222","nssj":"22","type":0}
         * sampleName : 鱼肉
         * recordId : 1
         * channelNo : 2
         * status : 0
         */

        private String sampleNo;
        private String parameter;
        private String sampleName;
        private int recordId;
        private int channelNo;
        private int status;

        public String getSampleNo() {
            return sampleNo;
        }

        public void setSampleNo(String sampleNo) {
            this.sampleNo = sampleNo;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getSampleName() {
            return sampleName;
        }

        public void setSampleName(String sampleName) {
            this.sampleName = sampleName;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public int getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(int channelNo) {
            this.channelNo = channelNo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
