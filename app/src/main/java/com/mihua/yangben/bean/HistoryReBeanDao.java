package com.mihua.yangben.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class HistoryReBeanDao extends AbstractDao<HistoryReBean, Long> {

    public static final String TABLENAME = "HISTORY_RE_BEAN";

    /**
     * Properties of entity HistoryReBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Bean = new Property(1, String.class, "bean", false, "BEAN");
    }

    private final HistoryReBean.CatConverter beanConverter = new HistoryReBean.CatConverter();

    public HistoryReBeanDao(DaoConfig config) {
        super(config);
    }

    public HistoryReBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTORY_RE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"BEAN\" TEXT);"); // 1: bean
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTORY_RE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HistoryReBean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        HistoryRe bean = entity.getBean();
        if (bean != null) {
            stmt.bindString(2, beanConverter.convertToDatabaseValue(bean));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HistoryReBean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        HistoryRe bean = entity.getBean();
        if (bean != null) {
            stmt.bindString(2, beanConverter.convertToDatabaseValue(bean));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public HistoryReBean readEntity(Cursor cursor, int offset) {
        HistoryReBean entity = new HistoryReBean( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : beanConverter.convertToEntityProperty(cursor.getString(offset + 1)) // bean
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, HistoryReBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBean(cursor.isNull(offset + 1) ? null : beanConverter.convertToEntityProperty(cursor.getString(offset + 1)));
    }

    @Override
    protected final Long updateKeyAfterInsert(HistoryReBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(HistoryReBean entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HistoryReBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
}
