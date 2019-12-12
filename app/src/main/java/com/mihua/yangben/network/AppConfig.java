package com.mihua.yangben.network;

/**
 * Created by lx on 2019/4/9.
 */

public class AppConfig {
    public static final String BASE_URL = "http://192.168.6.68:8081";//服务器
    //baudrate
    public static final int BAUDRATE = 115200;
    public static final String LOGIN = "/api/login/loginByPassword";//登录
    public static final String VIDEOLIST = "/api/video/videoList";//视频列表
    public static final String USERINFO = "/api/user/userInfo";//个人信息
    public static final String UPATAPASSWORD = "/api/user/changePassword";//修改密码
    public static final String UPDATE = "/api/user/update";//修改个人信息
    public static final String GETPLAN = "/api/plan/getPlan";//获取项目参数方案
    public static final String SAVE = "/api/detection/save";//保存记录
    public static final String LIST = "/api/detection/list";//记录列表
    public static final String DETALIEDLIST = "/api/detection/detailedList";//详情列表
    public static final String DELETE = "/api/detection/delete";//删除记录
    public static final String PICTURELIST = "/api/picture/pictureList";//图片列表
    public static final String SAVECONSUME = "/api/detection/saveConsume";//更新耗材参数
    public static final String CONSUME = "/api/detection/getConsume";//获取耗材参数
    public static final String RESETCONSUME = "/api/detection/resetConsume";//重置耗材参数
    public static final String RECORDCOUNT = "/api/detection/recordCount";//单日记录条数

}
