package com.mihua.yangben.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mihua.yangben.db.entity.ConsumablesKQ;

import java.util.List;

/**
 * 呋喃四项耗材记录表
 */

@Dao
public interface ConsumablesKQDao {
    //新增
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertKQ(ConsumablesKQ... consumablesKQS);


    //更新
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateKQ(ConsumablesKQ... consumablesKQS);

    //查询所有
    @Query("SELECT * FROM CONSUMABLES_KQ_TABLE")
    List<ConsumablesKQ> getAllKQ();


    //只查询当日数据
    @Query("SELECT * FROM CONSUMABLES_KQ_TABLE WHERE KQTime==:curretTime")
    List<ConsumablesKQ> getCurretTime(String curretTime);
}
