package com.jovelcasais.kotlinlibraryutilisation.api.irepo

import com.jovelcasais.kotlinlibraryutilisation.db.models.NewsModel
import com.jovelcasais.kotlinlibraryutilisation.db.models.ResponseGetModel
import com.jovelcasais.kotlinlibraryutilisation.utilities.constants.ApiConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IApiRepo {
    @GET(ApiConstants.API_VERSION+ ApiConstants.API_GET_NEWS)
    fun getItems(@QueryMap params: Map<String, String>) : Call<ResponseGetModel<NewsModel>>
}