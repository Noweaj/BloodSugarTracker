package com.noweaj.android.bloodsugartracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.noweaj.android.bloodsugartracker.data.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun eventDao(): EventDao

    companion object{
        private val DB_NAME = "bloodsugar-db"
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).addCallback(object: RoomDatabase.Callback(){}).build()
        }
    }
}