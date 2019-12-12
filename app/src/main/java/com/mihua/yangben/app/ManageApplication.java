package com.mihua.yangben.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.danikula.videocache.HttpProxyCacheServer;
import com.mihua.yangben.bean.DaoMaster;
import com.mihua.yangben.bean.DaoSession;
import com.mihua.yangben.usb.UsbSerialUtils;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * <pre>
 *
 * </pre>
 */
public class ManageApplication extends Application {
    private HttpProxyCacheServer proxy;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static ManageApplication instances;
    private DaoSession daoSession;

    private static Context context;

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), "248f40ca8c", true);
        //初始化usb工具类
        instances = this;
        UsbSerialUtils.init(this);
        //配置数据库
        setupDatabase();


    }

    public static HttpProxyCacheServer getProxy(Context context) {
        ManageApplication app = (ManageApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this).maxCacheSize(1024 * 1024 * 1024).maxCacheFilesCount(20).build();
    }


    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoInstant() {
        return daoSession;
    }


    //返回
    public static Context getContexts() {
        return context;
    }
}
