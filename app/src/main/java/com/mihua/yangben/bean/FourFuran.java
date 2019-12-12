package com.mihua.yangben.bean;

/**
 * Created by 李浩 on 2019/4/25.
 */

public class FourFuran {

    /**
     * msg : success
     * code : 0
     * plan : {"value":"{\"hcl\":\"45\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}","modifiedTime":"2019-04-23T10:54:26.000+0800"}
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
         * value : {"hcl":"45","fywd":"20","fysj":"20","jbzs1":"23","jbsj1":"22","naoh":"54","pbshcy":"554","jbzs2":"22","jbsj2":"22","ch3oh":"45","hhzy1":"22","hhsj1":"22","hhh2o":"54","hhzy2":"22","hhsj2":"22","syyby":"22","syzy":"22","sysj":"22","sycs":"22","jhh2o":"45","jhzy":"22","jhsj":"22","c4h8o2":"54","xtzy":"22","xtsj":"22","nsyywd":"22","nscqwd":"222","nssj":"22","type":0}
         * modifiedTime : 2019-04-23T10:54:26.000+0800
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
