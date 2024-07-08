package com.jovelcasais.kotlinlibraryutilisation.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jovelcasais.kotlinlibraryutilisation.db.models.NewsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface INewsDao {

    /* Update or Insert Data */
    @Upsert
    suspend fun upsertByBatch(items : List<NewsModel>)

    @Transaction
    suspend fun upsert(items: List<NewsModel>) {
        upsertByBatch(items)
    }

    /* Get All Data or Null */
    @Query("SELECT * FROM NewsModel")
    fun getAllItems() : Flow<List<NewsModel?>>

}