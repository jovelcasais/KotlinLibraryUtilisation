package com.jovelcasais.kotlinlibraryutilisation.db.repo

import com.jovelcasais.kotlinlibraryutilisation.db.dao.INewsDao
import com.jovelcasais.kotlinlibraryutilisation.db.models.NewsModel
import kotlinx.coroutines.flow.Flow

class NewsRepo(private val dao: INewsDao) {

    suspend fun upsert(items: List<NewsModel>){
        dao.upsert(items)
    }

    fun getAllItems() : Flow<List<NewsModel?>> {
        return dao.getAllItems()
    }

}