package com.mihua.yangben.db.conventer;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mihua.yangben.db.entity.HisRecord;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class RecordBeanTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public List<HisRecord> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<HisRecord>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(List<HisRecord> someObjects) {
        return gson.toJson(someObjects);
    }

}
