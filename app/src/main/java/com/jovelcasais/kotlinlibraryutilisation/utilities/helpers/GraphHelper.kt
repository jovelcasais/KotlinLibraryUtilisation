package com.jovelcasais.kotlinlibraryutilisation.utilities.helpers

import android.content.Context
import com.jovelcasais.kotlinlibraryutilisation.db.DatabaseService
import com.jovelcasais.kotlinlibraryutilisation.db.repo.NewsRepo

object GraphHelper {

    private lateinit var db: DatabaseService

    val newsRepo by lazy {
        NewsRepo(
            dao = db.iNewsDao()
        )
    }

    fun provide(context: Context){
        db = DatabaseService.getDatabase(context)
    }
}