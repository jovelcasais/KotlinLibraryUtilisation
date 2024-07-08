package com.jovelcasais.kotlinlibraryutilisation.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jovelcasais.kotlinlibraryutilisation.utilities.constants.ApiConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    fun getInstance(apiType: Int): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilderHelper()))
            .client(okHttpClientHelper(apiType))
            .build()
    }

    private fun gsonBuilderHelper() : Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    private fun okHttpClientHelper(apiType:Int) : OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .connectTimeout(ApiConstants.API_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.API_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.API_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val original : Request = chain.request()
                val request : Request = if(apiType > 0){
                    original.newBuilder()
                        .header("Content-Type", "application/json")
                        //.header("Authorization", "Bearer " + SharedPreferenceHelper.getString("token", ""))
                        .method(original.method, original.body)
                        .build()
                }else{
                    original.newBuilder()
                        .method(original.method, original.body)
                        .build()
                }

                chain.proceed(request)
            })
            .build()
    }

}