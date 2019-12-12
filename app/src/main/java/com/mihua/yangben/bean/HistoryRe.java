package com.mihua.yangben.bean;

import java.util.List;

/**
 * Created by 李浩 on 2019/5/10.
 */

public class HistoryRe {

    /**
     * msg : success
     * code : 0
     * record : [{"recordId":68,"recordNo":"NO190509162819401","status":0,"nodeFailure":"1","createTime":"2019-05-09T16:28:19.000+0800","type":0,"recordLists":[{"channelNo":1,"sampleNo":"221","sampleName":"鱼肉","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","status":0}]}]
     */

    private String msg;
    private int code;
    private List<RecordBean> record;

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

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * recordId : 68
         * recordNo : NO190509162819401
         * status : 0
         * nodeFailure : 1
         * createTime : 2019-05-09T16:28:19.000+0800
         * type : 0
         * recordLists : [{"channelNo":1,"sampleNo":"221","sampleName":"鱼肉","parameter":"{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","status":0}]
         */

        private int recordId;
        private String recordNo;
        private int status;
        private String nodeFailure;
        private String createTime;
        private int type;
        private List<RecordListsBean> recordLists;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
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

        public String getNodeFailure() {
            return nodeFailure;
        }

        public void setNodeFailure(String nodeFailure) {
            this.nodeFailure = nodeFailure;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<RecordListsBean> getRecordLists() {
            return recordLists;
        }

        public void setRecordLists(List<RecordListsBean> recordLists) {
            this.recordLists = recordLists;
        }

        public static class RecordListsBean {
            /**
             * channelNo : 1
             * sampleNo : 221
             * sampleName : 鱼肉
             * parameter : {"hcl":"452","fywd":"20","fysj":"20","jbzs1":"23","jbsj1":"22","naoh":"54","pbshcy":"554","jbzs2":"22","jbsj2":"22","ch3oh":"45","hhzy1":"22","hhsj1":"22","hhh2o":"54","hhzy2":"22","hhsj2":"22","syyby":"22","syzy":"22","sysj":"22","sycs":"22","jhh2o":"45","jhzy":"22","jhsj":"22","c4h8o2":"54","xtzy":"22","xtsj":"22","nsyywd":"22","nscqwd":"222","nssj":"22","type":0}
             * status : 0
             */

            private int channelNo;
            private String sampleNo;
            private String sampleName;
            private String parameter;
            private int status;

            public int getChannelNo() {
                return channelNo;
            }

            public void setChannelNo(int channelNo) {
                this.channelNo = channelNo;
            }

            public String getSampleNo() {
                return sampleNo;
            }

            public void setSampleNo(String sampleNo) {
                this.sampleNo = sampleNo;
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
}
