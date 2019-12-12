package com.mihua.yangben.bean;

/**
 * Created by lx on 2019/4/9.
 */

public class User {

    /**
     * msg : success
     * code : 0
     * data : {"token":"8fe24e50a9da4090865abef3c4f5c9d8","expire":43199944}
     */

    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : 8fe24e50a9da4090865abef3c4f5c9d8
         * expire : 43199944
         */

        private String token;
        private int expire;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }
    }
}
