package com.noweaj.android.bloodsugartracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.noweaj.android.bloodsugartracker.data.entity.ChartEntity

@Dao
interface ChartDao {
    @Query("SELECT COUNT(title) FROM chartENTITY")
    fun getDataCount(): LiveData<Int>
    
    @Query("SELECT * FROM chartENTITY")
    fun getAllEntities(): List<ChartEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: ChartEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntities(entities: List<ChartEntity>): List<Long>
    
    @Delete
    fun deleteEntity(entity: ChartEntity): Int
    
    @Query("DELETE FROM chartENTITY")
    fun deleteAllEntities()
}