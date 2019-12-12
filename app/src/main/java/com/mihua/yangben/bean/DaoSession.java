package com.mihua.yangben.bean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyReBeanDaoConfig;
    private final DaoConfig saveBeanDaoConfig;
    private final DaoConfig saveBeansDaoConfig;
    private final DaoConfig zooDaoConfig;

    private final HistoryReBeanDao historyReBeanDao;
    private final SaveBeanDao saveBeanDao;
    private final SaveBeansDao saveBeansDao;
    private final ZooDao zooDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyReBeanDaoConfig = daoConfigMap.get(HistoryReBeanDao.class).clone();
        historyReBeanDaoConfig.initIdentityScope(type);

        saveBeanDaoConfig = daoConfigMap.get(SaveBeanDao.class).clone();
        saveBeanDaoConfig.initIdentityScope(type);

        saveBeansDaoConfig = daoConfigMap.get(SaveBeansDao.class).clone();
        saveBeansDaoConfig.initIdentityScope(type);

        zooDaoConfig = daoConfigMap.get(ZooDao.class).clone();
        zooDaoConfig.initIdentityScope(type);

        historyReBeanDao = new HistoryReBeanDao(historyReBeanDaoConfig, this);
        saveBeanDao = new SaveBeanDao(saveBeanDaoConfig, this);
        saveBeansDao = new SaveBeansDao(saveBeansDaoConfig, this);
        zooDao = new ZooDao(zooDaoConfig, this);

        registerDao(HistoryReBean.class, historyReBeanDao);
        registerDao(SaveBean.class, saveBeanDao);
        registerDao(SaveBeans.class, saveBeansDao);
        registerDao(Zoo.class, zooDao);
    }

    public void clear() {
        historyReBeanDaoConfig.clearIdentityScope();
        saveBeanDaoConfig.clearIdentityScope();
        saveBeansDaoConfig.clearIdentityScope();
        zooDaoConfig.clearIdentityScope();
    }

    public HistoryReBeanDao getHistoryReBeanDao() {
        return historyReBeanDao;
    }

    public SaveBeanDao getSaveBeanDao() {
        return saveBeanDao;
    }

    public SaveBeansDao getSaveBeansDao() {
        return saveBeansDao;
    }

    public ZooDao getZooDao() {
        return zooDao;
    }

}
