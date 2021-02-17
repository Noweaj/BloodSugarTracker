package com.noweaj.android.bloodsugartracker.data.local

import androidx.room.*
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity

@Dao
interface EventDao {
    @Query("SELECT * FROM eventENTITY WHERE timestamp BETWEEN :startDate AND :endDate")
    fun getEntitiesBetweenDates(startDate: Long, endDate: Long): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: EventEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntities(entities: List<EventEntity>): List<Long>

    @Delete
    fun deleteEntities(entities: List<EventEntity>): Int

    @Query("DELETE FROM eventENTITY")
    fun deleteAllEntities()
}