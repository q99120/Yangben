package com.mihua.yangben.bean;

/**
 * Created by 李浩 on 2019/5/8.
 */

public class ConsumablesBean {

    /**
     * msg : success
     * code : 0
     * Consume : {"type":0,"parameter":"{\"hcl\":\"3164\",\"fywd\":\"140\",\"fysj\":\"140\",\"jbzs1\":\"161\",\"jbsj1\":\"154\",\"naoh\":\"378\",\"pbshcy\":\"3878\",\"jbzs2\":\"154\",\"jbsj2\":\"154\",\"ch3oh\":\"315\",\"hhzy1\":\"154\",\"hhsj1\":\"154\",\"hhh2o\":\"378\",\"hhzy2\":\"154\",\"hhsj2\":\"154\",\"syyby\":\"154\",\"syzy\":\"154\",\"sysj\":\"154\",\"sycs\":\"154\",\"jhh2o\":\"315\",\"jhzy\":\"154\",\"jhsj\":\"154\",\"c4h8o2\":\"378\",\"xtzy\":\"88\",\"xtsj\":\"88\",\"nsyywd\":\"154\",\"nscqwd\":\"1554\",\"nssj\":\"154\"}"}
     */

    private String msg;
    private int code;
    private ConsumeBean Consume;

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

    public ConsumeBean getConsume() {
        return Consume;
    }

    public void setConsume(ConsumeBean Consume) {
        this.Consume = Consume;
    }

    public static class ConsumeBean {
        /**
         * type : 0
         * parameter : {"hcl":"3164","fywd":"140","fysj":"140","jbzs1":"161","jbsj1":"154","naoh":"378","pbshcy":"3878","jbzs2":"154","jbsj2":"154","ch3oh":"315","hhzy1":"154","hhsj1":"154","hhh2o":"378","hhzy2":"154","hhsj2":"154","syyby":"154","syzy":"154","sysj":"154","sycs":"154","jhh2o":"315","jhzy":"154","jhsj":"154","c4h8o2":"378","xtzy":"88","xtsj":"88","nsyywd":"154","nscqwd":"1554","nssj":"154"}
         */

        private int type;
        private String parameter;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }
    }
}
