package com.mihua.yangben.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class SaveBeanDao extends AbstractDao<SaveBean, Long> {

    public static final String TABLENAME = "SAVE_BEAN";

    /**
     * Properties of entity SaveBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CreateTime = new Property(1, String.class, "createTime", false, "CREATE_TIME");
        public final static Property EquipmentNo = new Property(2, String.class, "equipmentNo", false, "EQUIPMENT_NO");
        public final static Property NodeFailure = new Property(3, String.class, "nodeFailure", false, "NODE_FAILURE");
        public final static Property RecordNo = new Property(4, String.class, "recordNo", false, "RECORD_NO");
        public final static Property Status = new Property(5, int.class, "status", false, "STATUS");
        public final static Property Type = new Property(6, int.class, "type", false, "TYPE");
    }


    public SaveBeanDao(DaoConfig config) {
        super(config);
    }

    public SaveBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"SAVE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CREATE_TIME\" TEXT NOT NULL ," + // 1: createTime
                "\"EQUIPMENT_NO\" TEXT NOT NULL ," + // 2: equipmentNo
                "\"NODE_FAILURE\" TEXT NOT NULL ," + // 3: nodeFailure
                "\"RECORD_NO\" TEXT NOT NULL ," + // 4: recordNo
                "\"STATUS\" INTEGER NOT NULL ," + // 5: status
                "\"TYPE\" INTEGER NOT NULL );"); // 6: type
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SAVE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SaveBean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getCreateTime());
        stmt.bindString(3, entity.getEquipmentNo());
        stmt.bindString(4, entity.getNodeFailure());
        stmt.bindString(5, entity.getRecordNo());
        stmt.bindLong(6, entity.getStatus());
        stmt.bindLong(7, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SaveBean entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getCreateTime());
        stmt.bindString(3, entity.getEquipmentNo());
        stmt.bindString(4, entity.getNodeFailure());
        stmt.bindString(5, entity.getRecordNo());
        stmt.bindLong(6, entity.getStatus());
        stmt.bindLong(7, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public SaveBean readEntity(Cursor cursor, int offset) {
        SaveBean entity = new SaveBean( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // createTime
                cursor.getString(offset + 2), // equipmentNo
                cursor.getString(offset + 3), // nodeFailure
                cursor.getString(offset + 4), // recordNo
                cursor.getInt(offset + 5), // status
                cursor.getInt(offset + 6) // type
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, SaveBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCreateTime(cursor.getString(offset + 1));
        entity.setEquipmentNo(cursor.getString(offset + 2));
        entity.setNodeFailure(cursor.getString(offset + 3));
        entity.setRecordNo(cursor.getString(offset + 4));
        entity.setStatus(cursor.getInt(offset + 5));
        entity.setType(cursor.getInt(offset + 6));
    }

    @Override
    protected final Long updateKeyAfterInsert(SaveBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(SaveBean entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SaveBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
}
