package com.jovelcasais.kotlinlibraryutilisation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jovelcasais.kotlinlibraryutilisation.db.dao.INewsDao
import com.jovelcasais.kotlinlibraryutilisation.db.models.NewsModel
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.DateConverterHelper
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.RoomConverterHelper

@TypeConverters(value = [DateConverterHelper::class, RoomConverterHelper::class])
@Database(
    entities = [NewsModel::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun iNewsDao() : INewsDao

    companion object{
        @Volatile
        var INSTANCE: DatabaseService? = null
        fun getDatabase(context: Context): DatabaseService {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, DatabaseService::class.java,
                    name = "myDb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}