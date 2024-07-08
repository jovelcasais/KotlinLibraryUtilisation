package com.jovelcasais.kotlinlibraryutilisation.utilities.helpers

import com.jovelcasais.kotlinlibraryutilisation.utilities.constants.ApiConstants

object DataHelper {
    fun getApiParam() : Map<String, String>{
        return mapOf(
            "api_token" to ApiConstants.API_TOKEN,
            "language" to ApiConstants.API_DATA_LANGUAGE,
            "limit" to ApiConstants.API_DATA_LIMIT
        )
    }
}