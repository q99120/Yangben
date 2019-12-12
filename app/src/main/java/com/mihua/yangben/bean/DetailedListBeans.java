package com.mihua.yangben.bean;

import java.util.List;

/**
 * Created by 李浩 on 2019/5/5.
 */

public class DetailedListBeans {

    /**
     * msg : success
     * detailedList : [[{"channelNo":1,"sampleName":"鱼肉","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","status":0},{"channelNo":2,"sampleName":"鱼肉","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","status":0},{"channelNo":3,"sampleName":"鱼肉","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","status":0},{"channelNo":4,"sampleName":"鱼肉","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","status":0}]]
     * code : 0
     */

    private String msg;
    private int code;
    private List<List<DetailedListBean>> detailedList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<List<DetailedListBean>> getDetailedList() {
        return detailedList;
    }

    public void setDetailedList(List<List<DetailedListBean>> detailedList) {
        this.detailedList = detailedList;
    }

    public static class DetailedListBean {
        /**
         * channelNo : 1
         * sampleName : 鱼肉
         * parameter : {"hcl":"452","fywd":"20","fysj":"20","jbzs1":"23","jbsj1":"22","naoh":"54","pbshcy":"554","jbzs2":"22","jbsj2":"22","ch3oh":"45","hhzy1":"22","hhsj1":"22","hhh2o":"54","hhzy2":"22","hhsj2":"22","syyby":"22","syzy":"22","sysj":"22","sycs":"22","jhh2o":"45","jhzy":"22","jhsj":"22","c4h8o2":"54","xtzy":"22","xtsj":"22","nsyywd":"22","nscqwd":"222","nssj":"22","type":0}
         * status : 0
         */

        private int channelNo;
        private String sampleName;
        private String parameter;
        private int status;

        public int getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(int channelNo) {
            this.channelNo = channelNo;
        }

        public String getSampleName() {
            return sampleName;
        }

        public void setSampleName(String sampleName) {
            this.sampleName = sampleName;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
