package com.mihua.yangben.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by 李浩 on 2019/5/14.
 */
@Entity
public class HistoryReBean {
    @Id(autoincrement = true)
    private Long id;
    @Property
    @Convert(converter = PropertyConverter.class, columnType = String.class)
    private HistoryRe bean;

    @Generated(hash = 536155780)
    public HistoryReBean(Long id, HistoryRe bean) {
        this.id = id;
        this.bean = bean;
    }

    @Generated(hash = 477082369)
    public HistoryReBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistoryRe getBean() {
        return this.bean;
    }

    public void setBean(HistoryRe bean) {
        this.bean = bean;
    }

    public static class CatConverter implements PropertyConverter<HistoryRe, String> {
        @Override
        public HistoryRe convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, HistoryRe.class);
        }

        @Override
        public String convertToDatabaseValue(HistoryRe entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
