package com.mihua.yangben.bean;

/**
 * Created by 李浩 on 2019/4/25.
 */

public class UserBeans {

    /**
     * msg : success
     * code : 0
     * user : {"userName":"test01","name":"李浩","policeNo":"001","duties":"测试","deptName":"测试"}
     */

    private String msg;
    private int code;
    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * userName : test01
         * name : 李浩
         * policeNo : 001
         * duties : 测试
         * deptName : 测试
         */

        private String userName;
        private String name;
        private String policeNo;
        private String duties;
        private String deptName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoliceNo() {
            return policeNo;
        }

        public void setPoliceNo(String policeNo) {
            this.policeNo = policeNo;
        }

        public String getDuties() {
            return duties;
        }

        public void setDuties(String duties) {
            this.duties = duties;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }
    }
}
