package com.willy.kotlin_mvvm_template.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.willy.kotlin_mvvm_template.data.db.entities.LiveTourEntity

/**
 * Created by Willy on 2019/11/7.
 */
@Dao
interface LiveTourDao {
    /**
     * 新增一個 LiveTour
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLiveTour(entity: LiveTourEntity)

    /**
     * 新增多個 LiveTour
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllLiveTours(entities: List<LiveTourEntity>)

    /**
     * 取得全部 LiveTours
     */
    @Query("SELECT * FROM livetours ORDER BY createAt")
    fun getLiveTours(): LiveData<List<LiveTourEntity>>

    /**
     * 取得全部未上傳 LiveTours
     */
    @Query("SELECT * FROM livetours ORDER BY createAt DESC")
    fun getNotUploadedLiveTours(): LiveData<List<LiveTourEntity>>
}