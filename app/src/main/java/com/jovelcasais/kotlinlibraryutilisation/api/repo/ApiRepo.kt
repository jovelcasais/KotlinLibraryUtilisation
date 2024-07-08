package com.jovelcasais.kotlinlibraryutilisation.api.repo

import com.jovelcasais.kotlinlibraryutilisation.api.RetrofitManager
import com.jovelcasais.kotlinlibraryutilisation.api.irepo.IApiRepo
import com.jovelcasais.kotlinlibraryutilisation.db.models.NewsModel
import com.jovelcasais.kotlinlibraryutilisation.db.models.ResponseGetModel
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.parseApiErrorResponse
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.toErrorMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepo {

    private val iApi: IApiRepo = RetrofitManager.getInstance(1).create(IApiRepo::class.java)

    fun getNews(params: Map<String, String>, onSuccess: (List<NewsModel>) -> Unit, onError: (String) -> Unit){
        iApi.getItems(params).enqueue(object : Callback<ResponseGetModel<NewsModel>> {
            override fun onResponse(call: Call<ResponseGetModel<NewsModel>>, response: Response<ResponseGetModel<NewsModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it.data) }
                }else{
                   onError(parseApiErrorResponse(response))
                }
            }
            override fun onFailure(call: Call<ResponseGetModel<NewsModel>>, t: Throwable) {
                onError(toErrorMessage(t))
            }
        })
    }

}