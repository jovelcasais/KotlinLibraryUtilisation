package com.jovelcasais.kotlinlibraryutilisation.db.models

data class ErrorResponseModel(
    val error: Error
)

data class Error(
    val code: String,
    val message: String
)