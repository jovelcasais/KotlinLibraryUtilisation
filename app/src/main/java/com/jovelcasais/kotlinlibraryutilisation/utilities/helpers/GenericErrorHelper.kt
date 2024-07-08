package com.jovelcasais.kotlinlibraryutilisation.utilities.helpers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jovelcasais.kotlinlibraryutilisation.db.models.ErrorResponseModel
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

fun toErrorMessage(e: Throwable): String {
    return when (e) {
        is IOException -> "Network error, please check your internet connection"
        is HttpException -> "Server error: ${e.response()?.errorBody()?.string() ?: "Unknown error occurred"}"
        else -> "Unknown error occurred"
    }
}

inline fun <reified T> parseApiErrorResponse(response: Response<T>): String {
    return try {
        val gson = Gson()
        val type = object : TypeToken<ErrorResponseModel>() {}.type
        val errorResponse: ErrorResponseModel? = gson.fromJson(response.errorBody()?.charStream(), type)
        errorResponse?.error.toString() ?: "Error parsing response"
    } catch (e: Exception) {
        e.printStackTrace()
        "Error parsing response"
    }
}