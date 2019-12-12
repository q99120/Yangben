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
public class Zoo {
    @Id(autoincrement = true)
    private Long id;

    @Property
    @Convert(converter = CatConverter.class, columnType = String.class)
    private FourFuranBean bean;

    @Generated(hash = 1183163440)
    public Zoo(Long id, FourFuranBean bean) {
        this.id = id;
        this.bean = bean;
    }

    @Generated(hash = 38177487)
    public Zoo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FourFuranBean getBean() {
        return this.bean;
    }

    public void setBean(FourFuranBean bean) {
        this.bean = bean;
    }

    public static class CatConverter implements PropertyConverter<FourFuranBean, String> {
        @Override
        public FourFuranBean convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, FourFuranBean.class);
        }

        @Override
        public String convertToDatabaseValue(FourFuranBean entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}

