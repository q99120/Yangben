package com.mihua.yangben.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mihua.yangben.db.dao.ConsumablesFuNanDao;
import com.mihua.yangben.db.dao.ConsumablesKQDao;
import com.mihua.yangben.db.dao.HisRecordDao;
import com.mihua.yangben.db.entity.ConsumablesFuNan;
import com.mihua.yangben.db.entity.ConsumablesKQ;
import com.mihua.yangben.db.entity.HisRecord;

@Database(entities = {HisRecord.class, ConsumablesFuNan.class, ConsumablesKQ.class}, version = 1, exportSchema = false)
public abstract class RecordDatabase extends RoomDatabase {
    private static volatile RecordDatabase instance;
    public static final String db_name = "roomDao.db";

    public static synchronized RecordDatabase getinstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RecordDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RecordDatabase.class,
                db_name).allowMainThreadQueries().build();
    }

    public abstract HisRecordDao hisRecordDao();

    public abstract ConsumablesFuNanDao consumablesFuNanDao();

    public abstract ConsumablesKQDao consumablesKQDao();

}
