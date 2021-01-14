package com.noweaj.android.bloodsugartracker.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase: RoomDatabase(){
    abstract fun recordDao(): RecordDao

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