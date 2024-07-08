package com.jovelcasais.kotlinlibraryutilisation.api.state

sealed class ApiUIState<out T> {
    data object Idle : ApiUIState<Nothing>()
    data object Loading : ApiUIState<Nothing>()
    data class Success<out T>(val data: T) : ApiUIState<T>()
    data class Error(val error: String) : ApiUIState<Nothing>()
}