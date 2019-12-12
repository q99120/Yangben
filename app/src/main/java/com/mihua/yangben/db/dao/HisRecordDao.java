package com.mihua.yangben.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mihua.yangben.db.entity.HisRecord;

import java.util.List;

/**
 * 历史记录实例
 */
@Dao
public interface HisRecordDao {
    //新增
    @Insert
    void insertHisRc(HisRecord... hisRecord);

    @Insert
    void insertHisList(List<HisRecord> hisRecords);

    //更新
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateHisRc(HisRecord... hisRecord);

    //删除
    @Delete
    void deleteHisRc(HisRecord hisRecord);

    //带参数查询,通过编号和类型查询
    @Query("SELECT * FROM his_table WHERE mRecordNum == :recordNum  and mProjectType==:proType ")
    List<HisRecord> loadAllHisByRecordNo(String recordNum, int proType);

    //当天查询
    @Query("SELECT * FROM his_table WHERE mProDay == :proDay ")
    List<HisRecord> loadAllHisByCurrentDay(long proDay);

    //查询所有
    @Query("SELECT * FROM his_table")
    List<HisRecord> getAll();

    //查询时间段内的所有样本,暂时不使用
    @Query("SELECT * FROM his_table  WHERE  mProDay  BETWEEN  :startTime and :endTime")
    List<HisRecord> getAllByDay(long startTime, long endTime);


    //根据项目信息查询
    @Query("SELECT * FROM his_table WHERE mProjectType == :type")
    List<HisRecord> loadProjectType(int type);

    //根据时间段查询
    @Query("SELECT * FROM his_table WHERE mProjectType ==:type and mProDay  BETWEEN  :startTime and :endTime")
    List<HisRecord> loadTimeBetween(long startTime, long endTime, int type);


}
