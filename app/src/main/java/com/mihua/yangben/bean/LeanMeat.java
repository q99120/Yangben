package com.mihua.yangben.bean;

/**
 * Created by 李浩 on 2019/4/25.
 */

public class LeanMeat {

    /**
     * msg : success
     * code : 0
     * plan : {"value":"{\"ch3cooh\":\"123\",\"fywd\":\"432\",\"fysj\":\"34\",\"jbzs\":\"4\",\"jbsj\":\"1231\",\"hhch3oh\":\"123\",\"hhzy1\":\"123\",\"hhsj1\":\"123\",\"hhh2o\":\"4324\",\"hhzy2\":\"234\",\"hhsj2\":\"234\",\"hclo4\":\"234\",\"hhzy3\":\"234\",\"hhsj3\":\"234\",\"syyby\":\"234\",\"syzy\":\"234\",\"sysj\":\"234\",\"sycs\":\"23\",\"jhch3oh\":\"234\",\"jhzy1\":\"234\",\"jhsj1\":\"234\",\"ch3ohh2o\":\"234\",\"jhzy2\":\"234\",\"jhsj2\":\"52\",\"ahch3oh\":\"134\",\"xtzy\":\"234\",\"xtsj\":\"23\",\"type\":2}","modifiedTime":"2019-04-19T00:00:00.000+0800"}
     */

    private String msg;
    private int code;
    private PlanBean plan;

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

    public PlanBean getPlan() {
        return plan;
    }

    public void setPlan(PlanBean plan) {
        this.plan = plan;
    }

    public static class PlanBean {
        /**
         * value : {"ch3cooh":"123","fywd":"432","fysj":"34","jbzs":"4","jbsj":"1231","hhch3oh":"123","hhzy1":"123","hhsj1":"123","hhh2o":"4324","hhzy2":"234","hhsj2":"234","hclo4":"234","hhzy3":"234","hhsj3":"234","syyby":"234","syzy":"234","sysj":"234","sycs":"23","jhch3oh":"234","jhzy1":"234","jhsj1":"234","ch3ohh2o":"234","jhzy2":"234","jhsj2":"52","ahch3oh":"134","xtzy":"234","xtsj":"23","type":2}
         * modifiedTime : 2019-04-19T00:00:00.000+0800
         */

        private String value;
        private String modifiedTime;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getModifiedTime() {
            return modifiedTime;
        }

        public void setModifiedTime(String modifiedTime) {
            this.modifiedTime = modifiedTime;
        }
    }
}
